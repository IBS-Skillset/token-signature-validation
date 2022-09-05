package com.mystays.tokensignaturevalidation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;

@Order(1)
@EnableWebSecurity
public class TokenSecurityConfig {

    @Value("${publicKey}")
    private String publicKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*return http.oauth2ResourceServer(oauth2 -> oauth2
                        .jwt().jwkSetUri("http://localhost:9000/oauth2/jwks"))
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().build();*/
        return http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey((RSAPublicKey) decodePublicKey((publicKey))).build();
    }

    private static PublicKey decodePublicKey(String publicKey){
        // public key decoding logic
        return null;
    }
}
