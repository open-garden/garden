
class Scanner(object):

    def __init__(self, road_data_dict, reach_road, reach_lane):
        """

        :param road_data_dict: MongoDBから取得したRoad, Lane情報
        :param reach_road: 他車両の走行するRoad
        :param reach_lane: 他車両の走行するLane
        """
        self.road_data_dict = road_data_dict
        self.reach_road = reach_road
        self.reach_lane = reach_lane

    def successor(self, target_road, target_lane, ego_road=None, ego_lane=None, index=0):
        """

        :param target_road:
        :param target_lane:
        :param ego_road:
        :param ego_lane:
        :param index:
        :return:
        """
        if target_road is None:  # 接続先の道路がない
            return None, None
        elif target_road == self.reach_road:  # 目標の道路に到達
            # Lane判定
            diff = int(self.reach_lane[2:]) - int(target_lane[2:])
            return index, diff
        elif target_road == ego_road:  # 再起し、走査開始Roadに到達
            return None, None
        if ego_road is None:  # 2周目以降で再起していないかの確認用
            ego_road = target_road
            ego_lane = target_lane
        road_data = self.road_data_dict.get(target_road)
        index += (road_data.get('index') - 1)  # Road結合部を考慮し、-1したIndexを合算
        lane_data = road_data.get(target_lane)
        successor = lane_data.get('successor')  # 次のRoadを走査
        return self.successor(successor['road'], successor['lane'], ego_road, ego_lane, index)

    def predecessor(self, target_road, target_lane, ego_road=None, ego_lane=None, index=0):
        """

        :param target_road:
        :param target_lane:
        :param ego_road:
        :param ego_lane:
        :param index:
        :return:
        """

        if target_road is None:  # 接続先の道路がない
            return None, None
        elif target_road == self.reach_road:  # 目標の道路に到達
            # Lane判定
            diff = int(self.reach_lane[2:]) - int(target_lane[2:])
            return index, diff
        elif target_road == ego_road:  # 再起し、走査開始Roadに到達
            return None, None
        if ego_road is None:  # 2周目以降で再起していないかの確認用
            ego_road = target_road
            ego_lane = target_lane
        road_data = self.road_data_dict.get(target_road)
        lane_data = road_data.get(target_lane)
        predecessor = lane_data.get('predecessor')  # 次のRoadを走査
        predecessor_road = predecessor.get('road')
        predecessor_lane = predecessor.get('lane')
        predecessor_road_data = self.road_data_dict[predecessor_road]
        index += (predecessor_road_data.get('index') - 1)  # Road結合部を考慮し、-1したIndexを合算
        return self.predecessor(predecessor_road, predecessor_lane, ego_road, ego_lane, index)
