package com.example.Baratillo.Controller.Cobros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Baratillo.Entity.Cobros.CobrosEntity;
import com.example.Baratillo.Service.Cobros.CobrosService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, })
@RestController
@RequestMapping("/api/v1/Cobros")
public class CobrosController {
    
    @Autowired
    CobrosService cobrosService;

    @GetMapping("/byUsuario")
    public List<CobrosEntity> getByUsuario(@PathVariable("idUsuario") Long idUsuario){
        return cobrosService.getByUsuario(idUsuario);
    }

    @GetMapping("/byFechasCobro")
    public List<CobrosEntity> getByFechasCobro(@RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin){
        return cobrosService.getByFechasCobro(fechaInicio, fechaFin);
    }

    @PostMapping("/cobrar")
    public ResponseEntity<CobrosEntity> addCobro(@RequestBody CobrosEntity cobrosEntity){
        return cobrosService.addCobro(cobrosEntity);
    } 
}
