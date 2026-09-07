package com.zeynep.eTicaretSitesi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ETicaretSitesiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ETicaretSitesiApplication.class, args);
	}

}
