package com.blog.blog.model;


import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table( name = "roles" )
public class Authorities implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 2, message = "A roll nevének minimum 2 karakternek kell lennie.")
    private String name;
    /*
    @ManyToMany( mappedBy = "roles")
    private List<User> users /*= new ArrayList<>();*/

    public Authorities(){}
    public Authorities(String name){
        this.name = name;
    }

}
