package com.PruebaTecser.Cloud.Servicios;

import com.PruebaTecser.Cloud.Dto.UserDto;
import com.PruebaTecser.Cloud.Entidades.Rol;
import com.PruebaTecser.Cloud.Entidades.User;
import com.PruebaTecser.Cloud.JWT.JwtService;
import com.PruebaTecser.Cloud.Repositorios.RoleRepo;
import com.PruebaTecser.Cloud.Repositorios.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Autowired
    private RoleRepo roleRepo;

    public UserDto crearUser(UserDto registrar){
        UserDto userDto = new UserDto();
        try{
            User user = new User();
            user.setNombre(registrar.getNombre());
            user.setStreet(registrar.getStreet());
            user.setImgUrl(registrar.getImgUrl());
            user.setFecha(registrar.getFecha());
            user.setEmail(registrar.getEmail());
            user.setPassword(passwordEncoder.encode(registrar.getPassword()));

            Optional<Rol> rolOpt = roleRepo.findByName(registrar.getRol());
            if (rolOpt.isPresent()){
                user.setRol(rolOpt.get());
            }else {
                userDto.setStatuscode(400);
                userDto.setMensaje("Rol no encontrado o no definido");
            }
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
