package com.example.Baratillo.Service.Cobros;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Baratillo.Entity.Cobros.CobrosEntity;
import com.example.Baratillo.Repository.Cobros.CobrosRepository;

@Service
public class CobrosService {

    @Autowired
    CobrosRepository cobrosRepository;

    public List<CobrosEntity> getByUsuario(Long idUsuario){
        try {
            return cobrosRepository.findByIdUsuarioCobro_Id(idUsuario); 
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    public List<CobrosEntity> getByFechasCobro(String fechaInicio, String fechaFin){
        try {
            LocalDateTime inicio = LocalDateTime.parse(fechaInicio);
            LocalDateTime fin = LocalDateTime.parse(fechaFin);
            
            return cobrosRepository.findByFechaCobroBetween(inicio, fin);
        } catch (Exception e) { 
            throw new IllegalArgumentException();
        }
    }

    public ResponseEntity<CobrosEntity> addCobro(CobrosEntity cobrosEntity){
        try {
            int year = Calendar.getInstance().get(Calendar.YEAR);
            Long number = cobrosRepository.countByStatus(true);
            String isCero = "";
            if(number < 9){
                isCero = "0";
            }
            
            cobrosEntity.setFolio(StringUtils.right(Integer.toString(year), 2) + "000" + isCero + number);
            cobrosEntity.setFechaCobro(LocalDateTime.now());
            cobrosEntity.setStatus(true);
            cobrosRepository.save(cobrosEntity);

            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }


}
