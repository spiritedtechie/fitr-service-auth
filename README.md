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

1. Install MongoDB (easiest way is as a Docker container e.g. via Kitematic)

2. Configure config-local.yml with MongoDB host, port and database name

3. Run the service:

    gradle run

Build Distribution
------------------

There are two options here:

1) Create a zip distribution

    gradle build
    cd build/distributions
    unzip fitr-service-auth-1.0-SNAPSHOT.zip
    cd fitr-service-auth-1.0-SNAPSHOT
    ./bin/fitr-service-auth server <path_to_config>

2) Create an uber jar

    gradle oneJar
    cd build/libs
    java -jar fitr-service-auth-1.0-SNAPSHOT-standalone.jar server <path_to_config>

Docker Installation
-------------------

1) Install Docker Toolbox, which will run and manage containers on a VirtualBox VM.

2) Install a MongoDB container via Kitematic.

3) Configure config-docker.yml with MongoDB host, port and database name.

4) Open Docker CLI from Kitematic.

5) In the project root directory:

    ./gradlew clean oneJar
    docker build -t spiritedtechie/fitr-service-auth .
    docker run -d -p 8443:8443 spiritedtechie/fitr-service-auth

6) For debugging, use one or more of the following:

    docker ps
    docker logs <container_id>
    docker exec -it <container_id> bash

Testing APIs
------------
One the service is running in local development more (or in a Docker container), you can test as follows:

POST Signup

    curl -k -H "Content-Type: application/json" -X POST -d '{"email": "bob1@gmail.com", "password": "test123"}' https://<host>:8443/signup

POST Login

    curl -k -H "Content-Type: application/json" -X POST -d '{"email": "bob1@gmail.com", "password": "test123"}' https://<host>:8443/login

POST Invalid password

    curl -k -H "Content-Type: application/json" -X POST -d '{"email": "bob1@gmail.com", "password": "test124"}' https://<host>:8443/login
