from utils.utility import get_env

# PostgreSQL
PS_HOST = get_env('PS_HOST', 'localhost')
PS_PORT = int(get_env('PS_PORT', '5432'))
PS_USER = get_env('PS_USER', 'postgres')
PS_PASSWORD = get_env('PS_PASSWORD', 'postgres')
PS_DB = get_env('PS_DB', 'garden')

# MongoDB
MG_HOST = get_env('MG_HOST', 'localhost')
MG_PORT = int(get_env('MG_PORT', '27017'))
# MG_USER = get_env('MG_USER', 'postgres')
# MG_PASSWORD = get_env('MG_PASSWORD', 'postgres')
MG_DB = get_env('MG_DB', 'garden_doc')

# Apache Jena FUSEKI
JF_HOST = get_env('JF_HOST', 'http://localhost')
JF_PORT = int(get_env('JF_PORT', '3030'))
# JF_USER = get_env('JF_USER', 'admin')
# JF_PASSWORD = get_env('JF_PASSWORD', 'admin')
JF_DATASET = get_env('JF_DATASET', 'garden_rdf')

# InfluxDB
IN_HOST = get_env('IN_HOST', 'localhost')
IN_PORT = int(get_env('IN_PORT', '8086'))
# IN_USER = get_env('IN_USER', 'admin')
# IN_PASSWORD = get_env('IN_PASSWORD', 'admin')
IN_DB = get_env('IN_DB', 'garden_ts')

# -- for debug --
# # PostgreSQL
# PS_HOST = get_env('PS_HOST', '172.16.11.43')
# PS_PORT = int(get_env('PS_PORT', '5432'))
# PS_USER = get_env('PS_USER', 'postgres')
# PS_PASSWORD = get_env('PS_PASSWORD', 'postgres')
# PS_DB = get_env('PS_DB', 'garden')

# # MongoDB
# MG_HOST = get_env('MG_HOST', '172.16.11.43')
# MG_PORT = int(get_env('MG_PORT', '27017'))
# MG_DB = get_env('MG_DB', 'garden_doc')

# # Apache Jena FUSEKI
# JF_HOST = get_env('JF_HOST', 'http://172.16.11.43')
# JF_PORT = int(get_env('JF_PORT', '3030'))
# JF_DATASET = get_env('JF_DATASET', 'garden')

# # InfluxDB
# IN_HOST = get_env('IN_HOST', '172.16.11.43')
# IN_PORT = int(get_env('IN_PORT', '8086'))
# IN_DB = get_env('IN_DB', 'garden_ts')
