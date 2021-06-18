# -*- coding: utf-8 -*-
import airflow
from airflow.models import DAG
from airflow.operators.bash_operator import BashOperator

args = {
    'owner': 'airflow',
    'start_date': airflow.utils.dates.days_ago(2),
    'provide_context': True,
}

dag = DAG(
    dag_id='CoordinatesConverter',
    default_args=args,
    schedule_interval='@once',
)

t1 = BashOperator(
    task_id='dump_arguments',
    bash_command="echo record_id: {{ dag_run.conf}}",
    default_args=args,
    dag=dag,
) 

t2 = BashOperator(
    task_id='coordinate_converter',
    bash_command="node /garden/nodejs/coordinates_convert/coordinate_converter.js -d {{ dag_run.conf['id'] }}",
    default_args=args,
    dag=dag,
) 

t1 >> t2
