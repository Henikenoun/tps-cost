package com.example.demos.role;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(Integer id) {
        super("Role not found: " + id);
    }
}
