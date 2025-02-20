package com.example.Config;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtValidator extends OncePerRequestFilter {
	
	private final SecretKey key;

    public JwtValidator(SecretKey key) {
        this.key = key;
    }

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
//		String jwt = request.getHeader("Authorization");
		String jwt = request.getHeader(JwtConstant.JWT_HEADER);
		
		if(jwt != null) {
			jwt = jwt.substring(7);//bearer + 1
			
			 try {
	                
				 Claims claims = Jwts.parserBuilder()
					        .setSigningKey(this.key) // Use the injected key
					        .build()
					        .parseClaimsJws(jwt)
					        .getBody();

	                
	                String email = String.valueOf(claims.get("email"));
	                String authorities = String.valueOf(claims.get("authorities"));
	                
	                List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
	                
	                
	                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, null, auths);
	                
	                
	                SecurityContextHolder.getContext().setAuthentication(authentication);
	                
	            } catch (ExpiredJwtException e) {
	                
	                throw new BadCredentialsException("Token has expired.");
	            } catch (Exception e) {
	                
	                throw new BadCredentialsException("Invalid token.");
	            }
	        }
		
		filterChain.doFilter(request, response);
		
		
		
	}
	


}
