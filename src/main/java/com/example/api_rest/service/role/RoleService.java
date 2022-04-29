package com.example.api_rest.service.role;

import java.util.UUID;

import com.example.api_rest.db.entities.Role;
import com.example.api_rest.service.GenericService;

public interface RoleService extends GenericService<Role, UUID> {
    Role saveRole(Role role);
}
