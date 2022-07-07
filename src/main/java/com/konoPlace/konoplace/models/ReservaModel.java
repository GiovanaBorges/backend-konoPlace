package com.konoPlace.konoplace.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
}
