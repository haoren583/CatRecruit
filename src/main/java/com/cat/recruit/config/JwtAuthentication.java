package com.cat.recruit.config;

import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 自定义Authentication对象
 */
public class JwtAuthentication implements Authentication {

    @Getter
    private final String token;
    private final UserDetails details;
    private boolean authenticated = true;

    public JwtAuthentication(UserDetails details, String token) {
        this.token = token;
        this.details = details;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return details.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getDetails() {
        return details;
    }

    @Override
    public Object getPrincipal() {
        return details;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    /**
     * Returns the name of this principal.
     *
     * @return the name of this principal.
     */
    @Override
    public String getName() {
        return details.getUsername();
    }

}
