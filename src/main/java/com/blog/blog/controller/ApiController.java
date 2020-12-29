package com.blog.blog.controller;

import com.blog.blog.model.Category;
import com.blog.blog.model.Comment;
import com.blog.blog.model.Post;
import com.blog.blog.model.User;
import com.blog.blog.service.CategoryService;
import com.blog.blog.service.CommentService;
import com.blog.blog.service.PostService;
import com.blog.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    CommentService commentService;
    UserService userService;
    PostService postService;
    CategoryService categoryService;

    @Autowired
    public ApiController(CommentService commentService, UserService userService, PostService postService, CategoryService categoryService) {
        this.commentService = commentService;
        this.userService = userService;
        this.postService = postService;
        this.categoryService = categoryService;
    }

    @GetMapping("/category/delete/{name}")
    public HttpEntity<String> deleteCategory(@PathVariable String name){

        if(! categoryService.deleteByName(name)){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>("successful deletion", HttpStatus.OK);

    }

    @GetMapping("/comment/delete/{id}")
    public void deleteComment(@PathVariable long id){
        commentService.deleteById(id);
    }

    @GetMapping("/post/delete/{id}")
    public void deletePost(@PathVariable long id){
        postService.deleteById(id);
    }

    @GetMapping("/category/list")
    public HttpEntity<List<Category>> listCategory(){
        List<Category> categories = categoryService.findAll();
        if(categories.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/post/get/{id}")
    public HttpEntity<Post> listCategory(@PathVariable long id){
        Post post = postService.findById(id);
        if(post == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/post/list")
    public HttpEntity<List<Post>> listPost(){
        List<Post> posts = postService.findAll();
        if(posts.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/comment/list")
    public List<Comment> listComment(){
        return commentService.findAll();
    }


    @GetMapping("/user/list")
    public List<User> listUser(){
        return userService.findAll();
    }

    @PostMapping(value = "/comment/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addComment(@Valid @RequestBody Comment comment){
        commentService.save(comment);
    }

    @PostMapping(value = "/post/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addPost(@Valid @RequestBody Post post){
        postService.save(post);
    }

    @PostMapping(value = "/category/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addCategory(@RequestBody Category category){
        categoryService.save(category);
    }

    @PostMapping(value = "/user/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody User user){
        userService.save(user);
    }

    @PostMapping(value = "/post/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Post> editPost(@RequestBody Post post){
        Post p = postService.findById(post.getId());
        if(p == null){
            return ResponseEntity.notFound().build();
        }

        if(post.getCreatedBy() != null){
            p.setCreatedBy(post.getCreatedBy());
        }

        if(post.getCategory() != null){
            p.setCategory(post.getCategory());
        }

        if(post.getCreatedDate() != null){
            p.setCreatedDate(post.getCreatedDate());
        }
        if(post.getContent() != null){
            p.setContent(post.getContent());
        }

        if(post.getTitle() != null){
            p.setTitle(post.getTitle());
        }
        postService.save(p);

        return new ResponseEntity<>(p, HttpStatus.OK);

    }

    @PostMapping(value = "/user/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<User> editUser(@RequestBody User user){
        User u = userService.findByUsername(user.getUsername());
        if(u == null){
            return ResponseEntity.notFound().build();
        }

        if(user.getPassword() != null){
            u.setPassword(user.getPassword());
        }

        if(user.getEmail() != null){
            u.setEmail(user.getEmail());
        }

        if(user.isEnabled() != u.isEnabled()){
            u.setEnabled(user.isEnabled());
        }
        if(user.getRoles() != null){
            u.setRoles(user.getRoles());
        }

        if(user.getImg() != null){
            u.setImg(user.getImg());
        }
        userService.update(u);

        return new ResponseEntity<>(u, HttpStatus.OK);

    }

}
