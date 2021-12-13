FROM maven:3.6.0-jdk-8

EXPOSE 8080

RUN mkdir /app

WORKDIR /app

COPY . .

RUN mvn -B dependency:resolve dependency:resolve-plugins

RUN mvn package

ENTRYPOINT ["java","-cp","app:app/lib/*","yes.finance.FinanceApplication"]