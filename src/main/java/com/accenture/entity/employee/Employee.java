package com.accenture.entity.employee;

import com.accenture.entity.post.Post;
import com.accenture.entity.user.User;
import com.accenture.entity.subdivision.SubDivision;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "First name cannot be empty")
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post postId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sub_division_id")
    private SubDivision subDivId;

    @OneToOne
    @JoinColumn(name = "user_ac")
    private User user_ac;

}
