package br.edu.ifpr.irati.trabalhofinal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @NotBlank(message = "the name is not blank")
    @Size(min = 5, max = 50, message = "the name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "the description is not blank")
    @Size(min = 10, max = 255, message = "the description must be between 10 and 255 characters")
    private String description;

    private Boolean completed;

}