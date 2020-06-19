Access swagger-ui
http://localhost:8080/spring-skeleton/swagger-ui.html

Access h2-console (only dev)
http://localhost:8080/spring-skeleton/h2-console

#Gradle build
- ./gradlew clean build
- ./gradlew bootRun 
- If development
    - ./gradlew bootRun -Dspring.profiles.active=dev

#Docker-compose
- docker-compose up --build -d

#### If an error occurs or need to update application image:
- docker-compose rm -f spring-skeleton-app
- docker image rm -f spring-skeleton-image
- docker-compose up --build -d
