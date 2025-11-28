package br.edu.ifpr.irati.trabalhofinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String cpf;

    private String phone;

    private String address;

    @OneToOne(mappedBy = "client")
    @JsonIgnore
    private Account account;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();
}