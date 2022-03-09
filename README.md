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

## Deploy Using a Kubernetes Manifest

You can deploy the same app to Kubernetes using the [Kubernetes manifest](.dck8s/k8s).
That describes the same application in terms of Kubernetes deployments, services and pod specifications.

Apply the manifest using `kubectl`:

Be sure that you are in the right folder [Kubernetes manifest](.dck8s/k8s) : 

```shell
cd .dck8s/k8s
```

And then, execute the following command :

```shell
kubectl apply --recursive -f ./
```
Now browse to [http://localhost:9090](http://localhost:9090) and you will see the same site.

Docker Desktop includes Kuernetes and the [kubectl](https://kubernetes.io/docs/reference/kubectl/overview/) command line,
so you can work directly with the Kube cluster.
Check the services are up, and you should see output like this:

```
$ kubectl get services -l environment=wordsmith-test-env
NAME                TYPE           CLUSTER-IP      EXTERNAL-IP   PORT(S)          AGE
wordsmith-api-svc   ClusterIP      None            <none>        8080/TCP         6m59s
wordsmith-db-svc    ClusterIP      None            <none>        5432/TCP         6m59s
wordsmith-web-svc   LoadBalancer   10.99.166.159   localhost     9191:30149/TCP   6m59s
```

Check the pods are running, and you should see one pod each for the database and web components,
and five pods for the words API - which is specified as the replica count in the manifest file:

```
$ kubectl get pods -l environment=wordsmith-test-env
NAME                                    READY   STATUS    RESTARTS   AGE
wordsmith-api-deploy-6b89d48b7-k469f    1/1     Running   0          14s
wordsmith-api-deploy-6b89d48b7-kmv7p    1/1     Running   0          14s
wordsmith-api-deploy-6b89d48b7-l7pq7    1/1     Running   0          14s
wordsmith-api-deploy-6b89d48b7-s7pgx    1/1     Running   0          14s
wordsmith-api-deploy-6b89d48b7-tsxzl    1/1     Running   0          14s
wordsmith-db-deploy-788b896dcc-tkf65    1/1     Running   0          14s
wordsmith-web-deploy-697c5d565c-m28tf   1/1     Running   0          13s
```
