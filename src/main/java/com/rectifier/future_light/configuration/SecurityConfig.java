package com.rectifier.future_light.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	JdbcUserDetailsManager jdbcUserDetailsManager(DataSource datasource) {
		JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(datasource);
		return userDetailsManager;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/").permitAll()
				.requestMatchers("/signup").permitAll()
				.requestMatchers("/signup/save").permitAll()
				.requestMatchers("/css/*").permitAll()
				.requestMatchers("/feather/**").permitAll()
				.requestMatchers("/images/*").permitAll()
				.requestMatchers("/images/favicon/*").permitAll()
				.requestMatchers("/images/lottie/*").permitAll()
				.requestMatchers("/js/*").hasAnyRole("USER", "ADMIN")
				.anyRequest().authenticated())
				.formLogin(login -> login
						.loginPage("/signin")
						.failureUrl("/signin?error")
						.permitAll())
				.logout(logout -> logout
						.logoutSuccessUrl("/signin?logout")
						.permitAll());

		return http.build();
	}

    AuthenticationManager authenticationManager() throws Exception {
        return new AuthenticationConfiguration().getAuthenticationManager();
    }

}
