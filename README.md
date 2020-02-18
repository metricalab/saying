
|Refranes | ![Metrica](https://github.com/metricalab/refranes/blob/master/src/main/resources/static/metricaLogo.jpg) |
|-------|--------|

Refranes es un proyecto tipo CRUD que permite consultar los refranes guardados en memoria así como en base de datos. Es un proyecto que tiene fundamentalmente una misión didáctica. 

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

## Descarga del proyecto

Para descargar el proyecto la forma más cómoda es disponer de git (con git bash instalado). Es necesario abrir una consola y lanzar el siguiente comando:

```
git clone https://github.com/metricalab/refranes.git
```

Una vez descargado el código se podrá importar este mediante el IDE Eclipse o STS (Spring Tool Suite). 

## Importar el proyecto

Es un proyecto maven luego es necesario utilizar la opción: **Import -> Existing Maven Projects** y seleccionar la carpeta **refranes** que es la carpeta contiene todo el código del proyecto.

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
../refranes/src/main/resources
```
En la sección
```
spring:
  datasource:
```
Se encuentran los valores de conexión.

## Lanzar el proyecto

Para lanzar el proyecto es necesarui hacer clic en el proyecto desde el IDE e ir a: **Run As -> Spring Boot App**

El proyecto corre por defecto por el puerto **8090** accediendo al fichero **aplication.yml** se puede cambiar este parámetro

## Adaptación a JAVA 11

Este proyecto es compatible con JAVA 11 siempre que se realice una pequeña modificación en el **pom.xml** del proyecto. 
Este fichero está localizado en la raiz de la carpeta (refranes). Es necesario modifcar la sección properties y substituir 8 por 11.
```
	<properties>
		<java.version>11</java.version>
	</properties>
 ```
Después del cambio es necesario relanzar el proyecto.

## Uso de Postman

Es recomendable utilizar postman. Esta aplicación incorpora en la ruta:

```
../refranes/src/main/resources/static/postman
```

Un fichero llamado **Metrica-Springboot-Springdata.postman_collection.json** que contiene todas las llamadas posibles realizables tanto en local como en el servidor juanonlab.com . Para hacer uso de este json es necesario abrir postman y en la parte superior derecha de Postman hacer clic en **import** y arrastrar este fichero json.

