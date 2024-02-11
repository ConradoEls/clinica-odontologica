package com.example.clinicaodontologica.security;

import com.example.clinicaodontologica.entities.AppUser;
import com.example.clinicaodontologica.entities.AppUserRole;
import com.example.clinicaodontologica.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargadoraDeDatos implements ApplicationRunner {

    private UserService userService;

    @Autowired
    public CargadoraDeDatos(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pass1="password";
        String passAdmin= passwordEncoder.encode(pass1);
        String pass2="password";
        String passUser= passwordEncoder.encode(pass2);
        userService.guardarUsuario(new AppUser("Conrado","Els","conradoels@gmail.com",passAdmin, AppUserRole.ROLE_ADMIN));
        System.out.println("La contaseña cifrada de admin es: "+passAdmin);
        userService.guardarUsuario(new AppUser("Agustin","Stoller","agustinstoller@gmail.com",passUser, AppUserRole.ROLE_USER));
        System.out.println("La contaseña cifrada user es: "+passUser);
    }
}
