from base_analayzer import BaseAnalyzer
from datas.lane_data import SceneData
from utils.rdf_graph_creator import RdfGraphCreator
from datas.behaivor_type import BehaviorType
from datas.scene_type import SceneType

import datetime
import utils.utility as utility

LOWER = 1.0
UPPER = 1.0


class OtherVehicleBehaviorAnalyzer(BaseAnalyzer):

    def get_vehicle_data(self, measurement, vehicles):
        """

        :param measurement:
        :param vehicles:
        :return:
        """
        ego_velocity_list = []
        other_xy_dict = {}
        for vehicle in vehicles:
            if vehicle == "ego":
                query = 'select {}_velocity from "{}"'.format(vehicle, measurement)
                ego_velocity_list = self.influxdb_accessor.query(query)
            else:
                query = 'select {}_x, {}_y from "{}"'.format(vehicle, vehicle, measurement)
                other_xy_dict[vehicle] = self.influxdb_accessor.query(query)
        return ego_velocity_list, other_xy_dict

    def convert_str_to_utc(self, time):
        """

        :param time:
        :return:
        """
        # str_to_dt = datetime.datetime.strptime(time, '%Y-%m-%dT%H:%M:%S.%f%z')
        str_to_dt = datetime.datetime.strptime(time, '%Y-%m-%dT%H:%M:%S.%fZ')
        return str_to_dt.timestamp()

    def calc_velocity(self, vehicle_data_dict):
        vehicle_velocity_dict = {}
        for vehicle, datas in vehicle_data_dict.items():
            velocity_dict = {}
            for num in range(1, len(datas)):
                prev = datas[num-1]
                prev_time = self.convert_str_to_utc(prev["time"])
                prev_x = prev[vehicle + "_x"]
                prev_y = prev[vehicle + "_y"]
                now = datas[num]
                now_time = self.convert_str_to_utc(now["time"])
                now_x = now[vehicle + "_x"]
                now_y = now[vehicle + "_y"]
                velocity = utility.calc_velocity(now_time, now_x, now_y, prev_time, prev_x, prev_y)

                if num == 1:
                    velocity_dict.update({prev["time"]: velocity})
                velocity_dict.update({now["time"]: velocity})
            vehicle_velocity_dict[vehicle] = velocity_dict
        return vehicle_velocity_dict

    def create_points(self, measurement, vehicle, velocity_data):
        """

        :param measurement:
        :param vehicle:
        :param velocity_data:
        :return:
        """
        datas = []
        for time, velocity in velocity_data.items():
            _format = {'fields': {vehicle + '_velocity': velocity},
                       'measurement': measurement,
                       'time': time}
            datas.append(_format)
        return datas

    def write_data(self, measurement, other_velocity_dict):
        """

        :param measurement:
        :param other_velocity_dict:
        :return:
        """
        for vehicle, velocity_data in other_velocity_dict.items():
            points = self.create_points(measurement, vehicle, velocity_data)
            self.influxdb_accessor.write(points)

    def vehicle_behavior_classification(self, ego_velocity_list, velocity_dict):
        """
        
        :param ego_velocity_list: 
        :param velocity_dict: 
        :return: 
        """
        behavior_data_list = []

        behavior_data = None
        prev_time = None
        for ego_data in ego_velocity_list:
            time = ego_data["time"]
            ego_velocity = ego_data["ego_velocity"]
            other_velocity = velocity_dict[time]

            _type = None
            if ((ego_velocity - LOWER) < other_velocity) and (other_velocity < (ego_velocity + UPPER)):
                _type = BehaviorType.SYNC
            elif ego_velocity < other_velocity:
                _type = BehaviorType.ACCEL
            elif ego_velocity > other_velocity:
                _type = BehaviorType.DECEL

            if behavior_data is None:
                behavior_data = SceneData(start=time, _type=_type)
            elif behavior_data.get_type() is not _type:
                behavior_data.set_end(prev_time)
                behavior_data_list.append(behavior_data)
                behavior_data = SceneData(start=time, _type=_type)
            prev_time = time

        # 後処理（最後のデータにEndを設定し、リストに追加）
        behavior_data.set_end(prev_time)
        behavior_data_list.append(behavior_data)
        return behavior_data_list

    def get_other_vehicle_behavior(self, ego_velocity_list, other_velocity_dict):
        """

        :param ego_velocity_list:
        :param other_velocity_dict:
        :return:
        """
        behavior_data_dict = {}
        for vehicle, velocity_dict in other_velocity_dict.items():
            behavior_data_dict[vehicle] = self.vehicle_behavior_classification(ego_velocity_list, velocity_dict)
        return behavior_data_dict

    def execute(self, imported_data_id=-1):
        """
        他車両の振る舞いを解析する
        ・他車両の速度
        ・Accel, Decel, Sync
        :param imported_data_id:
        :return:
        """
        # 走行データ管理DBからレコードを取得
        imported_data = self.get_imported_data(imported_data_id)
        measurement = imported_data.measurement

        # 車両取得
        vehicles = self.influxdb_accessor.get_vehicles(measurement)

        # 他車両ごとのx, y情報
        ego_velocity_list, other_xy_dict = self.get_vehicle_data(measurement, vehicles)

        # 他車両の速度を計算
        other_velocity_dict = self.calc_velocity(other_xy_dict)

        # 他車両の振る舞いを分類（Accel, Decel, Sync）
        behavior_data_dict = self.get_other_vehicle_behavior(ego_velocity_list, other_velocity_dict)

        # 他車両の速度書き込み
        self.write_data(measurement, other_velocity_dict)

        # RDFの作成と保存
        creator = RdfGraphCreator("garden_ts", measurement)
        creator.build_behavior(behavior_data_dict, SceneType.BEHAVIOR_VEHICLE_MOTION, "ego")


def execute(**kwargs):
    """

    :param kwargs:
    :return:
    """
    print('Record ID:{}'.format(kwargs['record_id']))
    record_id = int(kwargs['record_id'])
    analyzer = OtherVehicleBehaviorAnalyzer()
    analyzer.execute(imported_data_id=record_id)


if __name__ == '__main__':
    # for debug
    OtherVehicleBehaviorAnalyzer().execute(imported_data_id=2)

