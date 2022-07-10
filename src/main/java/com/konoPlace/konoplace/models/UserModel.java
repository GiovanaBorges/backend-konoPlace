package com.konoPlace.konoplace.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "O atributo Email é obrigatório")
    @Email(message = "O atributo deve ter um email válido!")
    private String email;

    @NotBlank
    private String cargo;

    @NotBlank
    private String foto;

    @NotBlank
    private String departamento;

    @NotBlank
    private String senha;

    @NotBlank
    private String telefone;


    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("user")
    private List<ReservaModel> reserva;
}
