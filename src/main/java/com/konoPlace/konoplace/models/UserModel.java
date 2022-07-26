package com.konoPlace.konoplace.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.List;

@Table(name = "user")
@Entity
@Data
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @NotNull(message = "O atributo Email é obrigatório")
    @Column(unique=true ,nullable = false)
    private String email;

    private String cargo;

    private String foto;

    private String departamento;

    private String senha;

    private String telefone;


    private String role;


    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("user")
    private List<ReservaModel> reserva;
}
