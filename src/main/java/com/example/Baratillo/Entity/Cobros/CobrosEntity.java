package com.example.Baratillo.Entity.Cobros;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import com.example.Baratillo.Entity.Usuarios.UsuariosEntity;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Cobros")
public class CobrosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String concepto;

    @Column
    private String nombre;

    @Column
    private Double importe;
    
    @Column
    private LocalDateTime fechaCobro;

    @Column
    private String folio;

    @Column
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "idUsuarioCobro")
    private UsuariosEntity idUsuarioCobro;
}
