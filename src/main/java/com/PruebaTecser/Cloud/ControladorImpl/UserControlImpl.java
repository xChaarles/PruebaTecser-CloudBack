package com.PruebaTecser.Cloud.ControladorImpl;

import com.PruebaTecser.Cloud.Dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
public interface UserControlImpl {

    @PostMapping("/auth/registrar")
    public ResponseEntity<UserDto> crearuser(@RequestBody UserDto registrar);

    @PostMapping("/admin/registrar")
    public ResponseEntity<UserDto> crearUserAdmin(@RequestBody UserDto registrar);

    @PostMapping("/auth/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto loginDto);

    @GetMapping("/adminuser/getallUser")
    public ResponseEntity<UserDto> getAllUser();

    @DeleteMapping("/admin/deleteUser/{id}")
    public ResponseEntity<UserDto> deleteUserById(@PathVariable Integer id);
}
