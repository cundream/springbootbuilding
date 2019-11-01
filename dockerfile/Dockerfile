FROM java:8
VOLUME /tmp
ADD springboot-0.0.1-SNAPSHOT.jar springbootbulid.jar
EXPOSE 8082
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/springbootbulid.jar"]
