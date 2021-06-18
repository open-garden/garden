from enum import Enum


class SceneType(Enum):
    LANE = "Lane"
    BEHAVIOR_LANE_MOTION = "Behavior_Lane_Motion"
    BEHAVIOR_VEHICLE_MOTION = "Behavior_Vehicle_Motion"
    DIRECTION = "Direction"
    ROADGEOMETRY = "RoadGeometry"
    SURROUNDING_VEHICLE_MOTION = "SurroundingVehicleMotion"
