from SPARQLWrapper import SPARQLWrapper, JSON
from utils.constants import *


ENDPOINT = f'{JF_HOST}:{JF_PORT}/{JF_DATASET}/sparql'
GRAPH_ID = "http://www.zipc.com/model/scenario"
PRIFIX_STR = """
prefix : <http://www.zipc.com/model/scenario#>
prefix owl: <http://www.w3.org/2002/07/owl#>
prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
prefix xml: <http://www.w3.org/XML/1998/namespace>
prefix xsd: <http://www.w3.org/2001/XMLSchema#>
prefix zss: <http://www.zipc.com/schema/scenario-schema#>
prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
base <http://www.zipc.com/model/scenario>
"""


class RdfGraphAccessor(object):

  @staticmethod
  def get_rdf_info(query):
    """

    :param _id:
    :return:
    """
    sparql = SPARQLWrapper(ENDPOINT)

    sparql.setQuery(query)
    sparql.setReturnFormat(JSON)
    results = sparql.query().convert()
    formatted = []
    keys = results['head']['vars']
    for item in results["results"]["bindings"]:
      record = {}
      for key in keys:
        record[key] = item[key]['value']
      formatted.append(record)
    return formatted
  

    # print('---------------------------')
  
    # for result in results["results"]["bindings"]:
    #     print('%s: %s' % (result["label"]["xml:lang"], result["label"]["value"]))
    # print(results)
  @staticmethod
  def get_scene_periods(measurement, sakura32_name):
    """

    :param _id:
    :return:
    """
    sparql = SPARQLWrapper(ENDPOINT)
    sparql.setQuery(create_sparql_str(measurement, sakura32_name))
    sparql.setReturnFormat(JSON)
    results = sparql.query().convert()
  

    # print('---------------------------')
  
    #     print('%s: %s' % (result["label"]["xml:lang"], result["label"]["value"]))
    # print(results)
    return results
