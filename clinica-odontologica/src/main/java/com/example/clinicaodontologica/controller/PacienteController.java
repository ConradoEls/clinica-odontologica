package com.example.clinicaodontologica.controller;

import com.example.clinicaodontologica.exception.BadRequestException;
import com.example.clinicaodontologica.exception.ResourceNotFoundException;
import com.example.clinicaodontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.clinicaodontologica.entities.Paciente;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id) throws BadRequestException {
            Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorID(id);
            if(pacienteBuscado.isPresent()) {
                return ResponseEntity.ok(pacienteBuscado.get());
            }else {
                throw new BadRequestException("No fue posible encontrar el paciente con ID: "+id);
            }
    }
    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) throws BadRequestException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorID(paciente.getId());
        if(pacienteBuscado.isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Paciente Actualizado - "+paciente.getNombre()+" "+paciente.getApellido());
        }
        else {
            throw new BadRequestException("No fue posible actualizar el paciente: "+paciente);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorID(id);
        if(pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Se eliminó el paciente con el ID: "+id);
        }
        else{
            throw new ResourceNotFoundException("No existe el ID asociado en la Base de Datos: "+id);
        }
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<Paciente> buscarPacientePorEmail(@PathVariable String email) throws BadRequestException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacienteXEmail(email);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }
        else {
            throw new BadRequestException("No fue posible encontrar el paciente con email: "+email);
        }
    }
    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes(){
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }
 }
