Description
-----------

A simple microservice for user auth, built using Dropwizard and MongoDB.

JWT is used as the auth token returned to a authenticated user.

Technologies
------------
- Dropwizard Core - JAXRS, Jersey
- Dropwizard Auth
- Mongojack
- MongoDB
- JJWT

Running in Development Mode
---------------------------

1) Install MongoDB (easiest way is as a Docker container e.g. via Kitematic)

2) Configure config-local.yml with MongoDB host, port and database name

3) Run the service:

    ./gradlew run

Build Distribution
------------------

There are two options here:

1) Create a zip distribution

    ./gradlew build
    cd build/distributions
    unzip fitr-service-auth-1.0-SNAPSHOT.zip
    cd fitr-service-auth-1.0-SNAPSHOT
    ./bin/fitr-service-auth server <path_to_local_config>

2) Create an uber jar

    ./gradlew oneJar
    cd build/libs
    java -jar fitr-service-auth-1.0-SNAPSHOT-standalone.jar server <path_to_local_config>

Docker Installation
-------------------

Docker Compose is used to create and link two Docker containers:

1. MongoDB instance
2. The Auth microservice

To get this running:

1) Install Docker Toolbox and start Kitematic. This will start a Docker VM in VirtualBox.

2) Configure Docker client to point to correct Docker machine

    eval $(docker-machine env default)

3) Change to the project root directory, then:

    ./gradlew clean oneJar
    docker-compose build
    docker-compose up -d

6) For debugging, use one or more of the following:

    docker ps
    docker logs <container_id>
    docker exec -it <container_id> bash

7) Fetch the Docker VM IP to use when testing the APIs:

    docker-machine ip default

Testing APIs
------------
One the service is running in local development more (or in Docker), you can test as follows:

POST Signup

    curl -k -H "Content-Type: application/json" -X POST -d '{"email": "bob1@gmail.com", "password": "test123"}' https://<host>:8443/signup

POST Login

    curl -k -H "Content-Type: application/json" -X POST -d '{"email": "bob1@gmail.com", "password": "test123"}' https://<host>:8443/login

POST Invalid password

    curl -k -H "Content-Type: application/json" -X POST -d '{"email": "bob1@gmail.com", "password": "test124"}' https://<host>:8443/login
