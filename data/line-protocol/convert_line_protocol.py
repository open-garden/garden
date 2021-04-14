import pandas as pd
import time

NAME = "01Kouhoku-Inter.scenario"
INPUT_EXTENSION = ".csv"
OUTPUT_EXTENSION = ".txt"


class LinePortocolConverter(object):

    def __init__(self):
        self.time = round(time.time(), 3)

    def road_csv(self):
        df = pd.read_csv("data/{}{}".format(NAME, INPUT_EXTENSION))

        lines = []
        for index, row in df.iterrows():
            fields = ""
            for column_num in range(1, len(df.columns)):
                if fields:
                    fields += ","
                value = row[column_num]
                if type(value) is str:
                    fields += "{}={}".format(df.columns[column_num], "\"{}\"".format(row[column_num]))
                else:
                    fields += "{}={}".format(df.columns[column_num], row[column_num])
            line = "{}{} {} {}".format(NAME, INPUT_EXTENSION, fields, str(int((self.time + row[0]) * 1000) * 1000000))
            lines.append(line)
        return lines

    def write(self, line):
        thefile = open('output/{}{}'.format(NAME, OUTPUT_EXTENSION), 'w', newline="\n")
        for item in lines:
            thefile.write("%s\n" % item)


if __name__ == '__main__':
    app = LinePortocolConverter()
    lines = app.road_csv()
    app.write(lines)
