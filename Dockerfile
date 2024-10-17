FROM openjdk:21-jdk
WORKDIR /workspace

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} catalog-service.jar
# Java 버전을 출력해 Dockerfile에서 제대로 설치되었는지 확인
ENTRYPOINT ["java", "-jar", "catalog-service.jar"]