# ecommerce-upgrad

## Pre-Requisites

- Java 11 - https://www.java.com/en/download/help/download_options.html
- Maven - https://www.baeldung.com/install-maven-on-windows-linux-mac
- MongoDB - https://www.mongodb.com/docs/manual/installation/
- IDE - IntelliJ Community Edition - https://www.jetbrains.com/idea/download
- Postman - https://www.postman.com/downloads/

### Postman Setup

- Download postman and use the **Upgrad Ecommerce.postman_collection.json** file to see what all APIs should look like
  and
  try them out with your local environment.
- In Postman simply Go to Import -> Select the file.
- This will create a new collection in your postman.
- Authentication tokens are saved in the headers tab and in the environment variables.

## Setup

- Open Project in IntelliJ Community Edition
- In the maven Side panel run Maven Clean and Install Lifecycle Commands.
- Go to Terminal in the IDE and run `mvn spring-boot:run`
- This will start the application and Also setup the roles if not already created.
- This is what my mongodb looks like:
-
- <img width="1437" alt="Screenshot 2023-08-23 at 1 17 12 PM" src="https://github.com/mohinishjoshi/ecommerce-upgrad/assets/29919861/f63c56e7-3d04-45ec-b896-5b7a4115551d">

### SIGNUP

For Normal User Signup

```
POST http://localhost:8080/api/auth/signup
{
    "email": "demo@demo.com",
    "password": "password",
    "firstName": "MyFName",
    "lastName": "MyLName",
    "contactNumber": "1234567890"
}
```

For Admin User Signup

```
POST http://localhost:8080/api/auth/signup
{
    "email": "demo@demo.com",
    "password": "password",
    "firstName": "MyAdminName",
    "lastName": "MyAdminLName",
    "contactNumber": "1234567890",
    "role": "ADMIN"
}
```

### LOGIN

```
POST http://localhost:8080/api/auth/signin
{
    "username": "demo@demo.com",
    "password": "password"
}
```

The above returns a token in the **x-auth-token** header.

All Authenticated Requests should include this token in the request Headers

```
x-auth-token: TOKEN_VALUE_HERE
```

### For All below remember to add the x-auth-token Header (Like Above)

## Products

### Create Products

```
POST http://localhost:8080/api/products
{
    "name": "Dryfit Tshirt",
    "category": "Clothes",
    "price": 200.5,
    "description": "An Awesome Tshirt",
    "manufacturer": "Nike",
    "availableItems": 100
}
```

### Update Products

```
PUT http://localhost:8080/api/products/{id}
{
    "id": "64e5d487ae2bb51b4b7e6e61",
    "name": "Sports Shoes By Reebok123",
    "category": "Shoes",
    "price": 500,
    "description": "Its not a shoe, its your partner",
    "manufacturer": "Reebok",
    "availableItems": 100
}
```

### Delete Products

```
DELETE http://localhost:8080/api/products/{id}
```

### GET All Categories

```
GET http://localhost:8080/api/products/categories
```

## Addresses

### Get All Addresses

```
GET http://localhost:8080/api/addresses
```

### Create Address

```
POST http://localhost:8080/api/addresses
{
    "name": "SomeAddress",
    "contactNumber": "123324234234",
    "city": "Delhi",
    "landmark": "Dilli Haat",
    "street": "Some Street",
    "state": "Delhi",
    "zipcode": "123456"
}
```

### Update Address

```
PUT http://localhost:8080/api/addresses/{id}
{
    "id": "64e5fc6a2122c31e8edc4a18",
    "name": "SomeAddress123",
    "contactNumber": "9999999999",
    "city": "Delhi",
    "landmark": "Dilli Haat",
    "street": "Some Street",
    "state": "Delhi",
    "zipcode": "123456"
}
```

### Delete Address

```
DELETE http://localhost:8080/api/addresses/{id}
```

## Orders

### Get All Orders

```
GET http://localhost:8080/api/orders
```

### Get an Order

```
GET http://localhost:8080/api/orders/{id}
```

### Create An Order

```
POST http://localhost:8080/api/orders
{
    "quantity": 5,
    "product": "PRODUCT_ID"
    "address": "ADDRESS_ID"
}
```
