package com.accenture.dao;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "post_id")
    private Long postId;
    @Column(name = "sub_division_id")
    private Long subDivId;

    @OneToOne
    @JoinColumn(name = "user_ac")
    private User user_ac;

    public Employee() {
    }

    public Employee(Long id, String firstName, String lastName, Long postId, Long subDivId, User user) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postId = postId;
        this.subDivId = subDivId;
        this.user_ac = user;
    }
}
