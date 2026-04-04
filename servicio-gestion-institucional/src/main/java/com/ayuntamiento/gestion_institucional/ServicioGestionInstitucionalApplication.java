package com.ayuntamiento.gestion_institucional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServicioGestionInstitucionalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicioGestionInstitucionalApplication.class, args);
    }
}