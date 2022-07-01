package com.konoPlace.konoplace.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name="reserva")
@Entity
@Data
public class ReservaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("reserva")
    private UserModel user;

    @ManyToOne
    @JsonIgnoreProperties("reserva")
    private MesaModel mesa;

    private LocalDate date;
}
