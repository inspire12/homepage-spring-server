kill $(cat application.pid)
sleep 1
nohup ./gradlew bootRun &