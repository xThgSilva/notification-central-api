package com.message.central.filters;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.message.central.entities.User;
import com.message.central.repositories.UserRepository;
import com.message.central.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{
	
	@Autowired
    private JwtService jwt;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

    	String authHeader = request.getHeader("Authorization");

    	String token = null;

    	if (authHeader != null && authHeader.startsWith("Bearer ")) {
    	    token = authHeader.substring(7);
    	}

        if (token != null) {
            try {
                String email = jwt.extractEmail(token);

                User user = userRepository.findByEmail(email).orElse(null);

                if (user != null && jwt.isValid(email, token)) {

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    user,
                                    null,
                                    new ArrayList<>()
                            );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                
            }
        }
        filterChain.doFilter(request, response);
    }
}
