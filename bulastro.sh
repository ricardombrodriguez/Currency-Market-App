trap killall SIGINT

killall(){
  kill 0
}

export SPRING_DATASOURCE_URL=jdbc:mysql://127.0.0.1:5000/ies
export SPRING_DATASOURCE_USERNAME=ies
export SPRING_DATASOURCE_PASSWORD=password
export FINANCE_RABBITMQ_HOST=127.0.0.1

cd projBackend/finance
./mvnw spring-boot:run &

cd ../../projFrontend
ng serve &

wait