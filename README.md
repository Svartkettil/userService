# Spring Boot Application - User Microservice

This is a Spring Boot application that creates, manages, saves and presents a registry of users. The different users contains:

- User id
- Username
- Age
- Biography
- Userimage

The application runs on port 8081.

## Getting Started
Start the application together with its dependencies:
```
docker compose up --build
```

## Endpoints
The following endpoints are available:

### `POST /users`

Request body parameters:

- user - The json payload to upload
- imageFile - The image file to upload


### `GET /users/{id}`

Returns the username of the id.

### `GET /users/{id}/image`

Returns the userimage of the id.


### `PUT /users/{id}`

Request body parameter:
- user - The json payload to upload
- imageFile - The image file to upload

Updates the user of the id and returns the username.

### `DELETE /users/{id}`

Deletes the user of the id.

## Responses
- 200 OK if there is a user or an image to return.
- 500 INTERNAL SERVER ERROR if there is no matching id.
- 500 INTERNAL SERVER ERROR if trying to add a different image file than:
  - jpg
  - jpeg
  - png
  - webp
  - avif
  - gif
  - svg
