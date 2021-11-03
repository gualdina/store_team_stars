#Image that we are using
FROM openjdk:11
ADD target/store-0.0.1-SNAPSHOT.jar store.jar
#EXPOSE 8080
ENTRYPOINT ["java", "-jar", "store.jar"]