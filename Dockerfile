FROM openjdk:17
WORKDIR /App

COPY /target/comment-0.0.1-SNAPSHOT.jar .


ENTRYPOINT ["java", "-jar" , "comment-0.0.1-SNAPSHOT.jar"]