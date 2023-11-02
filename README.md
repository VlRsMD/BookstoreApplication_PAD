***This is a project for laboratory work for Distributed Programming course at the Technical University of Moldova. This project represents an Online Book Store implementing microservice architecture.***

<br/>
<br/>

**1. Application Suitability**

- Online Book Store application should provide possibility to manage, buy, add, remove, sell and ship books. Such kind of a system is a complex one.
- It is relevant to use microservice architecture and to build the backend of the Online Book Store server application as a set of small services. Each microservice should implement a specific end-to-end functionality within a certain context boundary.
- In the Online Book Store app there will be several services, each dedicated to a separate set of functionalities: Account service, Storage service, Shipping Service, Payment Service and Authentication & Authorization service. Those services will be loosely coupled and each of them will have autonomy of development, deployment and scale.
- Microservice architecture in this application will encrease maintainability of the system. Microservice can be scaled independently. Instead of scaling a single monolithic application you can instead scale one of the specific microservices. Thus, you can scale just the functional area of the system providing more processing power or network bandwidth instead of scaling other areas of the app which don't require scaling.
- Microservice architecture also allows to run and test services in isolation and to evolve them independently while maintaining contracts between them.

<br/>
<br/>

**2. Real world example of an application that employs microservices:**

An example of a platform which uses microservice architecture and containerization is Spotify. Spotify uses a microservices architecture for music streaming. The architecture includes hundreds of services responsible for different aspects of the system. Each service addresses a specific functionality, like song retrieval, sharing recommendations, etc. Services are usually written in Python and/or Java. Spotify uses PostgreSQL and Casandra for database and storage-based services. Microservice architecture, with each service having single responsibility and objective, allows Spotify to be fast and precise and promptly address the end user's needs.

<br/>
<br/>

**3. Service boundaries**

The microservice architecture of the system will include following microservices:

- **Storage Service**

This service will be responsible of managing information about the book storage. In this service the management of books specific data, such as title, author, year of edition and etc. will be handled. This microservice will perform CRUD operations on books data.

- **Order Service**

This service will be responsible of managing data about orders made by users from the book store. In this service the management of order specific data, such as the name of the person who made the order, the address of that person, the book ordered, the time of the order and etc. will be handled. This microservice will perform CRUD operations on orders data.

- **Payment Service**

This service will be responsible of managing information about payments. In this service the management of payments specific data, such as the name of the payer, the method of payment, the item payed for and etc. will be handled. This microservice will perform CRUD operations on payments data.

<br/>
<br/>

**4. System architecture diagram**

![image](https://github.com/VlRsMD/OnlineBookStore/assets/90247966/d9db6d49-4ab7-4971-9dd8-74aac28f660b)


<br/>
<br/>

**5. Technology Stack and Communication Patterns**

For the backend development of the app services I will use Java programming language and Spring Boot framework. For data storage I will use Spring Data for Apache Cassandra, because Cassandra as a NoSQL database can provide faster data processing. I will use RESTful APIs to enable inter-service communication.

<br/>
<br/>

**6. Data Management**

Each of the services will be linked to a separate database to store the service specific data. The services will be enabled to communicate via APIs. Data will be transferred in JSON format.

The services will include following endpoints for specific services:


**2) For Storage Service:**

- **/book**

This endpoint will accept GET and POST requests. On the GET request to that endpoint data all the books will be displayed and the HTTP response will looks like in the following example:

![image](https://github.com/VlRsMD/OnlineBookStore/assets/90247966/d21b2a9f-1fa9-4a34-b0f0-f9b5ad353336)


On the POST request to that endpoint a new book will be submitted and the response will look like in the following example:

![image](https://github.com/VlRsMD/OnlineBookStore/assets/90247966/9363ce17-894a-472a-b862-db3729f2a094)


- **/book/{id}**

This endpoint will accept DELETE and PUT requests. On the DELETE request to that endpoint the book with ID indicated in the path will be deleted and the response will look like in the following example:

![image](https://github.com/VlRsMD/OnlineBookStore/assets/90247966/2df04cdc-4c99-4f67-94ef-8329066cd85d)


On the PUT request to that endpoint the data about the book with ID indicated in the path will be updated and the response will look in the following way:

![image](https://github.com/VlRsMD/OnlineBookStore/assets/90247966/fc1600be-4f8e-4f79-a093-cf9c5a15d172)


**3) For Order Service:**

- **/order**

This endpoint will accept GET and POST requests. On the GET request to that endpoint all the orders will be displayed and the response will look like in the following example:

![image](https://github.com/VlRsMD/OnlineBookStore/assets/90247966/aa65f743-bee2-469d-957e-e1544cf8fd92)


On the POST request to that endpoint new order will be submitted and the response will look in the following way:

![image](https://github.com/VlRsMD/OnlineBookStore/assets/90247966/b0efb05b-f4d2-4bf8-bbb9-8e661bac829f)


- **/order/{id}**

This endpoint will accept DELETE and PUT requests. On the DELETE request to that endpoint the order with ID indicated in the path will be deleted and the response will look like in the following example:

![image](https://github.com/VlRsMD/OnlineBookStore/assets/90247966/1fc54352-3cd6-416b-9889-530a9ec23869)


On the PUT request to that endpoint the data about the order with ID indicated in the path will be updated and the response will look in the following way:

![image](https://github.com/VlRsMD/OnlineBookStore/assets/90247966/2820fdb8-152f-4dd9-a3fe-488b9644a788)


**4) For Payment Service:**

- **/payment**

This endpoint will accept GET and POST requests. On the GET request to that endpoint all the payments will be displayed and the response will look like in the following example:

![image](https://github.com/VlRsMD/OnlineBookStore/assets/90247966/44c84d12-d203-40d0-b23c-225c3d5d5579)


On the POST request to that endpoint new order will be submitted and the response will look in the following way:

![image](https://github.com/VlRsMD/OnlineBookStore/assets/90247966/064d65a6-5d93-4998-bf15-36526ff4f0fc)


- **/payment/{id}**

This endpoint will accept DELETE and PUT requests. On the DELETE request to that endpoint the payment with ID indicated in the path will be deleted and the response will look like in the following example:

![image](https://github.com/VlRsMD/OnlineBookStore/assets/90247966/b46561c6-bfba-44b1-82ed-12831cfe2c3c)


On the PUT request to that endpoint the data about the payment with ID indicated in the path will be updated and the response will look in the following way:

![image](https://github.com/VlRsMD/OnlineBookStore/assets/90247966/7d9896e9-98f8-41b6-9238-de0baf2fc751)



<br/>
<br/>

**7. Deployment and Scaling**

For containerizing the Online Book store microservice application Docker will be used as a container engine. And for automating of deployment, scaling and management of the application Kubernetes will be used as a container orchestration platform.
