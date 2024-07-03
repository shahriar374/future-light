# Future Light
To run this project in local environment, clone this repository and open with any [compatible IDE](https://spring.io/tools).

## Prerequisites
- Java >= 17 ([OpenJDK](https://jdk.java.net/22/) / [Oracle JDK](https://www.oracle.com/java/technologies/downloads/))
- [MySQL server](https://dev.mysql.com/downloads/mysql/)
- [MySQL Workbench](https://dev.mysql.com/downloads/workbench/) (Optional)

> Please make sure a JDK and MySQL server is installed on your machine before running this project.

## Running the project in local environment
This project uses **Maven** as build tool. Use maven to resolve all required dependencies. Most of the compatible IDE will automatically resolve these dependencies.

Before running this project, run a MySQL server and import necessary tables and data using `default-schema.sql` file. 

### Importing schema to MySQL
Open MySQL Workbench and run the server. Then create a schema named `futurelight` and run the `default-schema.sql` file.

Executing this file will automatically create necessary tables and an `admin` user with password `password`.

<i id="blocked-port"></i>
> To resolve any port blockage related issue, follow these instructions.([Windows](https://knowledge.informatica.com/s/article/137920?language=en_US) / [Mac](https://pimylifeup.com/macos-kill-process-port/) / [Linux](https://stackoverflow.com/questions/11583562/how-to-kill-a-process-running-on-particular-port-in-linux) )

Then edit the `/src/main/resources/application.properties` file to link up the database with application. Consider the following example:

```java
spring.datasource.url=jdbc:mysql://localhost:3306/futurelight
spring.datasource.username=root
spring.datasource.password=root
```

Here `3306` is the port number which is running the MySQL server. `futurelight` is the name of schema. `root` is the username and password for MySQL server. Change these information according to your own configuration.

Now the application is ready to run in the localhost. The application will run in `http://localhost:8080/` by default. For any kind of port blockage issues consider:
- [Changing the default port of spring boot](https://www.baeldung.com/spring-boot-change-port)
- [Kill the process running in the blocked port](#blocked-port)
