FROM maven:3.8.4-jdk-11 as BUILD

EXPOSE 8080

RUN mkdir /app

WORKDIR /app

# COPY mvnw .

# COPY .mvn .mvn

# COPY pom.xml .

# RUN ./mvnw dependency:go-offline

# COPY src src

# RUN ./mvnw clean

# RUN ./mvnw package -DskipTests -Pprod,no-liquibase

# RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# ###Stage 2: A minimal docker image with command to run the app

# FROM openjdk:11

# ARG DEPENDENCY=/app/target/dependency

# COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib

# COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF

# COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# ENTRYPOINT ["java","-cp","app:app/lib/*","yes.finance.FinanceApplication"] MainApp is the main class of your java app

VOLUME /root/.m2/repository

#First copy only the pom file. This is the file with less change
COPY ./pom.xml .

#Download the package and make it cached in docker image
RUN mvn -B -f ./pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:resolve

#Copy the actual code
COPY . .

#Build code 
RUN mvn -B -f ./pom.xml -s /usr/share/maven/ref/settings-docker.xml package

ENTRYPOINT ["java","-cp","app:app/lib/*","yes.finance.FinanceApplication"]  