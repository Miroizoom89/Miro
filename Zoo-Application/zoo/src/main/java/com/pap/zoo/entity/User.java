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
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private int id;
    private String login;
    private String pass;
    @Column(columnDefinition = "VARCHAR(60) CHECK (role IN ('USER', 'ADMIN'))")
    private String role;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ZooKeeper zooKeeper;

    public User(String login, String pass, String role) {
        this.login = login;
        this.pass = pass;
        this.role = role;
    }
}
