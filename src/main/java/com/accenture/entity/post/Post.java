package com.accenture.entity.post;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
}
