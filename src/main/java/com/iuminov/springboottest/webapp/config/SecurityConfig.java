package com.iuminov.springboottest.webapp.config;

import com.iuminov.springboottest.webapp.entity.UserAccount;
import com.iuminov.springboottest.webapp.repository.UserManagementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.stream.Stream;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;

@Configuration
public class SecurityConfig {

    /*@Bean
    public UserDetailsService userDetailsService() {
        UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser( User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build());
        userDetailsManager.createUser(User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("password")
                        .roles("ADMIN")
                        .build());

        return userDetailsManager;
    }*/

    @Bean
    public UserDetailsService userService(UserManagementRepository repository) {
        return username -> repository.findByUsername(username)
                .asUser();
    }

    /*@Bean
    public SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                .requestMatchers("/resources/**", "/about", "/login")
                .permitAll()
                .requestMatchers(HttpMethod.GET, "/admin/**")
                .hasRole("ADMIN")
                .requestMatchers("/db/**").access((authentication, object) -> {
                    boolean anyMissing = Stream.of("ADMIN", "DBA")
                            .map(role -> hasRole(role)
                                    .check(authentication, object).isGranted())
                            .filter(granted -> !granted)
                            .findAny()
                            .orElse(false);
                    return new AuthorizationDecision(!anyMissing);
                })
                .anyRequest().denyAll());

        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }*/

    @Bean
    public CommandLineRunner initUsers(UserManagementRepository repository) {
        return args -> { repository.save(new UserAccount("user", "password", "ROLE_USER"));
            repository.save(new UserAccount("admin", "password", "ROLE_ADMIN"));
        };
    }
}
