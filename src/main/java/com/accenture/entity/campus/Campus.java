package com.accenture.entity.campus;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "campus")
public class Campus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
}
