package com.blog.blog.service;


import com.blog.blog.model.Comment;
import com.blog.blog.model.Post;
import com.blog.blog.model.User;
import com.blog.blog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private CommentRatingService commentRatingService;
    private UserService userService;


    @Autowired
    public CommentService(CommentRepository commentRepository, CommentRatingService commentRatingService, UserService userService) {
        this.commentRepository = commentRepository;
        this.commentRatingService = commentRatingService;
        this.userService = userService;
    }

    public void save(Comment comment){
        commentRepository.save(comment);
    }

    public List<Comment> findAllByPost(Post post){
        List<Comment> comments = commentRepository.findAllByPost(post);
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());



        for (Comment comment : comments){

            if(user == null){
                comment.setRatingCount(commentRatingService.getCommentRatingValue(comment));
            }else{
                comment.setRatingCount(commentRatingService.getCommentRatingValue(comment, user));
            }


        }
        return comments;
    }


    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
}
