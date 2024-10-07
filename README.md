# Reto Operación Fuego de Quasar

## Vista de Capas

![Vista de Capas](https://github.com/jcvillotab/fireQuasarOperation/blob/main/images/Paquetes.png?raw=true)

En esta vista encontramos todas las capas que componen la solución del reto, cada una de ella agrupa un conjunto de clases con responsabilidades comunes.

* Controllers: Contiene los controladores de la aplicación, los cuales se encargan de recibir las peticiones HTTP.
* Services: Contiene los servicios de la aplicación, los cuales se encargan de la lógica de negocio para localizar la posición de la nave y el reorganizado del mensaje.
* Repositories: Contiene los repositorios de la aplicación, los cuales se encargan de la persistencia de los datos en la base de datos escogida (H2).
* Entities: Contiene las entidades de la aplicación, las cuales representan los objetos de la aplicación.
* Exceptions: Contiene las excepciones personalizadas de la aplicación, las cuales seran lanzados desde los servicios y controlados por un manejador de excepciones global. En esta capa tambien encontraremos el manejador de excepciones global.
* Utils: Contiene las clases de utilidad de la aplicación, las cuales contienen métodos que se pueden utilizar en toda la aplicación, las constantes y la utilidad para la carga inicial de los datos en la base de datos.

## Vista de Despliegue

![Vista de Despliegue](https://github.com/jcvillotab/fireQuasarOperation/blob/main/images/Despliegue.png?raw=true)

En esta vista encontramos el despliegue de la aplicación, la cual se encuentra desplegada en el siguiente enlace http://meli-test-quasar-alb-711465552.us-east-1.elb.amazonaws.com

Esta aplicación se encuentra despllegada en un ambiente de AWS, en el cual se encuentra un balanceador de carga que distribuye el tráfico los contenedores que estan desplegados en un cluster de ECS(Elastic Container Service) utilizando fargate. Inicialmente solo se desplegara un contenedor en una zona de disponibilidad, pero se encuentra habilitado el autoescalamiento para que en caso de que la carga aumente se desplieguen mas contenedores en las otras zonas de disponibilidad. 

Debido al uso de una base de datos en memoria (H2) se debera activar las sesiones persistentes en el balanceador de carga para que las peticiones de un mismo usuario sean dirigidas al mismo contenedor, pudiendo asi cumplir con los requerimientos de nivel 3.

## Endpoints

### Nivel 1 - Nivel 2

#### POST /topsecret

Este endpoint recibe un JSON con la información de los satélites y la distancia a la nave, y retorna la posición de la nave y el mensaje reorganizado en caso de que el payload sea valido, en otros casos retornara el mensaje de error correspondiente con un status 404.

Request Body:
```json
{
    "satellites": [
        {
            "name": "kenobi",
            "distance": 100.0,
            "message": ["este", "", "", "mensaje", ""]
        },
        {
            "name": "skywalker",
            "distance": 115.5,
            "message": ["", "es", "", "", "secreto"]
        },
        {
            "name": "sato",
            "distance": 142.7,
            "message": ["este", "", "un", "", ""]
        }
    ]
}
```
Response Body:
```json
{
    "position": {
        "x": -58.3152525868874,
        "y": -69.55141837268692
    },
    "message": "este es un mensaje secreto"
}
```
### Nivel 3

#### POST /topsecret_split/{satellite_name}

Este endpoint recibe el nombre del satélite y la información de la distancia a la nave y el mensaje, y retorna un 200 OK si la información fue almacenada correctamente.

Request Body:
```json
{
    "distance": 100.0,
    "message": ["este", "", "", "mensaje", ""]
}
```

#### GET /topsecret_split

Este endpoint retorna la posición de la nave y el mensaje reorganizado en caso de que la información de los satélites almacenada sea suficiente para ser calculada, en otros casos retornara el mensaje de error correspondiente con un status 404.

Response Body:
```json
{
    "position": {
        "x": -58.3152525868874,
        "y": -69.55141837268692
    },
    "message": "este es un mensaje secreto"
}
```

## Ejecución Local

Para ejecutar la aplicación localmente se debe clonar el repositorio y ejecutar el siguiente comando en la raiz del proyecto:

```bash
docker build -t fire-quasar-operation .
docker run -dp 8080:8080 fire-quasar-operation 
```







