# Cella

Cella es un sistema de pr�stamos de material para pr�cticas de campo para los 
alumnos de la carrera de Biolog�a de la facultad 
de ciencias en la UNAM.


## Integrantes
Eduardo Lezama Rubio
Jaime
Jos� Carlos Buenrostro Rueda
Rossana Palma
Yessica Janeth Pablo

## Comenzando

El siguiente procedimiento crear� una copia del proyecto Cella en tu computadora 
personal para con el prop�sito de hace pruebas sobre el mismo.

### Prerequisitos

Todas las siguientes indicaciones est�n hechas en el sistema operativo Windows 10.
Tener propiamente instalado y configurado los siguientes programas

1 JDK 8
2 NetBeans (completo o en su versi�n JAVA EE) 8.2
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

Despu�s de tener el proyecto en tu netbeans deber�s de navegar sobre el proyecto
de la siguiente manera:

mi-primer-aplicacion-web -> Other Sources -> src/main/resurces -> sql 

y encontrar�s el archivo crear_base_cella.sql abr�lo y copia el contenido en 
tu pgAdmin y ejecuta esto crear� la base de datos de Cella. Ahora ub�cate nuevamente
en el netbeans y en la carpeta src/main/resources/META-INF encontrar�s el archivo
persistence.xml 
abr�lo y si se muestra la pesta�a de Design dir�gete a la pesta�a de Source
ahora busca la partes siguientes:

```
      <property name="javax.persistence.jdbc.user" value="tu-usuario"/>
      <property name="javax.persistence.jdbc.passwrd" value="tu-contrase�a"/>
```
en donde deber�s de poner los datos respectivos de tu base de datos, si por ejemplo 
usas el usuario: postgres es lo que deber�s de poner en "tu-usuario" 
con contrase�a: x eso es lo que deber�s de poner en "tu-contrase�a" 

## Descargando dependencias

Ub�cate en tu netbeans y elige el proyecto mi-primer-aplicacion-web
click derecho y picar: Clean and Build esto descargar� todas las dependencias
que necesita el proyecto para correr y debemos de esperar a que termine de descargar
las dependencias.

### Corriendo el proyecto

Una vez descargadas todas las dependencias navegamos en el proyecto 
de la siguiente manera
mi-primer-aplicacion-web -> Project Files -> pom.xml 
click derecho Run Maven -> tomcat7
Esto har� que un servidor apache tomcat versi�n 7 corra en tu equipo.
Inmediatamente dir�gete a tu navegador web y 
```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc

