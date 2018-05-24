# resbar_app

# COMANDOS DOCKER

docker build -t resbardb .

docker run -p 3306:3306 -v resbarvol:/var/lib/mysql --rm --name resbarcont resbardb

docker stop resbarcont

