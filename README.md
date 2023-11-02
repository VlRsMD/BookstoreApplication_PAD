***This is a project for laboratory work for Distributed Programming course at the Technical University of Moldova. This project represents an Online Book Store implementing microservice architecture.***

<br/>
<br/>

**1. Application Suitability**

- Online Book Store application should provide possibility to manage, buy, add, remove, sell and ship books. Such kind of a system is a complex one.
- It is relevant to use microservice architecture and to build the backend of the Online Book Store server application as a set of small services. Each microservice implements a specific end-to-end functionality within a certain context boundary.
- In the Online Book Store app there are be several services, each dedicated to a separate set of functionalities: Storage service, Payment Service and Order service. Those services are loosely coupled and each of them has autonomy of development, deployment and scale.
- Microservice architecture in this application encrease maintainability of the system. Microservice can be scaled independently. Instead of scaling a single monolithic application you can instead scale one of the specific microservices. Thus, you can scale just the functional area of the system providing more processing power or network bandwidth instead of scaling other areas of the app which don't require scaling.
- Microservice architecture also allows to run and test services in isolation and to evolve them independently while maintaining contracts between them.

<br/>
<br/>

**2. Real world example of an application that employs microservices:**

An example of a platform which uses microservice architecture and containerization is Spotify. Spotify uses a microservices architecture for music streaming. The architecture includes hundreds of services responsible for different aspects of the system. Each service addresses a specific functionality, like song retrieval, sharing recommendations, etc. Services are usually written in Python and/or Java. Spotify uses PostgreSQL and Casandra for database and storage-based services. Microservice architecture, with each service having single responsibility and objective, allows Spotify to be fast and precise and promptly address the end user's needs.

<br/>
<br/>

**3. Service boundaries**

The microservice architecture of the system includes following microservices:

- **Storage Service**

This service is responsible of managing information about the book storage. In this service the management of books specific data, such as title, author, year of edition and etc. are handled. This microservice performs CRUD operations on books data.

- **Order Service**

This service is responsible of managing data about orders made by users from the book store. In this service the management of order specific data, such as the name of the person who made the order, the address of that person, the book ordered, the time of the order and etc. are handled. This microservice performs CRUD operations on orders data.

- **Payment Service**

This service is responsible of managing information about payments. In this service the management of payments specific data, such as the name of the payer, the method of payment, the item payed for and etc. are handled. This microservice performs CRUD operations on payments data.

<br/>
<br/>

**4. Technology Stack and Communication Patterns**

For the backend development of the app services I use Java and Kotlin programming languages and Spring Boot framework. For data storage I use Spring Data for PostgreSQL. I use RESTful APIs to enable inter-service communication.

<br/>
<br/>

**5. Data Management**

Each of the services is linked to a separate database to store the service specific data. The services are enabled to communicate via APIs. Data is transferred in JSON format.

The services include following endpoints for specific services:


**2) For Storage Service:**

- **/book**

This endpoint accepts GET and POST requests. On the GET request to that endpoint data about all the books is displayed.
On the POST request to that endpoint a new book is submitted.


- **/book/{id}**

This endpoint accepts DELETE and PUT requests. On the DELETE request to that endpoint the book with ID indicated in the path is deleted.
On the PUT request to that endpoint the data about the book with ID indicated in the path is updated.


**3) For Order Service:**

- **/order**

This endpoint accepts GET and POST requests. On the GET request to that endpoint all the orders are displayed.
On the POST request to that endpoint new order is submitted.


- **/order/{id}**

This endpoint accepts DELETE and PUT requests. On the DELETE request to that endpoint the order with ID indicated in the path is deleted.
On the PUT request to that endpoint the data about the order with ID indicated in the path is updated.


**4) For Payment Service:**

- **/payment**

This endpoint accepts GET and POST requests. On the GET request to that endpoint all the payments are displayed.
On the POST request to that endpoint new order is submitted.

- **/payment/{id}**

This endpoint accepts DELETE and PUT requests. On the DELETE request to that endpoint the payment with ID indicated in the path is deleted.
On the PUT request to that endpoint the data about the payment with ID indicated in the path is updated.

<br/>
<br/>

**7. Deployment and Scaling**

For containerizing the Online Book store microservice application Docker is used as a container engine. And for automating of deployment, scaling and management of the application Kubernetes will be used as a container orchestration platform.
<br/>
<br/>

**8. Running Docker containers for the Microservices**

Each microservice of tthe application is depployed independently and has a correspondent Docker container. At current stage of the project the containers can be build using the correspondent project which build this appliccation.
- In order to create a container for the Order microservice open in an IDE the project folder of the order Microservice available here: https://drive.google.com/drive/folders/1NANVTOLkLb7CLn6_mmXfAstgVd1NiNSl. And then find under the 'Orders' folder the 'docker-compose.yml' file - running this file will create the Docker container for the Orders microservice.
- In order to create a container for the Payment microservice open in an IDE the project folder of the Payment Microservice available here: https://drive.google.com/drive/folders/1N2ZfGC9Y4YZ18A_UO4qCIfi2VGA0gIbT. And then find under the 'Payment' folder the 'docker-compose.yml' file - running this file will create the Docker container for the Payment microservice.
- In order to create a container for the Storage microservice the open in an IDE the project folder of the Storage Microservice available here: https://drive.google.com/drive/folders/1KWl_9Cc7hdSCsGAD1JlXoBxFT5qE0WAF. And then find under the 'Storage' folder the 'docker-compose.yml' file - running this file will create the Docker container for the Storage microservice.
- In order to create a container for the Gateway microservice open in an IDE the project folder of the Gateway Microservice available here: https://drive.google.com/drive/folders/1XsIDYleQo4VpqRHt3ZzEvbWi90Hg3PkG. And then find under the 'Gateway' folder the 'docker-compose.yml' file - running this file will create the Docker container for the Gateway microservice.
