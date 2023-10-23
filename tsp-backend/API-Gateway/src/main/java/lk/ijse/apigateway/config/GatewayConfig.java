package lk.ijse.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/21/2023
 * Time : 11:39 PM
 */

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("adminService", predicateSpec -> predicateSpec
                        .path("/admin/**")
                        .uri("http://localhost:8083/admin")
                )
                .route("bookingService", predicateSpec -> predicateSpec
                        .path("/booking/**")
                        .uri("http://localhost:8084/booking")
                )
                .route("guideService", predicateSpec -> predicateSpec
                        .path("/guide/**")
                        .uri("http://localhost:8085/guide")
                )
                .route("hotelService", predicateSpec -> predicateSpec
                        .path("/hotel/**")
                        .uri("http://localhost:8086/hotel")
                )
                .route("packageService", predicateSpec -> predicateSpec
                        .path("/package/**")
                        .uri("http://localhost:8087/package")
                )
                .route("userService", predicateSpec -> predicateSpec
                        .path("/user/**")
                        .uri("http://localhost:8088/user")
                )
                .route("vehicleService", predicateSpec -> predicateSpec
                        .path("/vehicle/**")
                        .uri("http://localhost:8089/vehicle")
                )
                .build();
    }
}
