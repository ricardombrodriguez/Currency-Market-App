trap killall SIGINT

killall(){
  echo "Killing containers..."
  docker kill finance_db
  docker kill finance_mq

  echo "Removing containers..."
  docker rm finance_db
  docker rm finance_mq
}

docker run --name finance_db -e MYSQL_DATABASE=ies -e MYSQL_USER=ies -e MYSQL_PASSWORD=password -e MYSQL_ROOT_PASSWORD=password -p 5000:3306 mysql:8.0.27 &
docker run --name finance_mq -p 5672:5672 rabbitmq:alpine &

wait