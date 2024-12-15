package com.PruebaTecser.Cloud.ControladorImpl;

import com.PruebaTecser.Cloud.Dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface UserControlImpl {

    @PostMapping("/auth/registrar")
    public ResponseEntity<UserDto> crearuser(@RequestBody UserDto registrar);

    @PostMapping("/auth/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto loginDto);

    @GetMapping("/adminuser/getallUser")
    public ResponseEntity<UserDto> getAllUser();
}
