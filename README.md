
|Saying Java (Springboot 2) Backend | ![Metrica](src/main/resources/static/metricaLogo.jpg) |
|-------|--------|

Saying es un proyecto tipo CRUD que permite consultar los refranes guardados en memoria así como en base de datos. Tiene fundamentalmente una misión didáctica. 

## Tecnologías/librerías

Existen multitud de tecnologías y conceptos incluidos en el código entre las que destacan:

 * Proyecto Maven para Java 8 (compatible con java 11 modificando el pom.xml)
 * Spring 5 con SpringBoot 2.2
 * Multitud de notaciones Spring (Autowired, Qualifier, RestController...)
 * Spring Data
 * Excepciones mediante ExceptionHandler
 * Expresiones lambda en Java
 * Optional Java
 * Patrones de diseño y de arquitectura (DAO, DTO...)
 * Generación de logs
 * Campos para auditorías
 * Swagger
 * Preparado para guardarse en una imagen docker

## Descarga del proyecto

Para descargar el proyecto la forma más cómoda es disponer de git (con git bash instalado). Es necesario abrir una consola y lanzar el siguiente comando:

```
git clone https://github.com/metricalab/saying.git
```

Una vez descargado el código se podrá importar este mediante el IDE Eclipse o STS (Spring Tool Suite). 

## Importar el proyecto

Es un proyecto maven luego es necesario utilizar la opción: **Import -> Existing Maven Projects** y seleccionar la carpeta **saying** que es la carpeta contiene todo el código del proyecto.

## Antes de lanzar el proyecto

Es necesario disponer de una base de datos para MariaDB que corra en localhost. La aplicación intentará conectar a la base de datos con las siguientes credenciales: 

| Propiedad | Valor |
|-------------------------------|-------------------------------------------------|
| Cadena de conexión utilizada: | jdbc:mysql://localhost/db_refranes?useSSL=false |
| Nombre de base de datos:      | db_refranes                                     |
| Usuario de la bbdd:           | root                                            |
| Password de la bbdd:          | metrica123                                      |

Si se desea cambiar alguno de estos datos es necesario modificar el fichero **application.yml** . Este fichero se encuentra en la ruta:
```
../saying/src/main/resources
```
En la sección
```
spring:
  datasource:
```
Se encuentran los valores de conexión.

Existe un fichero **application-dock.yml** que permite correr la aplicación con el entorno de desarrollo de métrica con Docker. Ver proyecto https://github.com/metricalab/entornoDockerMetrica.

## Lanzar el proyecto

Para lanzar el proyecto es necesarui hacer clic en el proyecto desde el IDE e ir a: **Run As -> Spring Boot App**

El proyecto corre por defecto por el puerto **8290** accediendo al fichero **aplication.yml** se puede cambiar este parámetro

## Uso de la aplicación sin Docker

Si no se va a disponer de Docker es necesario disponer de una base de datos mysql8 corriendo en el ordenador. Es necesario tener creada la base de datos **db_refranes**. Por defecto usado el usuario **root** y la contraseña **metrica123** aunque estos dos últimos parametros se pueden cambiar en el fichero **application.yml**. Se puede usar por ejemplo el programa **xampp** o similar para disponer de la base de datos.

## Uso de la aplicación con Docker

Este software está preparado para correr en **Docker**. 

Requisitos:
- Maven desde línea de comandos
- Docker instalado
- JDK Correctamente instalado para la versión 11
- Entorno de desarrollo con docker de métrica funcionando. Localizado en https://github.com/metricalab/entornoDockerMetrica.

Desde el directorio raiz del proyecto y en un consola (en windows se recomienda usar git bash) Se debe lanzar el comando:

```
mvn clean compile install
```

El proyecto quedará empaquetado en la ruta **/target/**

Para construir la imagen de docker:

```
docker build --build-arg BUILD_DATE=$(date -u +'%Y-%m-%dT%H:%M:%SZ') --build-arg BUILD_VERSION=0.0.1 -t metricadock/metrica-saying:0.0.1 .
```

Para lanzar el contenedor:

```
docker run -e "SPRING_PROFILES_ACTIVE=dock" -p 8290:8290 -t -d --name metrica-saying metricadock/metrica-saying:0.0.1
```

Parar el contenedor:

```
docker stop metrica-saying
```

Borrar el contenedor:

```
docker rm metrica-saying
```

Revisar los logs:

```
docker logs metrica-saying
```

La aplicación correrá sobre el puerto **8290**.

## Uso de Postman

Es recomendable utilizar postman para hacer pruebas sobre las llamadas. Esta aplicación incorpora en la ruta:

```
../saying/src/main/resources/static/postman
```

Un fichero llamado **Metrica-Springboot-Springdata.postman_collection.json** que contiene todas las llamadas posibles realizables tanto en local como en el servidor juanonlab.com . Para hacer uso de este json es necesario abrir postman y en la parte superior derecha del programa hacer clic en **import** y arrastrar este fichero json.

## Para acceder a Swagger

El acceso a swagger se realizaría desde

```
http://localhost:8290/metrica/swagger-ui.html
```

Suponiendo que la aplicación se lance desde local o desde un contenedor docker en local.

