package com.blog.blog.service;

import com.blog.blog.model.*;
import com.blog.blog.repository.CommentRatingRepository;
import com.blog.blog.repository.CommentRepository;
import com.blog.blog.repository.PostRatingRepository;
import com.blog.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentRatingService {

    CommentRatingRepository commentRatingRepository;
    CommentRepository commentRepository;
    UserService userService;

    @Autowired
    public CommentRatingService(CommentRatingRepository commentRatingRepository, CommentRepository commentRepository, UserService userService) {
        this.commentRatingRepository = commentRatingRepository;
        this.commentRepository = commentRepository;
        this.userService = userService;
    }


    public int getCommentRatingValue(Comment comment){
        List<CommentRating> commentRatings = commentRatingRepository.getAllByComment(comment);
        int count = 0;
        for (CommentRating commentRating : commentRatings){
            if (commentRating.isRating()){
                count++;
            }else{
                count--;
            }
        }
        return count;
    }

    public int getCommentRatingValue(Comment comment, User user){
        List<CommentRating> commentRatings = commentRatingRepository.getAllByComment(comment);
        int count = 0;
        for (CommentRating commentRating : commentRatings){
            if (commentRating.isRating()){
                count++;
            }else{
                count--;
            }
            if(commentRating.getUser().getUsername().equals(user.getUsername())){
                if(commentRating.isRating()){
                    comment.setUserLiked(true);
                    comment.setUserDisliked(false);
                }else{
                    comment.setUserLiked(false);
                    comment.setUserDisliked(true);
                }
            }
        }
        return count;
    }

    public void save(long comment_id, boolean like) {
        Comment comment = commentRepository.findById(comment_id).orElse(null);
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        CommentRating commentRating = commentRatingRepository.findByCommentAndUser(comment, user);

        if(commentRating == null){
            commentRating = new CommentRating();
            commentRating.setUser(user);
            commentRating.setComment(comment);
            commentRating.setRating(like);
            commentRatingRepository.save(commentRating);
            return;
        }

        if( like == commentRating.isRating()){
            commentRatingRepository.delete(commentRating);
        }else{
            commentRating.setRating(like);
            commentRatingRepository.save(commentRating);
        }
    }


}
