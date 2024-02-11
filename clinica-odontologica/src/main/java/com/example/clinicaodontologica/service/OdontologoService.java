package com.example.clinicaodontologica.service;

import com.example.clinicaodontologica.entities.Odontologo;
import com.example.clinicaodontologica.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    @Autowired
    private OdontologoRepository odontologoRepository;

    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }
    public Optional<Odontologo> buscarOdontologoPorID(Long id){
        return odontologoRepository.findById(id);
    }
    public void eliminarOdontologo(Long id){
        odontologoRepository.deleteById(id);
    }
    public void actualizarOdontologo(Odontologo odontologo){
        odontologoRepository.save(odontologo);
    }
    public List<Odontologo> listarOdontologos(){
        return odontologoRepository.findAll();
    }
}
