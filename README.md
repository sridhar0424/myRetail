## About myRetail
myRetail is a rest service that returns product description and price details for a given product id.

 - Price details are stored in mongoDB.
 - Product name comes from an external API
 - myRetail combines both information and returns response in json format
 - Spring Boot to create the project
 
 ### Technologies
  - Java 8
  - MongoDB
  - Maven
  - SpringBoot
 
 ### Build and Run
  - goto myretail directory
  - mvn clean package
  - Run the jar
  - java -jar target/myRetail-0.0.1-SNAPSHOT.jar
 
 
 ### Rest Service Details:
 #### *Title:* Get product information by product id.
 - URL: [http://localhost:8080/products/{productId}](http://localhost:8080/products/{productId})
 -  Method: **GET**
 -  Returns: json response
 -  Example: http://localhost:8080/products/13860428
    - {"productId": 13860428,"name": "The Big Lebowski (Blu-ray)", "currentPrice": {"value": 22.22,"currency_code": "USD"}}

#### *Title:* Update product price
 - URL: http://localhost:8080/products/{productId}
 - Method: **PUT**
 - Example:
   - Request: {"productId": 13860428,"name": "The Big Lebowski (Blu-ray)","currentPrice": {"value": 23.22,"currency_code": "USD"}}
   - Response: {"code": 0,"message": "Successfully updated"}

##### When product id is not found in external rest api
  - Method: **GET**
  - Example: http://localhost:8080/products/15117729
    - Response: {"productId": 15117729,"currentPrice": {"value": 100.99,"currency_code": "USD"}}
 
##### When product id is not found in database 
   - Method: **GET**
   - Example: http://localhost:8080/products/138604281
     - Response: {"httpStatus": 404, "httpstatusPhrase": "Not Found","message": "Record not found in database"}







 
