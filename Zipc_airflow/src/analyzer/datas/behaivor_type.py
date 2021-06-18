from enum import Enum


class BehaviorType(Enum):
    LANE_CHANGE = "LaneChange"
    LANE_KEEP = "LaneKeep"
    ACCEL = "Accel"
    DECEL = "Decel"
    SYNC = "Sync"
