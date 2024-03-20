FROM openjdk:17-oracle
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/inditex-0.0.1.jar
ADD ${JAR_FILE} inditex.jar
ENTRYPOINT ["java", "-jar", "/inditex.jar", "-web -webAllowOthers -tcp -tcpAllowOthers -browser"]