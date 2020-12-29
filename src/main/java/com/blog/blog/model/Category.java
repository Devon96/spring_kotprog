package com.blog.blog.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Data
@Entity
public class Category {

    @Id
    @Size(min = 2, message = "A kategóriának minimum 2 karakternek kell lennie.")
    private String name;
}
