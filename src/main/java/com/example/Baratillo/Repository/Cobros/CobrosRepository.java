package com.example.Baratillo.Repository.Cobros;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Baratillo.Entity.Cobros.CobrosEntity;

public interface CobrosRepository extends JpaRepository <CobrosEntity, Long> {
    // List<CobrosEntity> findAll();
    List<CobrosEntity> findByIdUsuarioCobro_Id(Long idUsuario);
    List<CobrosEntity> findByFechaCobroBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    
    Long countByStatus(Boolean status);
}
