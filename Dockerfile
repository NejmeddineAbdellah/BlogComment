FROM openjdk:17
WORKDIR /App

COPY /target/BlogComment-0.0.1-SNAPSHOT.jar .


ENTRYPOINT ["java", "-jar" , "BlogComment-0.0.1-SNAPSHOT.jar"]