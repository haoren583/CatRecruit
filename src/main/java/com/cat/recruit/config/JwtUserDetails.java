package com.cat.recruit.config;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author 钟健裕
 */
@Builder
@Setter
public class JwtUserDetails implements UserDetails {

    @Getter
    private Integer id;

    private String username;

    private String password;

    private Collection<GrantedAuthority> authorities;

    @Getter
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}
