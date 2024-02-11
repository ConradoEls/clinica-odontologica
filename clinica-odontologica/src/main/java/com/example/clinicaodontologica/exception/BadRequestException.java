package com.example.clinicaodontologica.exception;

public class BadRequestException extends Exception{

    public BadRequestException(String mensaje){
        super(mensaje);
    }
}
