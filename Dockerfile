FROM openjdk:22

EXPOSE 8080

ADD target/farming-hub.jar farming-hub.jar

ENTRYPOINT ["java","-jar","farming-hub.jar"]