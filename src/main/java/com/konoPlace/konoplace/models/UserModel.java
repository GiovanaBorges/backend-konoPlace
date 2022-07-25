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

    @NotBlank
    private String nome;

    @NotNull(message = "O atributo Email é obrigatório")
    @Column(unique=true ,nullable = false)
    private String email;

    @NotNull
    private String cargo;

    private String foto;

    @NotNull
    private String departamento;

    @NotNull
    private String senha;

    @NotNull
    private String telefone;


    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("user")
    private List<ReservaModel> reserva;
}
