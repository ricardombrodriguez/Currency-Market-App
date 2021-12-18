FROM maven:3.6.0-jdk-8

EXPOSE 8080

RUN mkdir /docker

WORKDIR /docker

COPY . .

RUN mvn -B dependency:resolve dependency:resolve-plugins

RUN mvn package

ENTRYPOINT ["java","-jar","/app.jar"]

