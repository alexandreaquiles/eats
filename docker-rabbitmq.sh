docker pull rabbitmq:3-management

docker run --rm -d --network eats-network --name rabbit -p 15672:15672 -p 5672:5672 -e RABBITMQ_DEFAULT_USER=eats -e RABBITMQ_DEFAULT_PASS=1234  rabbitmq:3-management

