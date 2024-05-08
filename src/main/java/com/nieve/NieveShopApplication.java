package com.nieve;

import com.nieve.config.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class NieveShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(NieveShopApplication.class, args);
	}

}
