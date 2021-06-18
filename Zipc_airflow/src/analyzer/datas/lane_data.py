from datas.behaivor_type import BehaviorType
from datas.direction_type import DirectionType


class SceneData(object):

    def __init__(self, start=None, end=None, _type=None):
        self.start = start
        self.end = end
        self._type = _type

    def get_start(self):
        return self.start

    def set_start(self, start):
        self.start = start

    def get_end(self):
        return self.end

    def set_end(self, end):
        self.end = end

    def get_type(self):
        return self._type

    def equals(self, name):
        return self._type.value == name


class LaneChangeData(SceneData):

    def __init__(self, start=None, end=None):
        super().__init__(start, end, BehaviorType.LANE_CHANGE)


class LaneKeepData(SceneData):

    def __init__(self, start=None, end=None):
        super().__init__(start, end, BehaviorType.LANE_KEEP)


class FrontFrontData(SceneData):
    def __init__(self, start=None, end=None):
        super().__init__(start, end, DirectionType.FRONT_FRONT)


class FrontData(SceneData):
    def __init__(self, start=None, end=None):
        super().__init__(start, end, DirectionType.FRONT)


class LeftFrontData(SceneData):
    def __init__(self, start=None, end=None):
        super().__init__(start, end, DirectionType.LEFT_FRONT)


class RightFrontData(SceneData):
    def __init__(self, start=None, end=None):
        super().__init__(start, end, DirectionType.RIGHT_FRONT)


class LeftData(SceneData):
    def __init__(self, start=None, end=None):
        super().__init__(start, end, DirectionType.LEFT)


class RightData(SceneData):
    def __init__(self, start=None, end=None):
        super().__init__(start, end, DirectionType.RIGHT)


class BackData(SceneData):
    def __init__(self, start=None, end=None):
        super().__init__(start, end, DirectionType.BACK)


class LeftBackData(SceneData):
    def __init__(self, start=None, end=None):
        super().__init__(start, end, DirectionType.LEFT_BACK)


class RightBackData(SceneData):
    def __init__(self, start=None, end=None):
        super().__init__(start, end, DirectionType.RIGHT_BACK)
