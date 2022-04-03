package com.datahub.Datahubtestserver;

import com.datahub.Datahubtestserver.controllers.ConfigController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class DatahubTestServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatahubTestServerApplication.class, args);
	}

}
