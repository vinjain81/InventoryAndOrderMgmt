package com.bgarage.autopartsmgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AutoPartsMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoPartsMgmtApplication.class, args);
	}

}
