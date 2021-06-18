from utils.influxdb_accessor import InfluxDBAccessor
from utils.mongodb_accesspr import MongoDBAccessor
from utils.postgresql_accessor import PostgreSQLAccessor


class BaseAnalyzer(object):

    def __init__(self):
        """
        initialize
        """
        self.postgres_accessor = PostgreSQLAccessor()
        self.influxdb_accessor = InfluxDBAccessor()
        self.mongo_accessor = MongoDBAccessor()

    def get_imported_data(self, imported_data_id=-1):
        """
        走行データ管理DBから指定されたIDのレコードを取得する
        :param imported_data_id:
        :return:
        """
        imported_data_list = PostgreSQLAccessor.get_imported_data_record(imported_data_id)
        if len(imported_data_list) != 1:
            return
        imported_data = imported_data_list[0]
        return imported_data
