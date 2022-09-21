To start the database:
docker-compose up -d (in the directory with docker-compose.yml)

database=jdbc:postgresql://localhost:5434/rabo
username=rabo
password=rabo1234


To run the application:
mvnw spring-boot:run
(runs on port 8480)


Documentaion / OpenApi links
http://localhost:8480/v3/api-docs.yaml
http://localhost:8480/swagger-ui/index.html


web index:
http://localhost:8480/index.html
