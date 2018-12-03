# Cella

Cella es un sistema de préstamos de material para prácticas de campo para los 
alumnos de la carrera de Biología de la facultad 
de ciencias en la UNAM.


## Integrantes
Eduardo Rubio Lezama 
Jaime
José Carlos Buenrostro Rueda
Rossana Palma
Yessica Janeth Pablo

## Comenzando

El siguiente procedimiento creará una copia del proyecto Cella en tu computadora 
personal para con el propósito de hace pruebas sobre el mismo.

### Prerequisitos

Todas las siguientes indicaciones están hechas en el sistema operativo Windows 10.
Tener propiamente instalado y configurado los siguientes programas

1 JDK 8
2 NetBeans (completo o en su versión JAVA EE) 8.2
3 Postgresql 10.6

### Cargando el proyecto

Hay dos opciones para obtener el proyecto:

* (Clonar el repositorio de la siguiente liga)[https://github.com/EduardoRubioLezama/Cella.git]
   Abrir netbeans, ir a la pestaña File y seguir las opciones 
	File -> Open Project 
   Navegar hasta la carpeta en donde se haya clonado el repositorio.

* (Descargar el comprimido de la siguiente liga)[https://github.com/EduardoRubioLezama/Cella.git]
   Abrir netbeans, ir a la pestaña File y seguir las opciones 
	File -> Open Project 
   Navegar hasta la carpeta en donde se haya descrompimido el archivo .zip que descargaste.

Después de tener el proyecto en tu netbeans deberás de navegar sobre el proyecto
de la siguiente manera:

mi-primer-aplicacion-web -> Other Sources -> src/main/resurces -> sql 

y encontrarás el archivo crear_base_cella.sql abrélo y copia el contenido en 
tu pgAdmin y ejecuta esto creará la base de datos de Cella. Ahora ubícate nuevamente
en el netbeans y en la carpeta src/main/resources/META-INF encontrarás el archivo
persistence.xml 
abrélo y si se muestra la pestaña de Design dirígete a la pestaña de Source
ahora busca la partes siguientes:

```
      <property name="javax.persistence.jdbc.user" value="tu-usuario"/>
      <property name="javax.persistence.jdbc.passwrd" value="tu-contraseña"/>
```
en donde deberás de poner los datos respectivos de tu base de datos, si por ejemplo 
usas el usuario: postgres es lo que deberás de poner en "tu-usuario" 
con contraseña: x eso es lo que deberás de poner en "tu-contraseña" 

## Descargando dependencias

Ubícate en tu netbeans y elige el proyecto mi-primer-aplicacion-web
click derecho y picar: Clean and Build esto descargará todas las dependencias
que necesita el proyecto para correr y debemos de esperar a que termine de descargar
las dependencias.

### Corriendo el proyecto

Una vez descargadas todas las dependencias navegamos en el proyecto 
de la siguiente manera
mi-primer-aplicacion-web -> Project Files -> pom.xml 
click derecho Run Maven -> tomcat7
Esto hará que un servidor apache tomcat versión 7 corra en tu equipo.
Inmediatamente dirígete a tu navegador web y escribe para que puedas comenzar a navegar
localhost:8084/mi-primer-aplicacion-web/faces/Inicio.xhtml

