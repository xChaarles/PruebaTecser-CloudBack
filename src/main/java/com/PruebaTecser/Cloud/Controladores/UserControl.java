package com.PruebaTecser.Cloud.Controladores;

import com.PruebaTecser.Cloud.ControladorImpl.UserControlImpl;
import com.PruebaTecser.Cloud.Dto.UserDto;
import com.PruebaTecser.Cloud.Servicios.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControl implements UserControlImpl {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<UserDto> crearuser(@RequestBody UserDto registrar){
        return ResponseEntity.ok(userService.crearUser(registrar));
    }

    @Override
    public ResponseEntity<UserDto> login(@RequestBody UserDto loginDto){
        return ResponseEntity.ok(userService.login(loginDto));
    }

    @Override
    public ResponseEntity<UserDto> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @Override
    public ResponseEntity<UserDto> crearUserAdmin(@RequestBody UserDto registrar){
        return ResponseEntity.ok(userService.crearUser(registrar));
    }
}
