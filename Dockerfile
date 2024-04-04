FROM openjdk:17

COPY target/NotificationService-0.0.1-SNAPSHOT.jar notification-service.jar

ENTRYPOINT ["java","-jar","/notification-service.jar"]