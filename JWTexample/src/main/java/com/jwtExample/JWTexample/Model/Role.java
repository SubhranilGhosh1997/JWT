package com.jwtExample.JWTexample.Model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN(Set.of(
            Permission.ADMIN_READ,
            Permission.ADMIN_CREATE,
            Permission.ADMIN_UPDATE,
            Permission.ADMIN_DELETE,
            Permission.MANAGER_READ,
            Permission.MANAGER_CREATE,
            Permission.MANAGER_UPDATE,
            Permission.MANAGER_DELETE
    )),
    MANAGER(Set.of(
            Permission.MANAGER_READ,
            Permission.MANAGER_CREATE,
            Permission.MANAGER_UPDATE,
            Permission.MANAGER_DELETE
    )),
    USER(Collections.emptySet());

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthority(){
        List<SimpleGrantedAuthority> authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return authorities;
    }
}
