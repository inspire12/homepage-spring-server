kill $(cat application.pid)
sleep 1
nohup ./gradlew bootrun --args='--spring.profiles.active=live' &
