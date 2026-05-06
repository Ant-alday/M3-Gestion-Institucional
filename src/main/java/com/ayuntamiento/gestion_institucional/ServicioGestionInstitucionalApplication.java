package com.ayuntamiento.gestion_institucional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {
	    "com.ayuntamiento.gestion_institucional",              // 1. Que escanee su propio proyecto
	    "com.auth.client_sdk",        // 2. Que escanee tu SDK
	    "com.ayuntamiento.security_lib" // 3. Que escanee tu librería de seguridad
	})
@EnableDiscoveryClient
public class ServicioGestionInstitucionalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicioGestionInstitucionalApplication.class, args);
    }
}