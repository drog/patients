package com.diego.rest.restExternal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


/*
 * Use this for external tomcat
 */

//@SpringBootApplication
//public class RestExternalApplication extends SpringBootServletInitializer {
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(RestExternalApplication.class);
//    }
//
//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(RestExternalApplication.class, args);
//    }
//
//}


 
//  Use this block code for embedded tomcat 
@SpringBootApplication
public class RestExternalApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestExternalApplication.class, args);
	}
}
 

