FROM java:8-jre

ADD ./target/zipkin-server.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/zipkin-server.jar"]

EXPOSE 9411