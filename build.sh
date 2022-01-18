cd ./projBackend/finance
./mvnw package -DskipTests=true

cd ../../
pip install -r ./projDataGenerator/requirements.txt

cd ./projFrontend
ng build
