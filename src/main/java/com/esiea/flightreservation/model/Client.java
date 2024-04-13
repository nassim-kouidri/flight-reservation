package com.esiea.flightreservation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Client extends BaseEntity {

    @Column(unique = true)
    int passportNumber;

    @ManyToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

}
