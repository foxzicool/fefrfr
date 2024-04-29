package com.example.demo.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

public class JwtAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        Authentication authentication = performJwtValidation((HttpServletRequest) request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private Authentication performJwtValidation(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7); // Remove "Bearer " prefix

            // Decode and validate the token
            // This is just a placeholder. You need to implement your own token validation
            // logic.
            if (isValidToken(jwtToken)) {
                // Create an authentication token based on the valid JWT
                return new UsernamePasswordAuthenticationToken(
                        getUsernameFromToken(jwtToken), null, getAuthoritiesFromToken(jwtToken));
            }
        }
        return null;
    }

    private boolean isValidToken(String jwtToken) {
        // Implement your JWT validation logic here
        // You would typically call a JWT library to verify the token
        return true;
    }

    private String getUsernameFromToken(String jwtToken) {
        // Extract the username from the token
        // You would typically decode the JWT and get the username from the payload
        return "username";
    }

    private Collection<? extends GrantedAuthority> getAuthoritiesFromToken(String jwtToken) {
        // Extract the roles/authorities from the token
        // You would typically decode the JWT and get the roles from the payload
        return Collections.emptyList();
    }
}
