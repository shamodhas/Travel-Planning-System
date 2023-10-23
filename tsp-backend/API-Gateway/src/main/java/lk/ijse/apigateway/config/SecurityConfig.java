package lk.ijse.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/23/2023
 * Time : 5:30 PM
 */

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/secure/").authenticated();
                    auth.anyRequest().permitAll();
                })

//                .authorizeHttpRequests(auth -> {
//                    auth.requestMatchers("/public/**").permitAll(); // user pass no need
//                    auth.anyRequest().authenticated();
//                })
                .build();
//        Basic dXNlcjo3ZDRjYWY0NC0yMjIxLTRhMGEtYWQxNS1hMDMwODVjNjIyMDU=
    }
}
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
////
////    @Bean
////    public UserDetailsService userDetailsService() {
////        UserDetails user = User
////                .withUsername("user")
////                .password("password")
////                .roles("USER")
////                .build();
////        return new InMemoryUserDetailsManager(user);
////    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.authorizeHttpRequests(auth -> {
//            auth.requestMatchers("/public/**").permitAll(); // user pass no need
//            auth.anyRequest().authenticated();
//        }).build();
////        http
////                .authorizeRequests(authorizeRequests ->
////                        authorizeRequests
////                                .requestMatchers("/public/**").permitAll()
//////                                .requestMatchers("/secure/**").authenticated()
////                                .anyRequest().authenticated()
////                )
////                .httpBasic(withDefaults());
////        return http.build();
//    }
//}
