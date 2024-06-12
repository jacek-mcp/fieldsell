package com.jacek.fieldsell.dtos;

public record RegistrationDTO(String username, String password )  {

    public String toString(){
        return "Registration info: username: " + this.username + " password: " + this.password;
    }
}
