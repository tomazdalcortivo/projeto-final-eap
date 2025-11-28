package br.edu.ifpr.irati.trabalhofinal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar em branco")
    @Size(min = 5, max = 50, message = "O nome deve ter entre 5 e 50 caracteres")
    private String name;

    @NotBlank(message = "A descrição não pode estar em branco")
    @Size(min = 10, max = 255, message = "A descrição deve ter entre 10 e 255 caracteres")
    private String description;

    private Boolean completed;
}