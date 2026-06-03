package br.com.lucascostabueno.vetmanager.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class VetManagerApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(VetManagerApiApplication.class, args);
  }

}
