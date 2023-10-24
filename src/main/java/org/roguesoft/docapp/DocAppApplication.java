package org.roguesoft.docapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "org.roguesoft.docapp", "com.roguesoft.apiexception" })
public class DocAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocAppApplication.class, args);
	}

}
