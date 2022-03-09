# Docker Compose & Kubernetes Workshop

In this workshop, we will work with the Wordsmith application which is the demo project shown at DockerCon EU 2017 and 2018.

NB: Please try to find your own solutions before getting response from the repository.

## Architecture Schema

![wordsmith-architcture](wordsmith-architecture.png   )

## Preamble

The demo app runs across three containers :

- [db](db/Dockerfile) - a Postgres database which stores words

- [api](api/Dockerfile) - a Java REST API which serves words read from the database

- [web](web/Dockerfile) - a Go web application which calls the API and builds words into sentences
