class Waypoint(object):

    def __init__(self, road_name, lane_name, waypoint_index, lane_width,  point):
        """
        initialize
        :param road_name:
        :param lane_name:
        :param waypoint_index:
        :param lane_width:
        :param point:
        """
        self.road_name = road_name
        self.lane_name = lane_name
        self.waypoint_index = waypoint_index
        self.lane_width = lane_width
        self.x = point['x']
        self.y = point['y']
        self.z = point['z']

    def get_road_name(self):
        return self.road_name

    def get_lane_name(self):
        return self.lane_name

    def get_waypoint_index(self):
        return self.waypoint_index

    def get_lane_width(self):
        return self.lane_width

    def get_x(self):
        return self.x

    def get_y(self):
        return self.y

    def get_z(self):
        return self.z
