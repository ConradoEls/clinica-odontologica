package com.example.clinicaodontologica.controller;

import com.example.clinicaodontologica.dto.TurnoDTO;
import com.example.clinicaodontologica.entities.Turno;
import com.example.clinicaodontologica.exception.BadRequestException;
import com.example.clinicaodontologica.exception.ResourceNotFoundException;
import com.example.clinicaodontologica.service.OdontologoService;
import com.example.clinicaodontologica.service.PacienteService;
import com.example.clinicaodontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listarTurnos(){
        return ResponseEntity.ok(turnoService.listarTurnos());
    }

    @PostMapping
    public ResponseEntity<TurnoDTO> guardarTurno(@RequestBody Turno turno) throws BadRequestException {
        if (odontologoService.buscarOdontologoPorID(turno.getOdontologo().getId())!=null&&pacienteService.buscarPacientePorID(turno.getPaciente().getId())!=null){
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }else {
            throw new BadRequestException("No fue posible registrar el turno: "+turno);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarTurno(@PathVariable Long id) throws BadRequestException {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(id);
        if(turnoBuscado.isPresent()){
            return ResponseEntity.ok(turnoBuscado.get());
        }else {
            throw new BadRequestException("No fue posible encontrar el paciente con ID: "+id);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(id);
        if(turnoBuscado.isPresent()){
            turnoService.eliminarTurno(turnoBuscado.get().getId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Se eliminó el turno con el ID: "+id);
        }else{
            throw new ResourceNotFoundException("No existe el ID asociado en la Base de Datos: "+id);
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDTO turno) throws BadRequestException {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(turno.getId());
        if(turnoBuscado.isPresent()){
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("Turno actualizado");
        }else{
            throw new BadRequestException("No fue posible actualizar el turno: "+turno);
        }
    }
}
