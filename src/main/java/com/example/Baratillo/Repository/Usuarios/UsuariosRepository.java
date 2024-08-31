package com.example.Baratillo.Repository.Usuarios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Baratillo.Entity.Usuarios.UsuariosEntity;

import java.util.Optional;

public interface UsuariosRepository extends JpaRepository <UsuariosEntity, Long> {
    Optional<UsuariosEntity> findOneByEmail(String email);
}
