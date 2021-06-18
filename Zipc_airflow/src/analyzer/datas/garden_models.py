from peewee import *
from utils.constants import *

database = PostgresqlDatabase(PS_DB, **{'host': PS_HOST, 'port': PS_PORT, 'user': PS_USER, 'password': PS_PASSWORD})

class UnknownField(object):
    def __init__(self, *_, **__): pass

class BaseModel(Model):
    class Meta:
        database = database

class Bpbehaviorpatterndao(BaseModel):
    fileid = CharField(null=True)
    id = BigAutoField()
    patternid = BigIntegerField(null=True)
    projectid = BigIntegerField(null=True)

    class Meta:
        table_name = 'bpbehaviorpatterndao'

class Bpstatemachinedao(BaseModel):
    fileid = CharField(null=True)
    fsmid = CharField(null=True)
    id = BigAutoField()
    name = CharField(null=True)
    projectid = BigIntegerField(null=True)

    class Meta:
        table_name = 'bpstatemachinedao'

class Bpbehaviordao(BaseModel):
    bpid = ForeignKeyField(column_name='bpid', field='id', model=Bpbehaviorpatterndao, null=True)
    fileid = CharField(null=True)
    id = BigAutoField()
    projectid = BigIntegerField(null=True)
    stmid = ForeignKeyField(column_name='stmid', field='id', model=Bpstatemachinedao, null=True)

    class Meta:
        table_name = 'bpbehaviordao'

class Bpeventdao(BaseModel):
    fileid = CharField(null=True)
    id = BigAutoField()
    name = CharField(null=True)
    projectid = BigIntegerField(null=True)
    stmid = ForeignKeyField(column_name='stmid', field='id', model=Bpstatemachinedao, null=True)

    class Meta:
        table_name = 'bpeventdao'

class Bpstatedao(BaseModel):
    fileid = CharField(null=True)
    id = BigAutoField()
    name = CharField(null=True)
    projectid = BigIntegerField(null=True)
    stmid = ForeignKeyField(column_name='stmid', field='id', model=Bpstatemachinedao, null=True)

    class Meta:
        table_name = 'bpstatedao'

class Bpspecdao(BaseModel):
    bpid = ForeignKeyField(column_name='bpid', field='id', model=Bpbehaviorpatterndao, null=True)
    eventid = ForeignKeyField(column_name='eventid', field='id', model=Bpeventdao, null=True)
    fileid = CharField(null=True)
    id = BigAutoField()
    projectid = BigIntegerField(null=True)
    stateid = ForeignKeyField(column_name='stateid', field='id', model=Bpstatedao, null=True)

    class Meta:
        table_name = 'bpspecdao'

class Bpstepdao(BaseModel):
    behaviorid = ForeignKeyField(column_name='behaviorid', field='id', model=Bpbehaviordao, null=True)
    eventid = ForeignKeyField(column_name='eventid', field='id', model=Bpeventdao, null=True)
    fileid = CharField(null=True)
    id = BigAutoField()
    projectid = BigIntegerField(null=True)
    stateid = ForeignKeyField(column_name='stateid', field='id', model=Bpstatedao, null=True)

    class Meta:
        table_name = 'bpstepdao'

class Commitfile(BaseModel):
    content = BlobField(null=True)
    extension = CharField(null=True)
    fileid = BigIntegerField(null=True)
    fullpath = CharField(null=True)
    hash = CharField(null=True)
    id = BigAutoField()
    name = CharField(null=True)
    projectid = BigIntegerField(null=True)
    updatetime = DateTimeField(null=True)
    updateuser = BigIntegerField(null=True)

    class Meta:
        table_name = 'commitfile'

class Commithistory(BaseModel):
    comment = CharField(null=True)
    committime = DateTimeField(null=True)
    commituser = BigIntegerField(null=True)
    id = BigAutoField()
    projectid = BigIntegerField(null=True)

    class Meta:
        table_name = 'commithistory'

class Commitfilehistory(BaseModel):
    commitfileid = ForeignKeyField(column_name='commitfileid', field='id', model=Commitfile, null=True)
    commithistoryid = ForeignKeyField(column_name='commithistoryid', field='id', model=Commithistory, null=True)
    id = BigAutoField()

    class Meta:
        table_name = 'commitfilehistory'

class Company(BaseModel):
    id = BigAutoField()
    name = CharField(null=True)

    class Meta:
        table_name = 'company'

class Concretescenario(BaseModel):
    concrete_scenario_id = BigIntegerField(null=True)
    data = CharField(null=True)
    id = BigAutoField()
    logical_scenario_id = BigIntegerField(null=True)
    vehicle_id = BigIntegerField(null=True)
    waypoints_id = BigIntegerField(null=True)

    class Meta:
        table_name = 'concretescenario'

