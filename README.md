Description
-----------

A microservice for users built using Dropwizard and MongoDB.

Technology
----------
Dropwizard is an set of stable and mature libraries for allowing developers to easily build Java REST services that can be deployed in a standalone manner, with the application server (Jetty) embedded into the JAR package. The framework is performant and lightweight, and supports operations by incorporating externalised configuration, operational metrics, logging and health checks.

Dropwizard is productive and simple to use. It really facilitates the efficient creation of REST microservices that are production/enterprise quality since they are built with mature Java libraries including Jetty, Jackson, JAX-RS/Jersey and Metrics.

MongoDB is a document database that provides incredible productivity and simplicity. The project uses Mongo jack which is a stable library for interaction with MongoDB from Java.

To get a service running with create and read resources and database operations took about 20 minutes in total. This really shows how easy it is to do this in Java, and yet still retain production quality software.

Setup
-----

Install MongoDB (easiest way is as a Docker container e.g. via Kitematic)

Configure local.yml with MongoDB host, port and database name

Running
-------

Run the service:

    gradle run

POST http://localhost:8080/users

    curl -H "Content-Type: application/json" -X POST -d '{"_id": "Bob", "emailAddress": "bob@gmail.com"}' http://localhost:8080/users

GET http://localhost:8080/users

    curl http://localhost:8080/users?email_address=bob@gmail.com
