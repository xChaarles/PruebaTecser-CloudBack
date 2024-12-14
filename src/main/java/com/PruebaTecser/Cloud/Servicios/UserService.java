package com.PruebaTecser.Cloud.Servicios;

import com.PruebaTecser.Cloud.Dto.UserDto;
import com.PruebaTecser.Cloud.Entidades.User;
import com.PruebaTecser.Cloud.JWT.JwtService;
import com.PruebaTecser.Cloud.Repositorios.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto CrearUser(UserDto registrar){
        UserDto userDto = new UserDto();
        try{
            User user = new User();
            user.setName(registrar.getName());
            user.setStreet(registrar.getStreet());
            user.setFecha(registrar.getFecha());
            user.setEmail(registrar.getEmail());
            user.setPassword(passwordEncoder.encode(registrar.getPassword()));
            user.setRole(registrar.getRole());

            User result = userRepo.save(user);

            if (result.getId()>0){
                userDto.setUser(result);
                userDto.setStatuscode(200);
                userDto.setMensaje("Registro Exitoso");
            }else {
                userDto.setStatuscode(404);
                userDto.setMensaje("Ocurrio un error al realizar el registro");
            }
        }catch (Exception e){
            userDto.setStatuscode(500);
            userDto.setMensaje("Ocurrio un error: "+ e.getMessage());
        }
        return userDto;
    }
}
