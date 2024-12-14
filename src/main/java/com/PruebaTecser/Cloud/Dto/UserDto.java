package com.PruebaTecser.Cloud.Dto;

import com.PruebaTecser.Cloud.Entidades.Role;
import com.PruebaTecser.Cloud.Entidades.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {

    private int statuscode;
    private String mensaje;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private Integer id;
    private String name;
    private String street;
    private Date fecha;
    private String email;
    private String password;
    private Role role;
    private User user;
    private List<User> userList;
}
