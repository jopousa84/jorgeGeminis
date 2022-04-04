
To run application:
mvnw spring-boot:run


Two clients added on @PostConstruct
1: Jorge
2: Mario


web index:
http://localhost:8480/index.html


h2:
http://localhost:8480/h2-console/


curl --request POST \
  --url http://localhost:8480/addAccount \
  --header 'Content-Type: application/json' \
  --data '{
	"customerId": 1,
	"initialCredit": 1785.97
}'


curl --request GET \
  --url http://localhost:8480/clientInfo/1 \
  --header 'Content-Type: application/json'

