package com.esiea.flightreservation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Flight extends BaseEntity{

    private String departure;

    private String destination;

    private Date departDate;

    private Date arrivalDate;

    private int placeNumber;

    @ManyToOne
    @JoinColumn(name = "aeroport_depart_id")
    private Airport airportDepart;

    @ManyToOne
    @JoinColumn(name = "aeroport_arrival_id")
    private Airport airportArrival;

    @ManyToOne
    @JoinColumn(name = "plane_id")
    private Plane plane;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
