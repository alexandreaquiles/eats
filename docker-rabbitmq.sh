docker pull rabbitmq:3-management

docker run --rm -d --network eats-network --name rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management

