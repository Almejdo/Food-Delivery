package com.fooddelivery.auth;

import com.fooddelivery.components.user.entity.User;
import com.fooddelivery.components.user.service.UserService;
import com.fooddelivery.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

@Configuration
public class TesFilter extends OncePerRequestFilter {
    @Autowired
    private JwtDecoder jwtDecoder;
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println("Auth header " + authorizationHeader);
        if (authorizationHeader == null || authorizationHeader.isEmpty()) {
            filterChain.doFilter(request, response);
        } else {
            authorizationHeader = authorizationHeader.replace("Bearer ", "");
            System.out.println("Auth header " + authorizationHeader);
            try {

                Map<String, Object> claims = decodeJwt(authorizationHeader);
                System.out.println("Auth header " + claims.toString());
                String username = (String) claims.get("sub");
                System.out.println("Username " + username);
                User userDetails = userService.loadUserByEmail(username);
                Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
                System.out.println("JEMI KTU");
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails
                        , userDetails.getPassword()
                        , authorities);
                auth.setDetails( new WebAuthenticationDetailsSource().buildDetails(request));
                // Authentication auth = new UsernamePasswordAuthenticationToken(username, new WebAuthenticationDetailsSource().buildDetails(request), null);
                SecurityContextHolder.getContext().setAuthentication(auth);
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
                filterChain.doFilter(request, response);
            }
        }
    }

    private Map<String, Object> decodeJwt(String jwt) {
        return jwtDecoder.decode(jwt).getClaims();
    }
}
