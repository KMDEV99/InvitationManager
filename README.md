# Invitation Manager

## Table of content
* [General Information](#general-information)
* [Requirements](#Requirements)
* [Get started](#get-started)
* [Sample Backend Requests](#Sample-Requests)


## General Information
Contains basic admin management panel done in React. I generally use Angular, and it shows. 

Decided to try it anyway.

## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Maven 3.8.6](https://maven.apache.org)
- [npm 8.1.2](https://www.npmjs.com/package/npm)

## Get started

## Run backend via Maven or Docker:

Caution, when using Windows cmd replace `./mvnw` with `mvnw`.

### Maven
    ./mvnw package
    ./mvnw spring-boot:run

### Docker (Make sure docker daemon is running)
    ./mvnw package
    docker build -f Dockerfile -t invitation-manager:invitation-manager .
    docker run -p 8080:8080 <image_id> (You can get it with `docker images`)

### Run Unit Tests
    ./mvnw test
## Emails
#### To enable email notifications, application.properties (username & password) need to be set.

## Run React Frontend
### Change current directory to ui
    cd invitation-manager-ui

### Install npm packages
Install the `npm` packages described in the `package.json` and verify that it works:

      npm install
      npm start

## Sample Requests
```
===========INVITATION===========
GET: http://localhost:8080/api/invitation => List of invitations
GET: http://localhost:8080/api/invitation/{invitationId} => Invitation or error if doesn't exist
POST: http://localhost:8080/api/invitation
Body:
    {
        "inviteeEmail": "matkonrad99@gmail.com",
        "description": "Birthday Party Meeting",
        "inviterEmail": "jankowalh4ck3r@protonmail.com"
    }
PUT: http://localhost:8080/api/invitation/accept/{invitationId}
PUT: http://localhost:8080/api/invitation/decline/{invitationId}

==============USER==============
GET: http://localhost:8080/api/user => List of users and their invitations
GET: http://localhost:8080/api/user/1 => User or error if doesn't exist
POST: http://localhost:8080/api/user
Body:
    {
    "firstName": "Kajko",
    "lastName" : "Kokosz",
    "email" : "kajkoKokosz@wp.pl"
    }
```

Shut it down manually with `Ctrl+C`.
