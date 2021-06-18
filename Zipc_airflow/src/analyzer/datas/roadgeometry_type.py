from aenum import MultiValueEnum


class RoadGeometryType(MultiValueEnum):
    MAINROADWAY = "Mainroadway", "main roadway"
    DEPARTURE = "Departure", "departure zone"
    MERGE = "Merge", "merge zone"
    RAMP = "Ramp", "ramp"