package com.pap.zoo.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Task {

    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    private int id;
    @Lob
    @NotNull
    private String description;
    @NotNull
    @Column(columnDefinition = "VARCHAR(60) CHECK (SUBSTR(cage,2)<20) CHECK (SUBSTR(cage,2)>0) CHECK(LEFT(cage,1) IN ('A','B','C','D','E','F'))")
    private String cage;
    private String login;
    private LocalDateTime startTime;
    private Time duration;

    @ManyToOne
    private ZooKeeper zooKeeper;

    @OneToOne
    private Species species;

    public Task(String description, String cage, String login, LocalDateTime startTime, Time duration) {
        this.description = description;
        this.cage = cage;
        this.login = login;
        this.startTime = startTime;
        this.duration = duration;
    }
}
