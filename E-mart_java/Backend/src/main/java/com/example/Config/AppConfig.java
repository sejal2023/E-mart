package com.example.Config;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.security.Keys;

@Configuration
public class AppConfig {
	
	@Value("${jwt.secret}") 
    private String jwtSecret;

    @Bean
    public SecretKey jwtSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes()); 
    }
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, SecretKey jwtSecretKey) throws Exception{
		

   	 http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
        		.requestMatchers("/api/payments/**").authenticated()
//            .anyRequest().permitAll()
            .anyRequest().permitAll()
        );
   	 
   	 
   	 //check karo koi bhi method run karnese pehla ki validate hai ki nahi
   	 http.addFilterBefore(new JwtValidator(jwtSecretKey), BasicAuthenticationFilter.class);
   	 
//   	 http.authenticationProvider(daoAuthenticationProvider());
//   	 DefaultSecurityFilterChain build=http.build();
   	 return http.build();

		
		
	}
	
	
	@Bean
	public PasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder(); //hash and compare karne ki method milegi
		
	}
	

}