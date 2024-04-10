package com.esiea.flightreservation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

    @OneToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

}
