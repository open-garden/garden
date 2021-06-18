from base_analayzer import BaseAnalyzer
from datas.lane_data import SceneData
from datas.scenario_schema import GRAPH_ID

from utils.rdf_graph_accessor import RdfGraphAccessor, PRIFIX_STR
from utils.rdf_graph_creator import RdfGraphCreator

SAKURA32_CONDITIONS = {
    'SAKURA32-No1': ['Mainroadway', 'LaneKeep', 'Cutin'],
    'SAKURA32-No2': ['Mainroadway', 'LaneKeep', 'Cutout'],
    'SAKURA32-No3': ['Mainroadway', 'LaneKeep', 'Accel'],
    'SAKURA32-No4': ['Mainroadway', 'LaneKeep', 'Decel'],
    'SAKURA32-No5': ['Mainroadway', 'LaneChange', 'Cutin'],
    'SAKURA32-No6': ['Mainroadway', 'LaneChange', 'Cutout'],
    'SAKURA32-No7': ['Mainroadway', 'LaneChange', 'Accel'],
    'SAKURA32-No8': ['Mainroadway', 'LaneChange', 'Decel'],
    'SAKURA32-No9': ['Mainroadway', 'LaneChange', 'Sync'],
    'SAKURA32-No10': ['Merge', 'LaneKeep', 'Cutin'],
    'SAKURA32-No11': ['Merge', 'LaneKeep', 'Sync'],
    'SAKURA32-No12': ['Merge', 'LaneChange', 'Cutin'],
    'SAKURA32-No13': ['Merge', 'LaneChange', 'Cutout'],
    'SAKURA32-No14': ['Merge', 'LaneChange', 'Accel'],
    'SAKURA32-No15': ['Merge', 'LaneChange', 'Decel'],
    'SAKURA32-No16': ['Merge', 'LaneChange', 'Sync'],
    'SAKURA32-No17': ['Departure', 'LaneKeep', 'Cutin'],
    'SAKURA32-No18': ['Departure', 'LaneKeep', 'Sync'],
    'SAKURA32-No19': ['Departure', 'LaneChange', 'Cutin'],
    'SAKURA32-No20': ['Departure', 'LaneChange', 'Cutout'],
    'SAKURA32-No21': ['Departure', 'LaneChange', 'Accel'],
    'SAKURA32-No22': ['Departure', 'LaneChange', 'Decel'],
    'SAKURA32-No23': ['Departure', 'LaneChange', 'Sync'],
    'SAKURA32-No24': ['Ramp', 'LaneKeep', 'Cutin'],
    'SAKURA32-No25': ['Ramp', 'LaneKeep', 'Cutout'],
    'SAKURA32-No26': ['Ramp', 'LaneKeep', 'Accel'],
    'SAKURA32-No27': ['Ramp', 'LaneKeep', 'Decel'],
    'SAKURA32-No28': ['Ramp', 'LaneChange', 'Cutin'],
    'SAKURA32-No29': ['Ramp', 'LaneChange', 'Cutout'],
    'SAKURA32-No30': ['Ramp', 'LaneChange', 'Accel'],
    'SAKURA32-No31': ['Ramp', 'LaneChange', 'Decel'],
    'SAKURA32-No32': ['Ramp', 'LaneChange', 'Sync'],
}

roadgeometry_query = """

  SELECT ?geoStart ?geoEnd WHERE {
    GRAPH <%s> {
      ?scenario a zss:RawData ;
                rdfs:label "%s".
      ?scenario zss:actor ?ego .
      ?ego rdfs:label "ego" .
      ?sceneType a zss:SceneType ;
                 rdfs:label "RoadGeometry" .
      ?sceneType zss:initial/zss:next* ?scene .
      ?scene a zss:Scene ;
             rdfs:label "%s"  ;
             zss:start [ a zss:Time ;
                         rdfs:label ?geoStart] ;
             zss:end [ a zss:Time ;
                       rdfs:label ?geoEnd ] .
    }
  }

""" % (GRAPH_ID, '%s', '%s')

