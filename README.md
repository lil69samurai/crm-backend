# CRM Backend API - 客戶管理系統

A simplified CRM backend system built with Spring Boot for customer management.
(使用 Spring Boot 開發的簡化版 CRM 客戶關係管理系統後端 API。)

## Features 功能

- Create customer
- Get all customers
- Get customer by ID
- Update customer
- Delete customer
- Search customers by name and email
- Pagination support
- Request validation
- Global exception handling
- Swagger/OpenAPI documentation

## API Endpoints
|
Method
|
Endpoint
|
Description
|
|

|
POST 新增客戶
|
/api/customers
|
Create a customer
|

|
GET 查詢全部客戶
|
/api/customers
|
Get customers with search and pagination
|

|
GET 查詢單一客戶
|
/api/customers/{id}
|
Get customer by ID
|

|
PUT 修改客戶
|
/api/customers/{id}
|
Update customer
|

|
DELETE 刪除客戶
|
/api/customers/{id}
|
Delete customer
|

## Tech Stack 技術棧
- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database
- Maven
- Swagger / OpenAPI


## Project Structure 專案架構
src/main/java/com/eichi/customer_api/
- Customer.java
- CustomerController.java
- CustomerService.java
- CustomerRepository.java
- CustomerRequestDTO.java
- CustomerResponseDTO.java
- GlobalExceptionHandler.java
- OpenApiConfig.java


## Search and Pagination Examples
- `/api/customers?name=田中`
- `/api/customers?email=gmail`
- `/api/customers?page=0&size=5`
- `/api/customers?name=田中&page=0&size=2`

## Future Improvements
- Replace H2 with MySQL
- Add unit and integration tests
- Add authentication and authorization with Spring Security
- Dockerize the application
- Deploy to cloud platform


## Run Locally 啟動方式
```bash
git clone https://github.com/lil69samurai/crm-backend.git
cd crm-backend
./mvnw spring-boot:run