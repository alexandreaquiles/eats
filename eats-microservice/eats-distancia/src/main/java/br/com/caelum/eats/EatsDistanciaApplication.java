package br.com.caelum.eats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EatsDistanciaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EatsDistanciaApplication.class, args); 
	}

}
