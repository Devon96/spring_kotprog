package com.blog.blog.model;

import lombok.Data;

import java.util.Date;

@Data
public class Search {
    private String title;
    private String content;
    private String creator;
    private Date from;
    private Date to;

}
