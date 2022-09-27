#! /bin/bash
#
###############################
#  	     Variables            #
###############################

GARDEN_SERVICE=("pm2" "tomcat" "job_executor" "airflow-scheduler" "airflow-webserver" "fuseki" "apache2")

### SETUP DIRECTORY ###
readonly HOME_DIR="/home/garden-user"
readonly SCRIPT_DIR="$HOME_DIR/garden/script"
readonly JOB_EXECUTOR_DIR="$HOME_DIR/garden/job_executor"
readonly FUSEKI_DIR="$HOME_DIR/etc/fuseki";
readonly FUSEKI_DATA_DIR="${FUSEKI_DIR}/databases/garden";
readonly FUSEKI_DATA_DIR_BK="${FUSEKI_DATA_DIR}/backup";

### SETUP POSTGRES LOGIN/Password ###
PGHOST="localhost"
PGPORT="5432"
PGUSER='postgres'

### JobExecutor JOBの状態 ###
JOB_STATUS_NOEXEC="100";
JOB_STATUS_EXECUTING="102";

### Airflow TASKの状態 ###
TASK_STATUS_RUNNING="running";

###############################
#  User Defined Functions     #
###############################

#
# JobExecutorの起動確認
#
has_running_job() {
	# 実行中のJOBの確認
	local sql="SELECT count(1) FROM job where status in ('$JOB_STATUS_NOEXEC' ,'$JOB_STATUS_EXECUTING');"
	local cnt=$(psql -h $PGHOST -p $PGPORT -U $PGUSER -d garden -q  --no-align -t -c "$sql";)

	if [ $cnt -ne 0 ]
	then # 実行中のJOBが存在する場合
		return 0
	else # 実行中のJOBが存在しない場合
		return 1
	fi
}

#
# Analyzerの起動確認
#
has_running_task() {
	# 実行中のServiceの確認
	systemctl is-active --quiet airflow-scheduler
	local schedulerStatus=$?
	systemctl is-active --quiet airflow-webserver
	local webserverStatus=$?
	# 実行中のTASKの確認
	local sql="SELECT count(1) FROM task_instance where state='$TASK_STATUS_RUNNING';"
	local cnt=$(psql -h $PGHOST -p $PGPORT -U $PGUSER -d airflow -q  --no-align -t -c "$sql";)

	if [ $schedulerStatus -eq 0 ]  && [ $webserverStatus -eq 0 ]  && [ $cnt -ne 0 ]
	then # 実行中のTASKが存在する場合
		return 0
	else # Analyzerが実行中のTASKない場合
		return 1
	fi
}

#
# Fuseki DB Compact
#
compact_fuseki() {
	echo "#########################################################"
	echo "###                                                   ###"
	echo "###  Fuseki Compact を実行します。                    ###"
	echo "###                                                   ###"
	echo "#########################################################"
	systemctl is-active --quiet fuseki
	local status=$?
	if [ $status -eq 0 ] ; then
		echo "Fuseki is running!! stop compact!!!"
		return 1
	else
		echo "compact database execution"
		sudo -u fuseki java -jar ${SCRIPT_DIR}/CompactDatabase.jar ${FUSEKI_DATA_DIR}
		result=$?
		if [ $result -ne 0 ]; then
			echo "Compact failed!! Error Code:${result}"
		fi

		echo "remove unnecessary files"
		sudo -u fuseki rm -rf ${FUSEKI_DATA_DIR_BK}
		sudo -u fuseki mkdir ${FUSEKI_DATA_DIR_BK}
		# sudo su - fuseki -c "cd ${FUSEKI_DATA_DIR}"
		readonly removeDataDirs=(`sudo -u fuseki ls -v $FUSEKI_DATA_DIR | grep Data`)
		for dir in "${removeDataDirs[@]}";
		do
			if [ -n "${removeDir}" ]; then
				sudo -u fuseki tar --remove-files -zcvf ${FUSEKI_DATA_DIR}/${removeDir}.tar.gz ${FUSEKI_DATA_DIR}/${removeDir}
				sudo -u fuseki mv ${FUSEKI_DATA_DIR}/${removeDir}.tar.gz ${FUSEKI_DATA_DIR_BK}/
			fi
			removeDir=$dir
		done
		echo "fuseki compact database finish"
		return 0
	fi
	echo "#########################################################"
	echo "###                                                   ###"
	echo "###  Fuseki Compact を実行しました。                  ###"
	echo "###                                                   ###"
	echo "#########################################################"
}

#
# サービス開始
#
start_service() {
	echo "$1 を起動します。";
	sudo systemctl restart $1
	local status=$?
	if [ $status -ne 0 ] ; then
		echo "$1 の起動が失敗しました。! Error Code:$status"
		return 1
	else
		echo "$1 を起動しました。";
		return 0
	fi
}

