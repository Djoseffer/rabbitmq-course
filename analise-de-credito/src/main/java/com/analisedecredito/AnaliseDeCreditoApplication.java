package com.analisedecredito;

import com.analisedecredito.domain.Proposta;
import com.analisedecredito.service.AnaliseCreditoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@AllArgsConstructor
@SpringBootApplication
public class AnaliseDeCreditoApplication {

    @Autowired
    private AnaliseCreditoService analiseCreditoService;
    public static void main(String[] args) {
        SpringApplication.run(AnaliseDeCreditoApplication.class, args);
    }


}