ego_behavior_query = """

  SELECT (if(?egoBehaviorStart >= ?geoStart, ?egoBehaviorStart, ?geoStart) AS ?egoStart) (if(?egoBehaviorEnd <= ?geoEnd, ?egoBehaviorEnd, ?geoEnd) AS ?egoEnd) WHERE {
    {%s}
    GRAPH <%s> {
      ?scenario a zss:RawData ;
                rdfs:label "%s".
      ?scenario zss:actor ?ego .
      ?ego rdfs:label "ego" .
      ?sceneType a zss:SceneType ;
                 rdfs:label "Behavior_Lane_Motion" ;
                 zss:actor ?ego.
      ?sceneType zss:initial/zss:next* ?scene .
      ?scene a zss:Scene ;
             rdfs:label "%s"  ;
             zss:start [ a zss:Time ;
                         rdfs:label ?egoBehaviorStart ] ;
             zss:end [ a zss:Time ;
                       rdfs:label ?egoBehaviorEnd ] .
    }

    FILTER(?egoBehaviorStart <= ?geoEnd && ?egoBehaviorEnd >= ?geoStart)
    
  }

""" % ('%s', GRAPH_ID, '%s', '%s')

obs_behavior_query = """

  SELECT ?actor (if(?egoStart >= ?obsBehaviorStart, ?egoStart, ?obsBehaviorStart) AS ?start) (if(?egoEnd <= ?obsBehaviorEnd, ?egoEnd, ?obsBehaviorEnd) AS ?end) WHERE {
    {%s}
    GRAPH <%s> {
      ?scenario a zss:RawData ;
                rdfs:label "%s".
      ?scenario zss:actor ?ego .
      ?ego rdfs:label ?actor .
      ?sceneType a zss:SceneType ;
                 rdfs:label "SurroundingVehicleMotion" ;
                 zss:actor ?ego .
      ?sceneType zss:initial/zss:next* ?scene .
      ?scene a zss:Scene ;
             rdfs:label "SVM%s"  ;
             zss:start [ a zss:Time ;
                         rdfs:label ?obsBehaviorStart ] ;
             zss:end [ a zss:Time ;
                       rdfs:label ?obsBehaviorEnd ] .
    }

    FILTER(?actor != "ego" && ?egoStart <= ?obsBehaviorEnd && ?egoEnd >= ?obsBehaviorStart)
    
  }

""" % ('%s', GRAPH_ID, '%s', '%s')


class Sakura32Analyzer(BaseAnalyzer):
    def create_sparql_str(self, measurement, sakura32_tag_name):
        conditions = SAKURA32_CONDITIONS[sakura32_tag_name]
        sparql0 = roadgeometry_query % (measurement, conditions[0])
        sparql1 = ego_behavior_query % (sparql0, measurement, conditions[1])
        sparql_str = obs_behavior_query % (sparql1, measurement, conditions[2])

        return f'{PRIFIX_STR}{sparql_str}'

    def execute(self, imported_data_id=-1):
        """
        道路形状解析実行
        :param imported_data_id:
        :return:
        """
        # 走行データ管理DBからレコードを取得
        imported_data = self.get_imported_data(imported_data_id)
        measurement = imported_data.measurement

        # SAKURA32シナリオそれぞれの条件を満たすRDF情報を取得
        data_list = []
        for condition_name in SAKURA32_CONDITIONS:
            print('Getting {} Data ...'.format(condition_name))
            query_str = self.create_sparql_str(measurement, condition_name)
            result = RdfGraphAccessor.get_rdf_info(query_str)
            for data in result:
                data_list.append(
                    SceneData(data['start'], data['end'], condition_name))

        # RDFへ書き込み
        print('Writing RDF ...')
        creator = RdfGraphCreator("garden_ts", measurement)
        creator.build_tag(data_list)
        print('Done')


def execute(**kwargs):
    """

    :param kwargs:
    :return:
    """
    print('Record ID:{}'.format(kwargs['record_id']))
    record_id = int(kwargs['record_id'])
    analyzer = Sakura32Analyzer()
    analyzer.execute(imported_data_id=record_id)


if __name__ == '__main__':
    # for debug
    Sakura32Analyzer().execute(imported_data_id=2)
