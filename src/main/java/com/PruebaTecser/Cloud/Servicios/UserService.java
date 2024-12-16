package com.PruebaTecser.Cloud.Servicios;

import com.PruebaTecser.Cloud.Dto.UserDto;
import com.PruebaTecser.Cloud.Entidades.Rol;
import com.PruebaTecser.Cloud.Entidades.User;
import com.PruebaTecser.Cloud.JWT.JwtService;
import com.PruebaTecser.Cloud.Repositorios.RoleRepo;
import com.PruebaTecser.Cloud.Repositorios.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
            user.setEstado(registrar.getEstado());
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

    public UserDto login (UserDto loginDto){
        UserDto userDto = new UserDto();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(), loginDto.getPassword()));
            var user = userRepo.findByEmail(loginDto.getEmail()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            var jwt = jwtService.generateSecretToken(user);
            var refreshToken =jwtService.generateRefreshToken(new HashMap<>(), user);

            userDto.setStatuscode(200);
            userDto.setToken(jwt);
            userDto.setRol(user.getRol().getName());
            userDto.setNombre(user.getNombre());
            userDto.setEstado(user.getEstado());
            userDto.setEmail(user.getEmail());
            userDto.setImgUrl(user.getImgUrl());
            userDto.setRefreshToken(refreshToken);
            userDto.setExpirationTime("24Hrs");
            userDto.setMensaje("Inicio de Sesion Exitosa");

        }catch (Exception e){
            userDto.setStatuscode(500);
            userDto.setMensaje("Ocurrio un error: "+ e.getMessage());
        }
        return userDto;
    }

    public UserDto getAllUser(){
        UserDto userDto = new UserDto();
        try{
            List<User> userList = userRepo.findAll();
            if (!userList.isEmpty()){
                userDto.setUserList(userList);
                userDto.setStatuscode(200);
                userDto.setMensaje("Lista de Usuario Cargada");
            }else {
                userDto.setStatuscode(400);
                userDto.setMensaje("No tienes Usuarios Registrados");
            }
        }catch (Exception e){
            userDto.setStatuscode(500);
            userDto.setMensaje("Ocurrio un error: "+ e.getMessage());
        }
        return userDto;
    }

    public UserDto deleteUser(Integer id){
        UserDto userDto = new UserDto();
        try{
            Optional<User> userOptional = userRepo.findById(id);
            if (userOptional.isPresent()){
                userRepo.deleteById(id);
                userDto.setStatuscode(200);
                userDto.setMensaje("Usuario Eliminado exitosamente");
            }else {
                userDto.setStatuscode(404);
                userDto.setMensaje("Usuario no encontrado");
            }
        }catch (Exception e){
            userDto.setStatuscode(500);
            userDto.setMensaje("Ocurrio un error: "+ e.getMessage());
        }
        return userDto;
    }

    public UserDto getUserById(Integer id){
        UserDto userDto = new UserDto();
        try {
            User userOptional = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                userDto.setUser(userOptional);
                userDto.setStatuscode(200);
                userDto.setMensaje("Usuario encontrado exitosamente");
        }catch (Exception e){
            userDto.setStatuscode(500);
            userDto.setMensaje("Ocurrio un error: "+ e.getMessage());
        }
        return userDto;
    }

    public UserDto updateUser(Integer id, UserDto updateUser){
        UserDto userDto = new UserDto();
        try {
            Optional<User> userOptional = userRepo.findById(id);
            if (userOptional.isPresent()){
                User userExiste = userOptional.get();
                userExiste.setNombre(updateUser.getNombre());
                userExiste.setStreet(updateUser.getStreet());
                userExiste.setEmail(updateUser.getEmail());
                userExiste.setImgUrl(updateUser.getImgUrl());
                userExiste.setFecha(updateUser.getFecha());
                userExiste.setEstado(updateUser.getEstado());
                Optional<Rol> rolOpt = roleRepo.findByName(updateUser.getRol());
                if (rolOpt.isPresent()){
                    userExiste.setRol(rolOpt.get());
                }else {
                    userDto.setStatuscode(400);
                    userDto.setMensaje("Rol no encontrado o no definido");
                }

                if (updateUser.getPassword() != null && !updateUser.getPassword().isEmpty()){
                    userExiste.setPassword(passwordEncoder.encode(updateUser.getPassword()));
                }

                User saveUser = userRepo.save(userExiste);
                userDto.setUser(saveUser);
                userDto.setStatuscode(200);
                userDto.setMensaje("Usuario actualizado Exitosamente");
            }
        }catch (Exception e){
            userDto.setStatuscode(500);
            userDto.setMensaje("Ocurrio un error: "+ e.getMessage());
        }
        return userDto;
    }

    public UserDto getUsersByNombre(String nombre) {
        UserDto userDto = new UserDto();
        try {
            List<User> usuarios = userRepo.findByNombreContainingIgnoreCase(nombre);
            if (usuarios.isEmpty()) {
                throw new RuntimeException("No se encontraron usuarios con el nombre proporcionado");
            }
            userDto.setUserList(usuarios);
            userDto.setStatuscode(200);
            userDto.setMensaje("Usuarios encontrados exitosamente");
        } catch (Exception e) {
            userDto.setStatuscode(500);
            userDto.setMensaje("Ocurrió un error: " + e.getMessage());
        }
        return userDto;
    }

}
