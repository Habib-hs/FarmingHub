FROM openjdk:22

EXPOSE 9090

ADD target/farming-hub.jar farming-hub.jar

ENTRYPOINT ["java","-jar","farming-hub.jar"]