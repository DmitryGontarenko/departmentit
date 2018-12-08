package com.accenture.entity.tag;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
}
