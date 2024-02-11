package com.example.clinicaodontologica.controller;


import com.example.clinicaodontologica.exception.BadRequestException;
import com.example.clinicaodontologica.exception.ResourceNotFoundException;
import com.example.clinicaodontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.clinicaodontologica.entities.Odontologo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;
    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id) throws BadRequestException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorID(id);
        if(odontologoBuscado.isPresent()) {
            return ResponseEntity.ok(odontologoBuscado.get());
        }else {
            throw new BadRequestException("No fue posible encontrar el odontologo con ID: "+id);
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorID(odontologo.getId());
        if(odontologoBuscado.isPresent()) {
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Odontologo Actualizado");
        }else {
            throw new BadRequestException("No fue posible actualizar el odontologo: "+odontologo);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorID(id);
        if(odontologoBuscado.isPresent()){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Se eliminó el odontologo con el ID: "+id);
        }
        else{
            throw new ResourceNotFoundException("No existe el ID asociado en la Base de Datos: "+id);
        }
    }
    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos(){
        return ResponseEntity.ok(odontologoService.listarOdontologos());
    }
}