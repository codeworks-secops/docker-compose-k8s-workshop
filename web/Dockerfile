# BUILD
FROM golang:alpine as builder

COPY dispatcher.go .

RUN go build dispatcher.go

# RUN
FROM alpine

COPY --from=builder /go/dispatcher /

COPY static /static/

EXPOSE 80

CMD ["/dispatcher"]
