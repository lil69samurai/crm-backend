# 🏢 CRM Backend — 客戶管理系統

使用 Spring Boot 開發的簡化版 CRM（客戶關係管理）系統後端 API。

## 📋 功能

|
功能
|
HTTP 方法
|
端點
|
|
------

------
|
|
新增客戶
|
POST
|
`/api/customers`

|
|
查詢全部客戶
|
GET
|
`/api/customers`

|
|
查詢單一客戶
|
GET
|
`/api/customers/{id}`

|
|
修改客戶
|
PUT
|
`/api/customers/{id}`

|
|
刪除客戶
|
DELETE
|
`/api/customers/{id}`


## 🛠 技術棧

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **H2 Database**
- **Maven**

## 📐 專案架構
src/main/java/com/eichi/customer_api/
├── Customer.java # Entity — 資料模型
├── CustomerRepository.java # Repository — 資料庫操作
├── CustomerService.java # Service — 商業邏輯
├── CustomerController.java # Controller — REST API
└── CustomerApiApplication.java # 主程式入口


## 🚀 啟動方式

```bash
git clone https://github.com/lil69samurai/crm-backend.git
cd crm-backend
./mvnw spring-boot:run
