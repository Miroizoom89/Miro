package com.pap.zoo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"login"})})
public class ZooKeeper {

    @Id
    @SequenceGenerator(
            name = "zookeeper_sequence",
            sequenceName = "zookeeper_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "zookeeper_sequence"
    )
    private int id;
    private String name;
    private String surname;
    private String login;

    public ZooKeeper(String name, String surname, String login) {
        this.name = name;
        this.surname = surname;
        this.login = login;
    }
}
