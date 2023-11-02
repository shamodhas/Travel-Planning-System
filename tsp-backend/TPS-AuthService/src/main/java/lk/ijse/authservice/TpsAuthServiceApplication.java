package lk.ijse.authservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 11/2/2023
 * Time : 5:19 PM
 */

@SpringBootApplication
@EnableDiscoveryClient
public class TpsAuthServiceApplication {
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(TpsAuthServiceApplication.class, args);
	}

}
