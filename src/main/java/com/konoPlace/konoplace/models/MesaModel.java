package com.konoPlace.konoplace.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "place")
@Data
public class MesaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private String[] perifericos;

    @OneToMany(mappedBy = "mesa", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("mesa")
    private List<ReservaModel> reserva;

}
