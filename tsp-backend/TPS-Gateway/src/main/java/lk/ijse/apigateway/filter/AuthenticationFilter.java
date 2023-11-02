package lk.ijse.apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/23/2023
 * Time : 11:12 PM
 */

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
//            if (!exchange.getRequest().getURI().getPath().contains("/public")) {
//            // check role and
//                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
//                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"missing authorization header");
//                }
//                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
//                if (authHeader != null && authHeader.startsWith("Bearer ")) {
//                    authHeader = authHeader.substring(7);
//                }
//                try {
//                    // call auth and validation
//                    // template.getForObject("http://IDENTITY-SERVICE//validate?token" + authHeader, String.class);
//
//                } catch (Exception e) {
//                    throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,"un authorized access to application");
//                }
//            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}
