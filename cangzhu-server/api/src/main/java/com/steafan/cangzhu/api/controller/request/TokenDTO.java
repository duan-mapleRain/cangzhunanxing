package com.steafan.cangzhu.api.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.steafan.cangzhu.api.controller.response.user.UserInfoResponse;
import com.steafan.cangzhu.api.repository.entity.UserDAO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO implements UserDetails {

    private UserDAO userDAO;

    private String token = "";
    private Set<String> permissions;

    private List<SimpleGrantedAuthority> authorities;


    public TokenDTO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public TokenDTO(UserDAO userDAO, Set<String> permissions) {
        this.userDAO = userDAO;
        this.permissions = permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        this.permissions = permissions;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userDAO.getPassword();
    }

    @Override
    public String getUsername() {
        return userDAO.getAccount();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserInfoResponse converter() {
        return new UserInfoResponse(userDAO.getId(), userDAO.getAccount(), userDAO.getStatus());
    }
}
