package lk.ijse.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TpsAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpsAuthServiceApplication.class, args);
	}

}
