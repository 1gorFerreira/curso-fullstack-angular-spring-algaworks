package com.algaworks.algamoney.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain resourceServerFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/categories").permitAll()
                            .anyRequest()
                            .authenticated();
                })
                .csrf((csrfConfigurer) -> csrfConfigurer.disable())
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .opaqueToken(opaqueToken -> opaqueToken
                                .introspectionUri("http://localhost:8080/oauth2/introspect")
                                .introspectionClientCredentials("angular", "@ngul@r0")));

        return http.build();
    }

}
