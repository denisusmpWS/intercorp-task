FROM openjdk:8-jre
MAINTAINER Denis S.A.
ADD /target/proy-client-service-intercorp-1.0.0-SNAPSHOT.jar proy-client-service-intercorp.jar
EXPOSE 80
ENTRYPOINT ["java", "-jar", "proy-client-service-intercorp.jar"]