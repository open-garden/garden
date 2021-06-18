# -*- coding: utf-8 -*-
from airflow.api.common.experimental.trigger_dag import trigger_dag
from airflow.hooks.postgres_hook import PostgresHook
from airflow.models import DAG
from airflow.operators.dummy_operator import DummyOperator
from airflow.operators.python_operator import PythonOperator
from airflow.utils import timezone

import airflow
import json


def get_records():
    pg_hook = PostgresHook(postgres_conn_id='postgresql_garden')
    pg_records = pg_hook.get_records('select * from importeddata where status=7')
    print(pg_records)
    records = []
    for record in pg_records:
        records.append({'id': record[0], 'measurement': record[2]})
    return records


def trigger(**kwargs):
    execution_date = kwargs['ti'].execution_date.isoformat()
    for record in get_records():
        print(record)
        run_id = 'trig__{}_{}_{}'.format(record['id'], record['measurement'], execution_date)
        trigger_dag(
            dag_id='GardenAnalyzer',
            run_id=run_id,
            conf=json.dumps(record),
            execution_date=None,
            replace_microseconds=False
        )


#
# TriggerするDAGの定義
#
args = {
    'owner': 'airflow',
    'start_date': airflow.utils.dates.days_ago(2),
    'provide_context': True,
}

dag = DAG(
    dag_id='GardenAnalyzerTrigger',
    default_args=args,
    schedule_interval="@daily",
)

t1 = DummyOperator(
    task_id='start',
    default_args=args,
    dag=dag,
)

t2 = PythonOperator(
    task_id='trigger_analyzer',
    python_callable=trigger,
    default_args=args,
    dag=dag,
)

t3 = DummyOperator(
    task_id='end',
    default_args=args,
    dag=dag,
)

t1 >> t2 >> t3
