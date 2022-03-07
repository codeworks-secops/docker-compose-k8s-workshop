# Docker Compose & Kubernetes Wordsmith Demo

Wordsmith is the demo project shown at DockerCon EU 2017 and 2018.

The demo app runs across three containers:

- [db](db/Dockerfile) - a Postgres database which stores words

- [words](api/Dockerfile) - a Java REST API which serves words read from the database

- [web](web/Dockerfile) - a Go web application which calls the API and builds words into sentences

## Build and Run in Docker Compose

- The only requirement to build and run the app from source is Docker.

- Clone this repo and use Docker Compose to build all the images.

- You can use the new V2 Compose with `docker compose` or the classic `docker-compose` CLI:

```shell
docker compose up --build
```

> Or you can pull pre-built images from Docker Hub using `docker compose pull`.

## Deploy Using a Kubernetes Manifest



