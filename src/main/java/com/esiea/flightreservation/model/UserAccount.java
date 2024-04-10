package com.esiea.flightreservation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserAccount extends BaseEntity {

    String lastName;

    String firstName;

    String address;

    @Column(unique = true)
    String email;

    int tel;

    Date birthday;

}
