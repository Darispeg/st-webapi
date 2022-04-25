package com.example.api_rest.service.role;

import java.util.UUID;

import com.example.api_rest.Model.Role;
import com.example.api_rest.service.GenericService;

public interface RoleService extends GenericService<Role, UUID> {
    Role saveRole(Role role);
}
