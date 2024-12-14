package com.PruebaTecser.Cloud.Dto;

import com.PruebaTecser.Cloud.Entidades.Rol;
import com.PruebaTecser.Cloud.Entidades.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private int statuscode;
    private String mensaje;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private Integer id;
    private String nombre;
    private String street;
    private String imgUrl;
    private LocalDate fecha;
    private String email;
    private String password;
    private String rol;
    private User user;
    private List<User> userList;

    public UserDto() {
    }

    public UserDto(String email, String expirationTime, LocalDate fecha, Integer id, String imgUrl, String mensaje, String nombre, String password, String refreshToken, String rol, int statuscode, String street, String token, User user, List<User> userList) {
        this.email = email;
        this.expirationTime = expirationTime;
        this.fecha = fecha;
        this.id = id;
        this.imgUrl = imgUrl;
        this.mensaje = mensaje;
        this.nombre = nombre;
        this.password = password;
        this.refreshToken = refreshToken;
        this.rol = rol;
        this.statuscode = statuscode;
        this.street = street;
        this.token = token;
        this.user = user;
        this.userList = userList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
