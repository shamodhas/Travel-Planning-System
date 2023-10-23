package lk.ijse.apigateway.config;

import lk.ijse.apigateway.filter.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/21/2023
 * Time : 11:39 PM
 */

@Configuration
public class GatewayConfig {
    @Bean
    public RestTemplate template(){
        return new RestTemplate();
    }
}
