package com.example.api_rest.service.role;

import java.util.UUID;

import com.example.api_rest.db.entities.Role;
import com.example.api_rest.api.RoleRepository;

import com.example.api_rest.service.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, UUID> implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    public CrudRepository<Role, UUID> getDao() {
        return repository;
    }

    @Override
    public Role saveRole(Role role) {
        role.setKey(UUID.randomUUID());
        return getDao().save(role);
    }

}
