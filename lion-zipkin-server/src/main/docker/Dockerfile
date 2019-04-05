FROM openjdk:8-jdk-alpine
MAINTAINER https://github.com/micyo202
VOLUME /tmp
ADD lion-zipkin-server.jar app.jar
#RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]