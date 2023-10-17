FROM openjdk:17
ADD ./target/customerbackend-0.0.1-SNAPSHOT.jar customerbackend-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/customerbackend-0.0.1-SNAPSHOT.jar"]