#
# サービス停止
#
stop_service() {
	echo "$1 を停止します。";
	sudo systemctl stop $1
	local status=$?
	if [ $status -ne 0 ] ; then
		echo "$1 の停止が失敗しました。! Error Code:$status"
		return 1
	else
		echo "$1 を停止しました。";
		return 0
	fi
}

#
# Nodeプロジェクトビルド
#
build_node_apps() {
	# ビルドGARDEN_Portal
	if [ -d "/home/garden-user/garden/node_services/GARDEN_Portal" ]; then rm -Rf /home/garden-user/garden/node_services/GARDEN_Portal; fi
	cp -rf /home/garden-user/garden/garden-repo/GARDEN_Portal /home/garden-user/garden/node_services
	echo -e "[\e[96mBuilding\e[0m] \e[4m/home/garden-user/garden/node_services/GARDEN_Portal\e[0m"
	(
		cd /home/garden-user/garden/node_services/GARDEN_Portal/client;
		npm install &>/dev/null;
		npm run build &>/dev/null;
		cp -rfR /home/garden-user/garden/node_services/GARDEN_Portal/client/build /home/garden-user/garden/node_services/GARDEN_Portal/server/portal;
		cd /home/garden-user/garden/node_services/GARDEN_Portal/server;
		npm install &>/dev/null;
		npm run build &>/dev/null;
	)
	echo -e "[\e[96mBuilded\e[0m] \e[4m/home/garden-user/garden/node_services/GARDEN_Portal\e[0m"

    # ビルドScenarioEditor
	if [ -d "/home/garden-user/garden/node_services/Zipc_ScenarioEditor" ]; then rm -Rf /home/garden-user/garden/node_services/Zipc_ScenarioEditor; fi
	cp -rf /home/garden-user/garden/garden-repo/Zipc_ScenarioEditor /home/garden-user/garden/node_services
	echo -e "[\e[96mBuilding\e[0m] \e[4m/home/garden-user/garden/node_services/Zipc_ScenarioEditor\e[0m"
	(
		cd /home/garden-user/garden/node_services/Zipc_ScenarioEditor/client;
		npm install &>/dev/null;
		npm run build &>/dev/null;
		cp -rfR /home/garden-user/garden/node_services/Zipc_ScenarioEditor/client/build /home/garden-user/garden/node_services/Zipc_ScenarioEditor/scenario_editor;
		cd /home/garden-user/garden/node_services/Zipc_ScenarioEditor;
		npm install &>/dev/null;
	)
	echo -e "[\e[96mBuilded\e[0m] \e[4m/home/garden-user/garden/node_services/Zipc_ScenarioEditor\e[0m"

    # ビルドRoadEditor
	if [ -d "/home/garden-user/garden/node_services/Zipc_Microservice-Road" ]; then rm -Rf /home/garden-user/garden/node_services/Zipc_Microservice-Road; fi
	echo -e "[\e[96mBuilding\e[0m] \e[4m/home/garden-user/garden/node_services/Zipc_Microservice-Road\e[0m"
	(
		cd /home/garden-user/garden/garden-repo/Zipc_RoadEditor;
		npm install &>/dev/null;
		npm run build-product &>/dev/null;
		cd /home/garden-user/garden/garden-repo/Zipc_Microservice-Road;
		npm install &>/dev/null;
		npm run build-product &>/dev/null;
		cp -rf /home/garden-user/garden/garden-repo/Zipc_RoadEditor/dist/static /home/garden-user/garden/garden-repo/Zipc_Microservice-Road/dist/Zipc_Microservice-Road/;
		cp -rf /home/garden-user/garden/garden-repo/Zipc_Microservice-Road/dist/Zipc_Microservice-Road /home/garden-user/garden/node_services;
		cd /home/garden-user/garden/node_services/Zipc_Microservice-Road;
		npm install &>/dev/null;
	)
	echo -e "[\e[96mBuilded\e[0m] \e[4m/home/garden-user/garden/node_services/Zipc_Microservice-Road\e[0m"
}

