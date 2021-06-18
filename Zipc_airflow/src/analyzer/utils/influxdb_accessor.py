# InfluxDB
from influxdb import InfluxDBClient
from utils.constants import *


class InfluxDBAccessor(object):

    def __init__(self):
        """
        Initialize
        """
        self.client = InfluxDBClient(host=IN_HOST, port=IN_PORT, database=IN_DB)
        self.vehicles = []
        self.fields = []

    def query(self, query):
        """
        InfluxDBに対し、指定したクエリを実行する
        :param query:
        :return:
        """
        print("Query: {0}".format(query))
        results = self.client.query(query=query)
        print("Result: {0}".format(results))
        point_list = list(results.get_points())
        if len(point_list) > 0:
            return list(results.get_points())
        return None

    def get_measurement(self, measurement):
        """
        指定したメジャーメントを取得する
        :param measurement:
        :return:
        """
        query = 'select * from "{}"'.format(measurement)
        return self.query(query)

    def get_fields(self, measurement):
        """
        指定したメジャーメントのフィールドを取得する
        :param measurement:
        :return:
        """
        if len(self.fields) == 0:
            # results = self.query("select * from /.*/ limit 1")
            results = self.query("select * from \"{}\" limit 1".format(measurement))
            if results is not None:
                result = results[0]
                self.fields.extend(result.keys())
        return self.fields

    def exists_field(self, measurement, field):
        """
        引数で指定したフィールドが存在するか確認
        :param measurement:
        :param field:
        :return:
        """
        fields = self.get_fields(measurement)
        return field in fields

    def get_vehicles(self, measurement):
        """
        車両名の一覧を取得する
        :param measurement:
        :return:
        """
        if len(self.vehicles) == 0:
            fields = self.get_fields(measurement)
            for field in fields:
                split = field.split("_")
                if len(split) < 2:
                    continue
                vehicle = split[0]
                if vehicle not in str(self.vehicles):
                    self.vehicles.append(vehicle)
        print("Vehicles: {0}".format(self.vehicles))
        return self.vehicles

    def write(self, data_):
        """
        データの書き込み
        format:
        data_ = [{'fields': {'metric1': 1.0, 'metric2': -1},
                  'measurement': 'garden_sim',
                  'time': datetime.datetime.utcnow(),
                  'tags': {'cat1': 'aaa'}},
                 {'fields': {'metric1': 2.0, 'metric2': -2},
                  'measurement': 'garden_sim',
                  'time': datetime.datetime.utcnow(),
                  'tags': {'cat1': 'aaa'}},
                 {'fields': {'metric1': 3.0, 'metric2': -3},
                  'measurement': 'garden_sim',
                  'time': datetime.datetime.utcnow(),
                  'tags': {'cat1': 'aaa'}}]
        :param data_:
        :return:
        """
        return self.client.write_points(data_)
