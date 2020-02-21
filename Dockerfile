FROM openjdk:11-jdk

ARG BUILD_DATE
ARG BUILD_VERSION

LABEL org.label-schema.build-date=$BUILD_DATE
LABEL org.label-schema.version=$BUILD_VERSION
LABEL org.label-schema.vendor="Metrica S.L"

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]

EXPOSE 8090
