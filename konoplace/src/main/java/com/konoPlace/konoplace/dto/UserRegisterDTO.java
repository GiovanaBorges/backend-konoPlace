package com.konoPlace.konoplace.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserRegisterDTO {
    private @NotBlank String nome;

    @Email
    private String email;

    @NotBlank
    private String cargo;

    @NotBlank
    private String departamento;

    @NotBlank
    private String senha;

    @NotBlank
    private String telefone;
}