#
# すべてのサービス開始
#
start_all() {
	echo "#########################################################"
	echo "###                                                   ###"
	echo "###  start all を実行します。                         ###"
	echo "###                                                   ###"
	echo "#########################################################"
	local jobExePid=$(ps -e -o pid,cmd | grep Zipc_JobExecutor.jar | grep -v grep | awk '{ print $1 }');
	has_running_job
	local hasRunJob=$?
	if [ -n "$jobExePid" ] && [ $hasRunJob -eq 0 ] ; then
		echo "Job Executorが実行中で、stop_all を実行してから start_allをもう一回実行してください。";
		return 1
	fi

	has_running_task
	local hasRunTask=$?
	if [ $hasRunTask -eq 0 ] ; then
		echo "Airflowのタスクが実行中で、stop_all を実行してから start_allをもう一回実行してください。";
		return 1
	fi

	local servName=""
	for ((i=${#GARDEN_SERVICE[@]}-1; i>=0; i--)); do
		servName=${GARDEN_SERVICE[i]}
		if [ $servName == "pm2" ] ; then
			pm2 start /home/garden-user/garden/node_services/ecosystem.config.js
		elif [ $servName == "job_executor" ] ; then
			cd $JOB_EXECUTOR_DIR
			nohup java -jar Zipc_JobExecutor.jar > out.log > err.log &
			cd $SCRIPT_DIR
		else
			start_service $servName
		fi
	done
	echo "#########################################################"
	echo "###                                                   ###"
	echo "###  start all を実行しました。                       ###"
	echo "###                                                   ###"
	echo "#########################################################"
}

#
# すべてのサービス停止
#
stop_all() {
	echo "#########################################################"
	echo "###                                                   ###"
	echo "###  stop all を実行します。                          ###"
	echo "###                                                   ###"
	echo "#########################################################"
	local jobExePid=$(ps -e -o pid,cmd | grep Zipc_JobExecutor.jar | grep -v grep | awk '{ print $1 }');
	has_running_job
	local hasRunJob=$?
	if [ -n "$jobExePid" ] && [ $hasRunJob -eq 0 ] ; then
		echo "Job Executorが実行中で、サーバの停止を取り消しました。";
		return 1
	fi

	has_running_task
	local hasRunTask=$?
	if [ $hasRunTask -eq 0 ] ; then
		echo "Airflowのタスクが実行中で、サーバの停止を取り消しました。";
		return 1
	fi

	local servName=""
	for ((i=0; i<${#GARDEN_SERVICE[@]}; i++)); do
		servName=${GARDEN_SERVICE[i]}
		if [ $servName == "pm2" ] ; then
			pm2 delete all
		elif [ $servName == "job_executor" ] ; then
			echo "JobExecutorを停止します。";
			kill -9  $jobExePid &
			wait;
			echo "JobExecutorを停止しました。";
		else
			stop_service $servName
		fi
	done
	echo "#########################################################"
	echo "###                                                   ###"
	echo "###  stop all を実行しました。                        ###"
	echo "###                                                   ###"
	echo "#########################################################"
}

#
# GARDENプロジェクトをビルドする
#
build_all() {
	echo "#########################################################"
	echo "###                                                   ###"
	echo "###  build all を実行します。                         ###"
	echo "###                                                   ###"
	echo "#########################################################"
    # com.zipc.garden.parser/gradlew の実行権限付与
    chmod 764 /home/garden-user/garden/garden-repo/com.zipc.garden.parser/gradlew

	# 依存ライブラリのダウロード
	find '/home/garden-user/garden/garden-repo' -type f -regex '.+/lib/\([^/]+/\)*requirements.txt'  -printf '%h\n'  | sort -t '\0' -n | while read line; do
	    echo -e "\e[1mProcessing $line\e[0m"
	    (
			cd $line;
			egrep 'http:|https://' requirements.txt | xargs -I%% sh -c ' \
			    curl -LOfs %% \
			    && echo "\t\e[96mDownload completed... \e[0m \e[4m%%\e[0m" \
			    || echo "\t\e[91mDownload failed...... \e[0m \e[4m%%\e[0m" \
			';
			egrep "^'[^']+'$" requirements.txt | xargs -I%% sh -c %%;
	    )
	done

    # ビルド実行
	# Javaプロジェクトのビルド
	sh -c ' \
	# Java
	    cd /home/garden-user/garden/garden-repo/Zipc_Webplatform; \
		ant -f build-all.xml Build-All; \
	'
    # ビルド資材の配布
	# warの資材
	if [ ! -d "/home/garden-user/garden/war" ]; then sudo mkdir -p /home/garden-user/garden/war; else sudo rm -rf /home/garden-user/garden/war/*; fi;
	sudo cp /home/garden-user/garden/garden-repo/RDFViewer/prefix.properties /home/garden-user/garden/war;
	sudo cp /home/garden-user/garden/garden-repo/RDFViewer/target/RDFViewer.war /home/garden-user/garden/war;
	sudo cp /home/garden-user/garden/garden-repo/Zipc_Webplatform/Zipc_Webplatform.war /home/garden-user/garden/war;
	sudo cp /home/garden-user/garden/garden-repo/com.zipc.garden.webplatform.dsl.sc.parent/com.zipc.garden.webplatform.dsl.sc.web/target/com.zipc.garden.webplatform.dsl.sc.web.war /home/garden-user/garden/war;
	sudo cp /home/garden-user/garden/garden-repo/com.zipc.garden.webplatform.dsl.fmc.parent/com.zipc.garden.webplatform.dsl.fmc.web/target/com.zipc.garden.webplatform.dsl.fmc.web.war /home/garden-user/garden/war;

	# warファイルをTomcatにコピーする
	sudo ls -l /home/garden-user/etc/tomcat/webapps/;
	sudo rm -rf /home/garden-user/etc/tomcat/webapps/Zipc_Webplatform;
	sudo cp /home/garden-user/garden/war/Zipc_Webplatform.war /home/garden-user/etc/tomcat/webapps/Zipc_Webplatform.war;
	sudo chown tomcat:tomcat /home/garden-user/etc/tomcat/webapps/Zipc_Webplatform.war;
	sudo cp /home/garden-user/garden/war/com.zipc.garden.webplatform.dsl.sc.web.war /home/garden-user/etc/tomcat/webapps/.;
	sudo cp /home/garden-user/garden/war/com.zipc.garden.webplatform.dsl.fmc.web.war /home/garden-user/etc/tomcat/webapps/.;
	sudo chown tomcat:tomcat /home/garden-user/etc/tomcat/webapps/com.zipc.garden.webplatform.dsl.fmc.web.war;
	sudo chown tomcat:tomcat /home/garden-user/etc/tomcat/webapps/com.zipc.garden.webplatform.dsl.sc.web.war;
	sudo cp /home/garden-user/garden/war/RDFViewer.war /home/garden-user/etc/tomcat/webapps/.;
	sudo chown tomcat:tomcat /home/garden-user/etc/tomcat/webapps/RDFViewer.war;
	sudo cp /home/garden-user/garden/war/prefix.properties /home/garden-user/etc/tomcat/webapps/.;
	sudo chown tomcat:tomcat /home/garden-user/etc/tomcat/webapps/prefix.properties;
	sudo ls -l /home/garden-user/etc/tomcat/webapps/;

	# dagsの資材
	sudo -Hu airflow bash -c ' \
		# 環境変数の適用 \
		source ~/.profile; \
		# dagsフォルダをクリアする \
		if [ ! -d "~/dags" ]; then mkdir -p ~/dags; else rm -rf ~/dags/*; fi; \
		# dagsフォルダをコピーする \
		cp -rf /home/garden-user/garden/garden-repo/Zipc_airflow/src/* ~/dags; \
		# 依存パッケージをインストールする \
		pip install --user -r ~/dags/requirements.txt; \
		# DAGsの初期状態を設定する \
		airflow scheduler -D && ( \
			sleep 5s; \
			airflow dags unpause CoordinatesConverter; \
			airflow dags unpause GardenAnalyzer; \
			airflow dags unpause LonLatExtractor; \
			cat ~/airflow-scheduler.pid | xargs kill; \
		) \
	'

	# scriptの資材
	sudo cp -fR /home/garden-user/garden/garden-repo/RDFCompact/target/RDFCompact.jar /home/garden-user/garden/script/CompactDatabase.jar;

	# Job Executorの資材
	if [ ! -d "/home/garden-user/garden/job_executor" ]; then sudo mkdir -p /home/garden-user/garden/job_executor; else sudo rm -rf /home/garden-user/garden/job_executor/*; fi;
	sudo cp -rf /home/garden-user/garden/garden-repo/Zipc_JobExecutor/lib /home/garden-user/garden/job_executor;
	sudo cp -rf /home/garden-user/garden/garden-repo/Zipc_JobExecutor/acts_cmd_2.92.jar /home/garden-user/garden/job_executor;
	sudo cp -rf /home/garden-user/garden/garden-repo/Zipc_JobExecutor/Zipc_JobExecutor.jar /home/garden-user/garden/job_executor;


	# Nodeプロジェクトのビルド
	build_node_apps
	echo "#########################################################"
	echo "###                                                   ###"
	echo "###  build all を実行しました。                       ###"
	echo "###                                                   ###"
	echo "#########################################################"
}



###################################
# Main Script Logic Starts Here   #
###################################
case "$1" in
	compact_fuseki)
		stop_all
		echo ""
		compact_fuseki
		echo ""
		start_all
		echo ""
		;;
	stop_all)
		stop_all
		;;
	start_all)
		start_all
		;;
	build_all)
		build_all
		;;
	*)
		echo "Usage: $0 { compact_fuseki | stop_all | start_all | build_all }"
		echo ""
		echo "Use this shell script to start / stop / compact fuseki database."
esac
