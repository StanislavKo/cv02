# Swagger

Swagger API documentation: http://localhost:8080/v2/api-docs

# Configuration

src\main\resources\application.yaml and src\main\resources\application-dev.yaml contains configuration of the program.

- redis.host and redis.password
- logging.level.com.cv

# Build

Java 8 and maven should be installed to build the program.

cd project_folder
mvn clean package

# Launch

cd project_folder
java -Dspring.profiles.active=dev -jar target/depuser-0.0.1-SNAPSHOT.jar

Test requests and responses are in responses.md file