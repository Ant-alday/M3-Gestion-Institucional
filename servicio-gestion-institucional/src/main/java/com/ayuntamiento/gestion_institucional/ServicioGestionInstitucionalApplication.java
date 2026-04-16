package com.ayuntamiento.gestion_institucional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD

@SpringBootApplication
public class ServicioGestionInstitucionalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioGestionInstitucionalApplication.class, args);
	}

}
=======
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServicioGestionInstitucionalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicioGestionInstitucionalApplication.class, args);
    }
}
>>>>>>> 52d7a8c0df7d3247d2930a3ecdce0567a2148320
