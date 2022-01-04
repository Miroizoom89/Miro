package com.pap.zoo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"species"}), @UniqueConstraint(columnNames = {"cage"})})
public class Species {

    @Id
    @SequenceGenerator(
            name = "species_sequence",
            sequenceName = "species_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "species_sequence"
    )
    private int id;
    @Column(columnDefinition = "VARCHAR(60) CHECK (SUBSTR(cage,2)<20) CHECK (SUBSTR(cage,2)>0) CHECK(LEFT(cage,1) IN ('A','B','C','D','E','F'))")
    private String cage;
    private Time feedingHour;
    @Lob
    private String description;
    private String species;

    public Species(String cage, Time feedingHour, String description, String species) {
        this.cage = cage;
        this.feedingHour = feedingHour;
        this.description = description;
        this.species = species;
    }
}
