# intercorp-task
Project Task from Intercorp Retail API Spring Boot and Deploy in AWS + Docker

Resources in use AWS:
VPC, Subnets, Security Group, IGW, Docker, Docker-Hub, EC2, S3, etc.


#Detalle:
API proy-client-service-intercorp, tiene como alcance lo siguiente:
- Registro de Cliente.
- Calculo de Promedio de Edad y Desviacion Estandar.
- Listar información de los clientes + Fecha probale de mortalidad.


#Tecnologias Aplicadas:
- Spring Boot
- Swagger 2.x
- RxJava
- Lombok
- Thymeleaf
- CircuitBreaker
- Dockerfile


#Consideraciones para Pruebas:
- Utilizar un cliente Rest con soporte a respuesta reactivas.
- Con POSTMAN o CLIENT-REST: 
- Metodo 1 y 2, se podran probar las 2 primeras funcionalidades.
- Para el metodo 3 se recomienda invocarlo directamente desde un browser directamente. (Chorme, Internet, etc)


### Information Use API:

URL Base:	http://18.233.159.234

|Type Method	| Resource	  
-------------------------------------------------
|POST         | /creacliente        
|GET	        | /kpideclientes
|GET          |	/listclientes

GitHub	https://github.com/denisusmpWS/intercorp-task.git
