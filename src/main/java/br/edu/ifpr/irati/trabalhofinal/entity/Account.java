package br.edu.ifpr.irati.trabalhofinal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Digite um email valido")
    @NotBlank(message = "email não pode ser vazio")
    private String email;

    @NotBlank(message = "Senha não pode ser vazia")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Client client;

    @CreationTimestamp
    private Instant creationDate;

}