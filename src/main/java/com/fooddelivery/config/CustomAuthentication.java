package com.fooddelivery.config;


import com.fooddelivery.components.user.entity.User;
import com.fooddelivery.components.user.repository.UserRepository;
import com.fooddelivery.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;



@Component
public class CustomAuthentication implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
       User user = userRepository.findByEmail(name).orElseThrow(() -> new ResourceNotFoundException("Not found user"));

        return new UsernamePasswordAuthenticationToken(
                name, password, user.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
