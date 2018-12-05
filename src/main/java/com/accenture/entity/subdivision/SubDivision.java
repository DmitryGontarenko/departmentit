package com.accenture.entity.subdivision;

import com.accenture.entity.campus.Campus;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "sub_division")
public class SubDivision {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "campus_id")
    private Campus campusId;
}
