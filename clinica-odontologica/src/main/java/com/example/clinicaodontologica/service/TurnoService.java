package com.example.clinicaodontologica.service;

import com.example.clinicaodontologica.dto.TurnoDTO;
import com.example.clinicaodontologica.entities.Odontologo;
import com.example.clinicaodontologica.entities.Paciente;
import com.example.clinicaodontologica.entities.Turno;
import com.example.clinicaodontologica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;
    public TurnoDTO guardarTurno(Turno turno){
        Turno turnoGuardado = turnoRepository.save((turno));
        return turnoATurnoDTO(turnoGuardado);
    }
    public List<TurnoDTO> listarTurnos(){
        List<Turno> turnosEncontrados = turnoRepository.findAll();
        List<TurnoDTO> turnoDTOList = new ArrayList<>();
        for (Turno turno : turnosEncontrados) {
            turnoDTOList.add(turnoATurnoDTO(turno));
        }
        return turnoDTOList;
    }
    public Optional<TurnoDTO> buscarTurno(Long id){
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if (turnoBuscado.isPresent()){
            return Optional.of(turnoATurnoDTO(turnoBuscado.get()));
        }else{
            return Optional.empty();
        }
    }
    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }
    public void actualizarTurno(TurnoDTO turno){
        turnoRepository.save(turnoDTOaTurno(turno));
    }

    private TurnoDTO turnoATurnoDTO(Turno turno){
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setId(turno.getId());
        turnoDTO.setFecha(turno.getFecha());
        turnoDTO.setPacienteId(turno.getPaciente().getId());
        turnoDTO.setOdontologoId(turno.getOdontologo().getId());
        return turnoDTO;
    }

    private Turno turnoDTOaTurno(TurnoDTO turnoDTO){
        Turno turno = new Turno();
        Odontologo odontologo = new Odontologo();
        Paciente paciente = new Paciente();
        odontologo.setId(turnoDTO.getOdontologoId());
        paciente.setId(turnoDTO.getPacienteId());
        turno.setId(turnoDTO.getId());
        turno.setFecha(turnoDTO.getFecha());

        return turno;
    }
}
