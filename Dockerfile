FROM openjdk:17-alpine
ADD target/invitation-manager-0.0.1-SNAPSHOT.jar .
EXPOSE 8000
CMD java -jar invitation-manager-0.0.1-SNAPSHOT.jar --envname=dev
