package com.backend.clinicaOdontologica;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class ClinicaOdontologicaApplication {

    private static Logger LOGGER = LoggerFactory.getLogger(ClinicaOdontologicaApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(ClinicaOdontologicaApplication.class, args);
        crearTabla();

    }

    private static void crearTabla() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/clinicaOdontologia2;INIT=RUNSCRIPT FROM 'create.sql'", "a", "a");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
