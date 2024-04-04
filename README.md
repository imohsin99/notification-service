# NotificationService - Spring Boot Microservice
This service will coordinate the supervisors currently working at LightFeather and the jurisdiction they cover.
Any employee in the company can submit their contact information for a specific supervisor to be notified of
any announcements the supervisor has made.

### Backend Service
This microservice will be responsible for consolidating the list of current supervisors as well as updating the
notification service. As a part of this coding challenge, any submissions to this module will simply display the
results. This data does not need to be moved to another service.

## Requirements

To run this project, you will need to have the following installed on your system:

- Java (version 17)

- Spring Boot (version 3.0.6)

- Maven (version 3.9.1)


# Running locally with maven
```
mvn install
```
```
mvn spring-boot:run
```

# Running with docker
To help ensure consistently correct startup across multiple platforms, you may choose to use Docker to containerize your application.  Installation steps for docker can be found on their main page.
https://docs.docker.com/engine/install/

With Docker installed, you can build your a new image. This build needs to be run after any changes are made to the source code.
```
docker build -t notification-service . 
```

After the image builds successfully, run a container from that image.
```
docker run -p 8080:8080 notification-service:latest
```

When you are done testing, stop the server and remove the container.
```
docker rm -f notification-service
```

# Running with docker-compose
docker-compose up