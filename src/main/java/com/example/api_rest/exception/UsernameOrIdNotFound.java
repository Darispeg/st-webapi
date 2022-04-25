package com.example.api_rest.exception;

public class UsernameOrIdNotFound extends Exception{
    public UsernameOrIdNotFound(){
        super("Usuario o Id no encontrado");
    }

    public UsernameOrIdNotFound(String message){
        super(message);
    }
}
