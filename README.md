# Docker Compose & Kubernetes Workshop

In this workshop, we will work with the Wordsmith application which is the demo project shown at DockerCon EU 2017 and 2018.

NB: Please try to find your own solutions before getting response from the repository.

## Architecture Schema

PNG

## Preamble

The demo app runs across three containers :

- [db](db/Dockerfile) - a Postgres database which stores words

- [api](api/Dockerfile) - a Java REST API which serves words read from the database

- [web](web/Dockerfile) - a Go web application which calls the API and builds words into sentences

## Build and Run in Docker Compose

- The only requirement to build and run the app from source is Docker.

- Clone this repo and use Docker Compose to build all the images.

- Go to the **[.dck8s/compose](.dck8s/compose)** folder.

```shell
cd .dck8s/compose
```

- You can use the new V2 Compose with `docker compose` or the classic `docker-compose` CLI:

```shell
docker compose up --build
```

> Or you can pull pre-built images from Docker Hub using `docker compose pull`.

Check that all 3 containers are running :

```
$ docker-compose ls
NAME                STATUS
compose             running(3)
```

Check the services are up, and you should see output like this:

```
$ docker-compose ps
NAME                COMMAND                  SERVICE             STATUS              PORTS
compose-api-1       "java -Xmx8m -Xms8m …"   api                 running             0.0.0.0:8080->8080/tcp
compose-db-1        "docker-entrypoint.s…"   db                  running             0.0.0.0:5432->5432/tcp
compose-web-1       "/dispatcher"            web                 running             0.0.0.0:9090->80/tcp
```

Tear down all services by executing this command : 

```shell
docker-compose down
```

You should see output like this : 

```
$ docker-compose down
[+] Running 4/4
 ⠿ Container compose-api-1  Removed                                                                                                                                                              0.6s
 ⠿ Container compose-web-1  Removed                                                                                                                                                              0.3s
 ⠿ Container compose-db-1   Removed                                                                                                                                                              0.3s
 ⠿ Network compose_default  Removed  
```

Check again if there are services up : 

```
$ docker-compose ps
NAME                COMMAND                  SERVICE             STATUS              PORTS
```