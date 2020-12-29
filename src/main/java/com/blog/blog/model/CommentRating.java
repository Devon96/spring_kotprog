package com.blog.blog.model;


import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class CommentRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull
    boolean rating;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    User user;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    Comment comment;


}
