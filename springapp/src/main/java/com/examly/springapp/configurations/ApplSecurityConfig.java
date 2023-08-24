package com.examly.springapp.configurations;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity

public class ApplSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().and().authorizeHttpRequests().antMatchers("/api/register","/api/auth/login","/api/admin/***","/api/organizer/***","/player/***","/team/***").permitAll()
	      //  .and().authorizeHttpRequests().requestMatchers("/api/login").hasAuthority("ADMIN")
//		        .and().authorizeHttpRequests().requestMatchers("/api/organizer/***").hasAuthority("ORGANIZER")
				.anyRequest().authenticated()
				.and().formLogin()
				.and().httpBasic()
				.and()
				.logout((logout) -> logout.permitAll());
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		// UserDetails
		// user=User.withDefaultPasswordEncoder().username("sound").password("123").roles("USER").build();
		return new MyUserDetailsService();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authentictaionProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;

	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("https://8081-dffafdafedbdcbadebfefaccdcffceeecaffac.premiumproject.examly.io")); // Allow your frontend's origin
	    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
	    configuration.setAllowedHeaders(Arrays.asList("*"));
	    configuration.setAllowCredentials(true);

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}
	    
}
