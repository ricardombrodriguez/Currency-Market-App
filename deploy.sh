trap killcontainers SIGINT

killcontainers(){
  docker-compose kill
  docker-compose down
}

if [ -z $1 ] || [[ $1 != "no-build" ]]; then sh build.sh && docker-compose build; fi

docker-compose up &
wait