import os
import math


def get_env(varname, default):
    """

    :param varname:
    :param default:
    :return:
    """
    return str(os.getenv(varname, default))


def set_env(varname, value):
    """

    :param varname:
    :param value:
    :return:
    """
    os.environ[varname] = value
    # os.putenv(varname, value)


def circle(x, y, a, b, width):
    """
    widthで指定された直径の中に含まれているかを判定
    :param x:
    :param y:
    :param a:
    :param b:
    :param width:
    :return:
    """
    _x = round(((x - a) ** 2), 3)
    _y = round(((y - b) ** 2), 3)
    _r = round(((width/2) ** 2), 3)
    if (_x + _y) <= _r:
        return _r - (_x + _y)
    return None


def calc_distance(x1, y1, x2, y2):
    """
    2点間の距離を算出
    :param x1:
    :param y1:
    :param x2:
    :param y2:
    :return:
    """
    return math.sqrt((x1 - x2) ** 2 + (y1 - y2) ** 2)


def calc_velocity(now_time, now_x, now_y, prev_time, prev_x, prev_y):
    """
    車両の速度を算出
    :param now_time:
    :param now_x:
    :param now_y:
    :param prev_time:
    :param prev_x:
    :param prev_y:
    :return:
    """
    time = calc_distance(now_x, prev_x, now_y, prev_y) / (now_time - prev_time)
    return round(time, 3)
