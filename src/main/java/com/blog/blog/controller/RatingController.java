package com.blog.blog.controller;


import com.blog.blog.service.CommentRatingService;
import com.blog.blog.service.PostRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rating")
public class RatingController {
    PostRatingService postRatingService;
    CommentRatingService commentRatingService;

    @Autowired
    public RatingController(PostRatingService postRatingService, CommentRatingService commentRatingService) {
        this.postRatingService = postRatingService;
        this.commentRatingService = commentRatingService;
    }

    @GetMapping("/post/like/{id}")
    public String likePost(@PathVariable long id){
        postRatingService.save(id, true);
        return "redirect:/post/" + id;
    }

    @GetMapping("/post/dislike/{id}")
    public String dislikePost(@PathVariable long id){
        postRatingService.save(id, false);
        return "redirect:/post/" + id;
    }

    @GetMapping("/comment/like/{postid}/{id}")
    public String likeCopmment(@PathVariable long postid ,@PathVariable long id){
        commentRatingService.save(id, true);
        return "redirect:/post/" + postid;
    }

    @GetMapping("/comment/dislike/{postid}/{id}")
    public String dislikeComment(@PathVariable long postid, @PathVariable long id){
        commentRatingService.save(id, false);
        return "redirect:/post/" + postid;
    }
}
