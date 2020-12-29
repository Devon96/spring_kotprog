package com.blog.blog.controller;


import com.blog.blog.model.Post;
import com.blog.blog.model.Search;
import com.blog.blog.service.CategoryService;
import com.blog.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class IndexController {

    PostService postService;
    CategoryService categoryService;

    @Autowired
    public IndexController(PostService postService, CategoryService categoryService) {
        this.postService = postService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String posts(Model model, Search search){
        model.addAttribute("title", "Bejegyzések");
        model.addAttribute("posts", postService.findAll());
        model.addAttribute("writers",postService.getAllWriters());
        return "index";
    }

    @PostMapping("/")
    public String postsPost(Model model, Search search){
        model.addAttribute("title", "Bejegyzések");
        model.addAttribute("posts", postService.findAll());
        model.addAttribute("writers",postService.getAllWriters());
        return "index";
    }

    @GetMapping("/search")
    public String searchPost(Model model, Search search, BindingResult result){
        model.addAttribute("title", "Bejegyzések");
        model.addAttribute("writers",postService.getAllWriters());

        if(search.getFrom() != null && search.getTo() != null ){
            model.addAttribute("posts", postService.findByTitleLikeAndContentLikeAndCreatedDateBetweenAndCreatedBy(search));
        }else{
            model.addAttribute("posts", postService.findByTitleLikeAndContentLikeAndCreatedBy(search));
        }
        return "index";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
}