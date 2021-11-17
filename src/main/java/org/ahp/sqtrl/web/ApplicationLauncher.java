package org.ahp.sqtrl.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan
@SpringBootApplication
public class ApplicationLauncher {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationLauncher.class, args);
	}
}



