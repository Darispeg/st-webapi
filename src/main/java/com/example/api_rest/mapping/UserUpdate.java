package com.example.api_rest.mapping;

public class UserUpdate
{
    private String fullname;
    private String username;
    private String phone;
    private String address;
    private String email;

    private String status;

    public String getStatus() {
        return status;
    }

    public String getName() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