class Cscspatterndao(BaseModel):
    csc = TextField(null=True)
    cscspatternid = BigIntegerField(null=True)
    fileuuid = CharField(null=True)
    id = BigAutoField()
    pattern = CharField(null=True)
    projectid = BigIntegerField(null=True)
    scspatternid = BigIntegerField(null=True)

    class Meta:
        table_name = 'cscspatterndao'
        indexes = (
            (('fileuuid', 'projectid'), False),
        )

class Directory(BaseModel):
    deleteflg = BooleanField(null=True)
    fullpath = CharField(null=True)
    id = BigAutoField()
    name = CharField(null=True)
    parentdirid = ForeignKeyField(column_name='parentdirid', field='id', model='self', null=True)
    projectid = BigIntegerField(null=True)

    class Meta:
        table_name = 'directory'

class Drivingdata(BaseModel):
    id = BigAutoField()
    sourcetype = CharField(null=True)
    sourceuri = BlobField(null=True)

    class Meta:
        table_name = 'drivingdata'

class File(BaseModel):
    commitflg = BooleanField(null=True)
    content = BlobField(null=True)
    createtime = DateTimeField(null=True)
    createuser = BigIntegerField(null=True)
    deleteflg = BooleanField(null=True)
    extension = CharField(null=True)
    fullpath = CharField(null=True)
    hash = CharField(null=True)
    id = BigAutoField()
    name = CharField(null=True)
    parentdirid = ForeignKeyField(column_name='parentdirid', field='id', model=Directory, null=True)
    projectid = BigIntegerField(index=True, null=True)
    updatetime = DateTimeField(null=True)
    updateuser = BigIntegerField(null=True)
    uuid = CharField(null=True)

    class Meta:
        table_name = 'file'

class Filehistory(BaseModel):
    content = BlobField(null=True)
    extension = CharField(null=True)
    fileid = ForeignKeyField(column_name='fileid', field='id', model=File, null=True)
    fullpath = CharField(null=True)
    hash = CharField(null=True)
    id = BigAutoField()
    name = CharField(null=True)
    projectid = BigIntegerField(null=True)
    updatetime = DateTimeField(null=True)
    updateuser = BigIntegerField(null=True)

    class Meta:
        table_name = 'filehistory'

class Project(BaseModel):
    allvariablesname = CharField(null=True)
    description = TextField(null=True)
    encodingtype = CharField(null=True)
    id = BigAutoField()
    image = BlobField(null=True)
    imageheight = IntegerField(null=True)
    imagename = CharField(null=True)
    imagewidth = IntegerField(null=True)
    name = CharField(null=True)
    rootdirid = ForeignKeyField(column_name='rootdirid', field='id', model=Directory, null=True)

    class Meta:
        table_name = 'project'

class Filereferences(BaseModel):
    extension = CharField(null=True)
    fileid = ForeignKeyField(column_name='fileid', field='id', model=File, null=True)
    fileuuid = CharField(null=True)
    hash = CharField(null=True)
    id = BigAutoField()
    projectid = ForeignKeyField(column_name='projectid', field='id', model=Project, null=True)
    refextension = CharField(null=True)
    refid = ForeignKeyField(backref='file_refid_set', column_name='refid', field='id', model=File, null=True)
    refprojectid = ForeignKeyField(backref='project_refprojectid_set', column_name='refprojectid', field='id', model=Project, null=True)
    refuuid = CharField(null=True)

    class Meta:
        table_name = 'filereferences'

class Importeddata(BaseModel):
    drivingdataid = BigIntegerField(null=True)
    id = BigAutoField()
    lat = DoubleField(null=True)
    latmax = DoubleField(null=True)
    latmin = DoubleField(null=True)
    lon = DoubleField(null=True)
    lonmax = DoubleField(null=True)
    lonmin = DoubleField(null=True)
    mapid = CharField(null=True)
    measurement = CharField(null=True)
    status = IntegerField(null=True)

    class Meta:
        table_name = 'importeddata'

class Job(BaseModel):
    id = BigAutoField()
    inputfile = BlobField(null=True)
    inputfileid = BigIntegerField(null=True)
    message = CharField(null=True)
    outputfileid = BigIntegerField(null=True)
    projectid = BigIntegerField(null=True)
    status = IntegerField(null=True)
    stepprogressmessage = CharField(null=True)
    type = IntegerField(null=True)

    class Meta:
        table_name = 'job'

class Jobstatus(BaseModel):
    fileid = ForeignKeyField(column_name='fileid', field='id', model=File, null=True)
    hash = CharField(null=True)
    id = BigAutoField()
    infomation = CharField(null=True)
    status = IntegerField(null=True)

    class Meta:
        table_name = 'jobstatus'

class Logicalscenario(BaseModel):
    data = CharField(null=True)
    id = BigAutoField()
    logical_scenario_id = BigIntegerField(null=True)
    vehicle_model_id = BigIntegerField(null=True)
    waypoints_id = BigIntegerField(null=True)

    class Meta:
        table_name = 'logicalscenario'

