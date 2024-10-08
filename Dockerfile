FROM registry.cn-shanghai.aliyuncs.com/bitypes/maven:3.9.9-amazoncorretto-23-alpine AS builder
WORKDIR /app

ADD ./settings.xml settings.xml
ADD ./pom.xml pom.xml
ADD ./src src/

RUN mvn clean package -Dmaven.test.skip=true -s settings.xml

FROM registry.cn-shanghai.aliyuncs.com/bitypes/openjdk:24-oracle
WORKDIR /app
ARG app
ENV app=${app}

COPY --from=builder /app/target/${app}.jar /app/${app}.jar
EXPOSE 8080

ENTRYPOINT sh -c 'exec java -jar /app/${app}.jar'