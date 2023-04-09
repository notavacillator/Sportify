package com.sportify.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.sportify.filters.JwtRequestFilters;

@EnableWebSecurity
@SuppressWarnings("deprecation")
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtRequestFilters jwtRequestFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
				.antMatchers("/event/{cityId}").permitAll()
				.antMatchers("/event/**").authenticated()
				.and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		http.cors();
	}

//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowedOrigins(Arrays.asList("https://localhost:3000"));
//		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//		config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
////		config.setAllowCredentials(true);
////		config.setMaxAge(Duration.ofMinutes(20));
//
//		source.registerCorsConfiguration("/event/**", config);
//
//		return source;
//	}

}