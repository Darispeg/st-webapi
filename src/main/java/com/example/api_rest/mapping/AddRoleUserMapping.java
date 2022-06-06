package com.example.api_rest.mapping;

import lombok.Getter;

import java.util.UUID;

@Getter
public class AddRoleUserMapping {
    private UUID userKey;
    private UUID roleKey;
}
