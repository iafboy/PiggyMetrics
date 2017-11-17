FROM java:8-jre

ADD ./target/license-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/license-service.jar"]

EXPOSE 6003