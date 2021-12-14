FROM maven:3.8.4-jdk-11 as BUILD

EXPOSE 8080

RUN mkdir /app

WORKDIR /app

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