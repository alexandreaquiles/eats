docker pull openzipkin/zipkin

docker run --rm -d --network eats-network --name zipkin -p 9410:9410 -p 9411:9411 -e RABBIT_URI=amqp://eats:caelum123@localhost:5672 openzipkin/zipkin

