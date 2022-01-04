package com.pap.zoo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Animal {

    @Id
    @SequenceGenerator(
            name = "animal_sequence",
            sequenceName = "animal_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "animal_sequence"
    )
    private int id;
    private String name;
    private LocalDate dob;
    private String species;

    @ManyToOne
    private Species speciesInfo;

    public Animal(String name, LocalDate dob, String species) {
        this.name = name;
        this.dob = dob;
        this.species = species;
    }

}
