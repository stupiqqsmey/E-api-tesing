package ite_istad_product.product_demo.configSecurity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Value("${keycloak.client-id}")
    private String clientId;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);

        http.formLogin(AbstractHttpConfigurer::disable);

        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // JWT Authentication from Keycloak
        http.oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwt ->
                        jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
                ));

        http.authorizeHttpRequests(request -> request

                // Public APIs
                .requestMatchers("/scalar/**", "/v3/api-docs/**").permitAll()
                .requestMatchers("/api/v1/auth/register").permitAll()
                .requestMatchers("/api/v1/files/**", "/files/**").permitAll()

                .requestMatchers(HttpMethod.GET,
                        "/api/v1/categories/**").permitAll()

                .requestMatchers(HttpMethod.GET,
                        "/api/v1/products/**",
                        "/api/v1/tags/**").permitAll()

                // Admin only
                .requestMatchers("/api/v1/admin/**")
                .hasRole("ADMIN")

                // Everything else requires login
                .anyRequest()
                .authenticated());

        return http.build();
    }

    @Bean
    @SuppressWarnings("unchecked")
    public JwtAuthenticationConverter jwtAuthenticationConverter() {

        Converter<Jwt, Collection<GrantedAuthority>> converter = jwt -> {
            Map<String, Object> resourceAccess =
                    jwt.getClaim("resource_access");
            if (resourceAccess == null) {
                return Collections.emptySet();
            }

            Map<String, Object> clientAccess =
                    (Map<String, Object>) resourceAccess.get(clientId);

            if (clientAccess == null) {
                return Collections.emptySet();
            }

            Object rolesObj = clientAccess.get("roles");

            if (!(rolesObj instanceof Collection<?> roles)) {
                return Collections.emptySet();
            }

            return roles.stream()
                    .map(Object::toString)
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .collect(Collectors.toSet());
        };

        JwtAuthenticationConverter jwtConverter =
                new JwtAuthenticationConverter();

        jwtConverter.setJwtGrantedAuthoritiesConverter(converter);

        return jwtConverter;
    }
}