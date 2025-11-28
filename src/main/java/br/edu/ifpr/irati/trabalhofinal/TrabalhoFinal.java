package br.edu.ifpr.irati.trabalhofinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
@SpringBootApplication
public class TrabalhoFinal {

    public static void main(String[] args) {
        SpringApplication.run(TrabalhoFinal.class, args);
    }

}