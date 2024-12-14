package com.PruebaTecser.Cloud.Repositorios;

import com.PruebaTecser.Cloud.Entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByName( String name );
}
