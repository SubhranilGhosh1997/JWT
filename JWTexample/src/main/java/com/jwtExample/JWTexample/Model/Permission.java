package com.jwtExample.JWTexample.Model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:get"),
    ADMIN_CREATE("admin:post"),
    ADMIN_UPDATE("admin:put"),
    ADMIN_DELETE("admin:delete"),
    MANAGER_READ("manager:get"),
    MANAGER_CREATE("manager:post"),
    MANAGER_UPDATE("manager:put"),
    MANAGER_DELETE("manager:delete")
    ;
    private final String permission;
}
