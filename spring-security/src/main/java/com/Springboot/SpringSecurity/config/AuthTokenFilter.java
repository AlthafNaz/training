package com.Springboot.SpringSecurity.config;

import com.Springboot.SpringSecurity.service.JWTUtils;
import com.Springboot.SpringSecurity.service.UserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Retrieve the Authorization header from the request
        final String authorizationHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String userEmail = null;

        try {
            if (authorizationHeader != null && !authorizationHeader.isBlank() && authorizationHeader.startsWith("Bearer ")) {
                jwtToken = authorizationHeader.substring(7);
                userEmail = jwtUtils.extractUsername(jwtToken);
                log.info("JWT Token extracted: {}" , jwtToken);
                log.info("User email extracted from JWT Token: {}", userEmail);
            }

            // Check if the username is extracted and no authentication is set in the security context
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Load user details using the username
                UserDetails userDetails = userDetailService.loadUserByUsername(userEmail);
                log.info("User details loaded for user: {}", userEmail);

                // Validate the token
                if (jwtUtils.isTokenValid(jwtToken, userDetails)) {
                    // Create an authentication token
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    // Set authentication details in the token
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // Set the authentication token in the security context
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    log.info("Authentication token created and set in security context for user: {}", userEmail);
                }else {
                    log.warn("Invalid JWT token for user: {}", userEmail);
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while processing the authentication token", e);
            e.printStackTrace();
        }

        // next filter in the chain
        filterChain.doFilter(request, response);
    }
}
