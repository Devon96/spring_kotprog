package com.blog.blog.controller;

import com.blog.blog.model.Comment;
import com.blog.blog.model.Post;
import com.blog.blog.repository.CommentRepository;
import com.blog.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/post")
public class PostController {

    PostService postService;
    CategoryService categoryService;
    CommentService commentService;
    PostRatingService postRatingService;

    @Autowired
    public PostController(PostService postService, CategoryService categoryService, CommentService commentService, PostRatingService postRatingService) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.commentService = commentService;
        this.postRatingService = postRatingService;
    }


    @GetMapping("/create")
    public String createPost(Post post, Model model){
        model.addAttribute("title", "Bejegyzés írása");
        model.addAttribute("categories", categoryService.findAll());
        return "create-post";
    }

    @PostMapping("/add")
    public String addPost(@Valid Post post, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("title", "Új post");
            model.addAttribute("categories", categoryService.findAll());
            return "create-post";
        }
        post = postService.save(post);
        return "redirect:/post/" + post.getId();
    }

    @GetMapping("/{id}")
    public String post(Model model, @PathVariable long id, Comment comment){
        Post p = postService.findById(id);
        p.setComments(commentService.findAllByPost(p));
        model.addAttribute("post", p);
        model.addAttribute("title", p.getTitle());
        return "post";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable long id){
        postService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editPost(Model model, @PathVariable long id){
        model.addAttribute("post", postService.findById(id));
        model.addAttribute("title", "szerkesztés");
        model.addAttribute("categories", categoryService.findAll());
        return "create-post";
    }

    @PostMapping("/edit/{id}")
    public String editPost(@PathVariable long id, @Valid Post post, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("title", "Bejegyzés módosítása");
            model.addAttribute("categories", categoryService.findAll());
            return "create-post";
        }
        postService.save(post);
        return "redirect:/post/" + id;
    }

}
