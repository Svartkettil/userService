# Spring Boot Application -User Microservice

This is a sample Spring Boot application that creates, manages, saves and presents a registry of users. The different users contains:

- username
- age
- biography
- userimage

Runs on port 8081

## Getting Started
Start a mySQL docker container and run it's image:

```
docker compose --build up
```

## Endpoints
The following endpoints are available:

Endpoint: `POST /users`
Request Parameters:

- user - The .json file to upload 
- imageFile - The image file to upload


Endpoint: `GET /users/{id}`


returns the username of the id

Endpoint: `GET /users/{id}/image`

returns the userimage of the id

## Responses
- 200 OK if there is a user or an image to return
- 500 INTERNAL SERVER ERROR if there is no matching id
- 500 INTERNAL SERVER ERROR if trying to add a different file than:


- jpg
- jpeg
- png
- webp
- avif
- gif
- svg