class Map(BaseModel):
    data = CharField(null=True)
    id = BigAutoField()
    map_id = BigIntegerField(null=True)

    class Meta:
        table_name = 'map'

class Usermaster(BaseModel):
    companyid = ForeignKeyField(column_name='companyid', field='id', model=Company, null=True)
    displayname = CharField(null=True)
    editextensions = CharField(null=True)
    id = BigAutoField()
    info1 = CharField(null=True)
    info2 = CharField(null=True)
    mail = CharField(null=True)
    name = CharField(null=True)
    password = CharField(null=True)
    role = IntegerField(null=True)
    verifystatus = BooleanField(null=True)

    class Meta:
        table_name = 'usermaster'

class Patternelement(BaseModel):
    createtime = DateTimeField(null=True)
    createuser = ForeignKeyField(column_name='createuser', field='id', model=Usermaster, null=True)
    id = BigAutoField()
    name = CharField(null=True)
    project = ForeignKeyField(column_name='project', field='id', model=Project, null=True)
    updatetime = DateTimeField(null=True)
    updateuser = ForeignKeyField(backref='usermaster_updateuser_set', column_name='updateuser', field='id', model=Usermaster, null=True)
    value = TextField(null=True)

    class Meta:
        table_name = 'patternelement'

class Projectusers(BaseModel):
    projectid = ForeignKeyField(column_name='projectid', field='id', model=Project)
    role = IntegerField(null=True)
    userid = ForeignKeyField(column_name='userid', field='id', model=Usermaster)

    class Meta:
        table_name = 'projectusers'
        indexes = (
            (('projectid', 'userid'), True),
        )
        primary_key = CompositeKey('projectid', 'userid')

class Referencescenario(BaseModel):
    db_id = BigIntegerField(null=True)
    id = BigAutoField()
    refer_db = CharField(null=True)

    class Meta:
        table_name = 'referencescenario'

class Scenariotag(BaseModel):
    id = BigAutoField()
    tag_name = CharField(null=True)

    class Meta:
        table_name = 'scenariotag'

class Scspatterndao(BaseModel):
    cscfilename = CharField(null=True)
    cscuuid = CharField(null=True)
    fileuuid = CharField(null=True)
    id = BigAutoField()
    lsc = TextField(null=True)
    projectid = BigIntegerField(null=True)
    scspatternid = BigIntegerField(null=True)

    class Meta:
        table_name = 'scspatterndao'
        indexes = (
            (('fileuuid', 'projectid'), False),
        )

class Scspatternreferencedao(BaseModel):
    fileuuid = CharField(null=True)
    id = BigAutoField()
    patternid = CharField(null=True)
    projectid = BigIntegerField(null=True)
    scsid = ForeignKeyField(column_name='scsid', field='id', model=Scspatterndao, null=True)

    class Meta:
        table_name = 'scspatternreferencedao'

class Simulationlog(BaseModel):
    concrete_scenario_id = BigIntegerField(null=True)
    data = CharField(null=True)
    id = BigAutoField()
    log_id = BigIntegerField(null=True)

    class Meta:
        table_name = 'simulationlog'

class Tpelementdao(BaseModel):
    fileid = CharField(null=True)
    id = BigAutoField()
    projectid = BigIntegerField(null=True)
    simplepath = CharField(null=True)
    value = CharField(null=True)

    class Meta:
        table_name = 'tpelementdao'

class Tptsdpatterndao(BaseModel):
    elementid = ForeignKeyField(column_name='elementid', field='id', model=Tpelementdao, null=True)
    fileid = CharField(null=True)
    id = BigAutoField()
    patternid = BigIntegerField(null=True)
    projectid = BigIntegerField(null=True)

    class Meta:
        table_name = 'tptsdpatterndao'

class Userproperty(BaseModel):
    createtime = DateTimeField(null=True)
    createuser = ForeignKeyField(column_name='createuser', field='id', model=Usermaster, null=True)
    id = BigAutoField()
    initialvalue = CharField(null=True)
    project = ForeignKeyField(column_name='project', field='id', model=Project, null=True)
    updatetime = DateTimeField(null=True)
    updateuser = ForeignKeyField(backref='usermaster_updateuser_set', column_name='updateuser', field='id', model=Usermaster, null=True)
    userproperty = CharField(null=True)

    class Meta:
        table_name = 'userproperty'

class Verificationtoken(BaseModel):
    expirydate = DateTimeField(null=True)
    id = BigAutoField()
    token = CharField(null=True)
    user = ForeignKeyField(column_name='user_id', field='id', model=Usermaster, null=True)

    class Meta:
        table_name = 'verificationtoken'

class Waypoints(BaseModel):
    data = CharField(null=True)
    id = BigAutoField()
    map_id = BigIntegerField(null=True)
    waypoints_id = BigIntegerField(null=True)

    class Meta:
        table_name = 'waypoints'

