from bson import ObjectId
from pymongo import MongoClient
from utils.constants import *


class MongoDBAccessor(object):

    def __init__(self):
        """

        """
        self.client = MongoClient(host=MG_HOST, port=MG_PORT)
        self.db = self.client[MG_DB]

    def get_db(self):
        """

        :return:
        """
        return self.db

    def get_collections(self, collection_name):
        """

        :param collection_name:
        :return:
        """
        collection = self.db.get_collection(collection_name)
        return collection

    def get_mapdatas(self, _filter=None):
        """

        :param _filter:
        :return:
        """
        collection = self.get_collections('mapdatas')
        return collection.find(filter=_filter)

    def get_waypoints(self, _filter=None):
        """

        :param _filter:
        :return:
        """
        collection = self.get_collections('waypoints')
        return collection.find(filter=_filter)

    def get_waypoints_from_map_id(self, gid):
        """

        :param gid:
        :return:
        """
        # mapdatasからmap情報を取得
        #map_data = self.db.mapdatas.find_one({'_id': ObjectId(map_id)})
        #gid = map_data['gid']

        # waypointsから対象のwaypointを取得
        waypoint_data = self.db.waypoints.find_one({'gid': gid})

        return waypoint_data
