# -*- coding: utf-8 -*-
import airflow
from airflow.models import DAG
from airflow.operators.bash import BashOperator
from airflow.operators.python import PythonOperator
from airflow.utils import timezone
import analyzer.lane_analyzer as lane_analyzer
import analyzer.lane_keep_or_change_analyzer as lane_keep_or_change_analyzer
import analyzer.other_vehicle_behavior_analyzer as other_vehicle_behavior_analyzer
import analyzer.nine_block_analyzer as nine_block_analyzer
import analyzer.roadgeometry_analyzer as roadgeometry_analyzer
import analyzer.surrounding_analyzer as surrounding_analyzer
import analyzer.sakura32_analyzer as sakura32_analyzer

args = {
    'owner': 'airflow',
    'start_date': airflow.utils.dates.days_ago(2),
    'provide_context': True,
}
dag = DAG(
    dag_id='GardenAnalyzer',
    default_args=args,
    schedule_interval='@once',
)
dump_arguments = BashOperator(
    task_id='dump_arguments',
    bash_command="echo record_id: {{ dag_run.conf}}",
    default_args=args,
    dag=dag,
)
roadgeometry = PythonOperator(
    task_id='roadgeometry_analyzer',
    python_callable=roadgeometry_analyzer.execute,
    default_args=args,
    op_kwargs={'record_id': "{{ dag_run.conf['id'] }}"},
    dag=dag,
)
vehicle_lane = PythonOperator(
    task_id='vehicle_lane_analyzer',
    python_callable=lane_analyzer.execute,
    default_args=args,
    op_kwargs={'record_id': "{{ dag_run.conf['id'] }}"},
    dag=dag,
)
vehicle_lane_change = PythonOperator(
    task_id='vehicle_lane_change_analyzer',
    python_callable=lane_keep_or_change_analyzer.execute,
    default_args=args,
    op_kwargs={'record_id': "{{ dag_run.conf['id'] }}"},
    dag=dag,
)
other_vehicle_behavior = PythonOperator(
    task_id='other_vehicle_behavior_analyzer',
    python_callable=other_vehicle_behavior_analyzer.execute,
    default_args=args,
    op_kwargs={'record_id': "{{ dag_run.conf['id'] }}"},
    dag=dag,
)
nine_block = PythonOperator(
    task_id='nine_block_analyzer',
    python_callable=nine_block_analyzer.execute,
    default_args=args,
    op_kwargs={'record_id': "{{ dag_run.conf['id'] }}"},
    dag=dag,
)
surrounding_vehicle_motion = PythonOperator(
    task_id='surrounding_vehicle_motion_analyzer',
    python_callable=surrounding_analyzer.execute,
    default_args=args,
    op_kwargs={'record_id': "{{ dag_run.conf['id'] }}"},
    dag=dag,
)
sakura32 = PythonOperator(
    task_id='sakura32_analyzer',
    python_callable=sakura32_analyzer.execute,
    default_args=args,
    op_kwargs={'record_id': "{{ dag_run.conf['id'] }}"},
    dag=dag,
)

dump_arguments >> [vehicle_lane]
vehicle_lane >> [vehicle_lane_change,
                 other_vehicle_behavior, nine_block, roadgeometry]
[vehicle_lane_change, other_vehicle_behavior,
    nine_block] >> surrounding_vehicle_motion
[surrounding_vehicle_motion, roadgeometry] >> sakura32
