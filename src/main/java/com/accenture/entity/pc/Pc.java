package com.accenture.entity.pc;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_pc")
public class Pc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type_id")
    private Long type;

    @Column(name = "model_id")
    private Long model;
}
