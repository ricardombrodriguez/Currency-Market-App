FROM openjdk:11-jre-slim
COPY ./target/finance-0.0.1-SNAPSHOT.jar /usr/local/lib/finance.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/finance.jar"]