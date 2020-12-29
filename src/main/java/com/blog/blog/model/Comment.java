package com.blog.blog.model;


import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
public class Comment extends AuditableEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Lob
    @Size(min = 1, message = "A komment legalább 1 karakter kell legyen")
    private String text;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    /*
    @OneToMany
    private List<CommentRating> commentRatings;
    */

    @Transient
    private int ratingCount;

    @Transient
    private boolean userLiked;

    @Transient
    private boolean userDisliked;

}
