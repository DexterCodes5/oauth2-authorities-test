package dev.dex.oauth2authorities.security;

import dev.dex.oauth2authorities.oauth2.*;
import dev.dex.oauth2authorities.service.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.provisioning.*;
import org.springframework.security.web.*;

import javax.sql.*;

@Configuration
public class SecurityConfig {
    // For this project the users have to be stored in a database,
    // because the roles should be in a database to be retrieved
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        // I'm using the defaults
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CustomOAuth2UserService oauthUserService,
                                           UserService userService) throws Exception {
        return http
                .authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/oauth/**").permitAll()
                                .requestMatchers("/home/**").authenticated()
                                .requestMatchers("/client/**").hasRole("CLIENT")
                                .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/my-login")
                                .loginProcessingUrl("/authenticate-client")
                                .permitAll()
                )
                .logout(logout -> logout.permitAll())
                .oauth2Login(outh2 ->
                        outh2
                                .loginPage("/my-login")
                                .userInfoEndpoint(userInfoEndpoint ->
                                        userInfoEndpoint.userService(oauthUserService))
                                .successHandler((request, response, authentication) -> {
                                    CustomOAuth2User oauth2User = (CustomOAuth2User) authentication.getPrincipal();
                                    userService.processOAuthPostLogin(oauth2User.getName());
                                    response.sendRedirect("/home");
                                })
                )
                .build();
    }
}
