# myRetail Restful service

Tech Stack
1. Java 1.8
2. Spring Boot 2.4.2
3. MongoDb
4. Tomcat
5. Gradle
6. Swagger

Pre-requistes:
1. Install MongoDb
2. Provide the MongoDb database details at application.properties file

Access API with swagger 
URL: http://localhost:8080/swagger-ui.html

Note: 
1. For this requirement, I have created a Mockserver in Postman with a sample JSON response to combine the
HTTP request to show the produt_dec in the response. This change is done only for the url:/Products/{id}

The URL details needs to be provided in application.properties for the key product_desc
{
    "id":16483589,
    "product_desc":"Shirt"
 }
 
Assume there is a mongo DB which has product description and reads product information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response. • Example: {"id":13860428,"current_price":{"value": 13.49,"currency_code":"USD", "product_desc":"Shirt"}}

2. Spring security is enabled. Use the default credentials for logging in.




