package com.PruebaTecser.Cloud.ControladorImpl;

import com.PruebaTecser.Cloud.Dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface UserControlImpl {

    @PostMapping("/admin/registrar")
    public ResponseEntity<UserDto> crearuser(@RequestBody UserDto registrar);

}
