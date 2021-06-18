from enum import Enum


class SurroundingVehicleMotionType(Enum):
    CUTIN = "SVMCutin"
    CUTOUT = "SVMCutout"
    ACCEL = "SVMAccel"
    DECEL = "SVMDecel"
    SYNC = "SVMSync"
