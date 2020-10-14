# ziro-todo-backend

Default property config, please update DB props as needed

server.port=9090

spring.jpa.hibernate.ddl-auto=update

spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/ziro_db?createDatabaseIfNotExist=true

spring.datasource.username=root

spring.datasource.password=admin
