export CONFIG_SERVICE_PASSWORD=oracle
export NOTIFICATION_SERVICE_PASSWORD=oracle
export STATISTICS_SERVICE_PASSWORD=oracle
export ACCOUNT_SERVICE_PASSWORD=oracle
export LICENSE_SERVICE_PASSWORD=oracle
export MONGODB_PASSWORD=oracle
mvn clean package -DskipTests
docker-compose -f docker-compose.yml -f docker-compose.dev.yml up --build
