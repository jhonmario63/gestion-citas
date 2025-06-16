package com.api.config;

import com.api.model.entities.UsuariosEntity;
import com.api.model.enums.TipoUsuarioEnum;
import com.api.repositories.IUsuariosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;


@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final IUsuariosRepository usuariosRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (usuariosRepository.count() == 0) {
            UsuariosEntity admin = new UsuariosEntity();
            admin.setNombre("Administrador");
            admin.setEmail("admin");
            admin.setNumDocumento("123456");
            admin.setPassword(passwordEncoder.encode("admin")); // contraseña encriptada
            admin.setTipoUsuario(TipoUsuarioEnum.ADMIN);
            admin.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
            usuariosRepository.save(admin);
            System.out.println("Usuario ADMIN creado: admin");
        }
    }
}
