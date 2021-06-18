#Postgresql
from datas.garden_models import Importeddata


class PostgreSQLAccessor(object):

    @staticmethod
    def get_imported_data_record(_id):
        """

        :param _id:
        :return:
        """
        return [ids for ids in Importeddata.select(Importeddata).where(Importeddata.id == _id)]
