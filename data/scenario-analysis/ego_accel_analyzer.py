from base_analayzer import BaseAnalyzer
import datetime


class EgoAccelAnalyzer(BaseAnalyzer):

    def convert_str_to_utc(self, time):
        """
        datatime -> utc
        :param time:
        :return:
        """
        # str_to_dt = datetime.datetime.strptime(time, '%Y-%m-%dT%H:%M:%S.%f%z')  # for python 3.7
        str_to_dt = datetime.datetime.strptime(time, '%Y-%m-%dT%H:%M:%S.%fZ')  # for python 3.6
        return str_to_dt.timestamp()

    def exists_fields(self, measurement):
        """
        カラム存在チェック
        :param measurement:
        :return:
        """
        return self.influxdb_accessor.exists_field(measurement, "ego_accel")

    def calc_accel(self, now_time, now_velocity, prev_time, prev_velocity):
        """
        車両の加速度を算出
        :param now_time:
        :param now_velocity:
        :param prev_time:
        :param prev_velocity:
        :return:
        """
        accel = (now_velocity - prev_velocity) / (now_time - prev_time)
        return round(accel, 3)

    def get_ego_velocity(self, measurement):
        """
        自車両の速度取得
        :param measurement:
        :return:
        """
        query = 'select ego_velocity from "{}"'.format(measurement)
        results = self.influxdb_accessor.query(query)
        return results

    def calc_ego_accel(self, ego_velocity_list):
        """
        車両の加速度算出
        :param ego_velocity_list:
        :return:
        """
        ego_accel_list = []
        prev_velocity = 0.0
        prev_utc = 0.0
        prev_accel = 0.0
        for num in range(0, len(ego_velocity_list)):
            data = ego_velocity_list[num]
            now_velocity = data.get("ego_velocity")
            now_time = data.get("time")
            now_utc = self.convert_str_to_utc(now_time)
            now_accel = self.calc_accel(now_utc, now_velocity, prev_utc, prev_velocity)
            accel = round((now_accel + prev_accel) / 2.0, 3)
            # set prev data
            prev_velocity = now_velocity
            prev_utc = now_utc
            prev_accel = now_accel
            # append data
            ego_accel_list.append({"time": now_time, "ego_accel": accel})
        return ego_accel_list

    def create_points(self, measurement, ego_accel_list):
        """
        書き込み用フォーマット作成
        :param measurement:
        :param ego_accel_list:
        :return:
        """
        data_list = []
        for data in ego_accel_list:
            vehicle_accel = 'ego_accel'
            _format = {'fields': {vehicle_accel: data.get(vehicle_accel),
                                  },
                       'measurement': measurement,
                       'time': data.get('time')}
            data_list.append(_format)
        return data_list

    def write_data(self, measurement, ego_accel_list):
        """
        データ書き込み
        :param measurement:
        :param ego_accel_list:
        :return:
        """
        points = self.create_points(measurement, ego_accel_list)
        self.influxdb_accessor.write(points)

    def execute(self, imported_data_id=-1):
        """
        レーン解析実行
        :param imported_data_id:
        :return:
        """
        # 走行データ管理DBからレコードを取得
        imported_data = self.get_imported_data(imported_data_id)
        measurement = imported_data.measurement

        # すでに[ego_accel]が存在する場合は、処理しない
        if self.exists_fields(measurement):
            return

        # 自車両の速度[ego_velocity]情報を取得
        ego_velocity_list = self.get_ego_velocity(measurement)

        # 自車両の加速度を算出
        ego_accel_list = self.calc_ego_accel(ego_velocity_list)

        # InfluxDBに書き込み
        self.write_data(measurement, ego_accel_list)


def execute(**kwargs):
    """
    Airflowのpython_operatorからcall
    :param kwargs:
    :return:
    """
    print('Record ID:{}'.format(kwargs['record_id']))
    record_id = int(kwargs['record_id'])
    analyzer = EgoAccelAnalyzer()
    analyzer.execute(imported_data_id=record_id)


if __name__ == '__main__':
    # for debug
    EgoAccelAnalyzer().execute(imported_data_id=2)
