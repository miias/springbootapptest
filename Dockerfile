FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/spring_test-0.0.1-SNAPSHOT.jar springtest.jar
EXPOSE 8081
ENTRYPOINT exec java $JAVA_OPTS -jar springtest.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar springtest.jar
