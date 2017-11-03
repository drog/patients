# CRUD Patients

Small Project with Spring boot + exposing services REST + SQLite + angularjs in client side.
This project was built based in the next tutorial http://websystique.com/spring-boot/spring-boot-angularj

## Libraries used
##### Server Side
- [Spring boot v1.5.8](https://github.com/spring-projects/spring-boot)
- [org.xerial/sqlite-jdbc 3.20.0](https://bitbucket.org/xerial/sqlite-jdbc)

##### Client Side
- [Angularjs v1.5.8](https://github.com/angular/angular.js)
- [Angular-ui router 0.3.1](https://github.com/angular-ui/ui-router)
- [ngstorage 0.3.10](https://github.com/gsklee/ngStorage)
- [angular-base64-upload](https://github.com/adonespitogo/angular-base64-upload)
- [Bootstrap 3.3.2](https://github.com/twbs/bootstrap)

## Execute Application
1. Download zip or clone from github `git clone https://github.com/drog/patients.git`
2. Go to parent folder 
3. Create a executable jar `mvn package`
4. Start the application `java -jar target/restExternal-0.0.1-SNAPSHOT.jar`
5. The Application will start in  http://localhost:7777

Aditionally you can run the application in Eclipse or IntelliJ IDEA running the class main  **RestExternalApplication**

## Configuration
You can edit the configuration file in `src\main\resources\application.properties` and change the port, security, whatever

```
app.name=Pacientes App

spring.profiles.active=dev
#comment above profile and uncoment line below to operate using https
#spring.profiles.active=https
#spring.mvc.view.prefix: /WEB-INF/jsp/
#spring.mvc.view.suffix: .jsp
server.port=7777
#server.context-path=/v1

spring.jpa.database-platform=com.diego.rest.restExternal.config.SQLiteDialect
#spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=false
spring.jpa.hibernate.naming-strategy=org.hibernate.cfg.EJB3NamingStrategy

management.security.enabled=true
```

#### DOCS
- https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/	
	
	
#### More Info
- http://blog.codeleak.pl/2014/10/spring-boot-actuator-custom-endpoint.html
- http://javasampleapproach.com/java-integration/integrate-angular-4-springboot-web-app-springtoolsuite		
- http://www.baeldung.com/spring-boot-application-configuration
