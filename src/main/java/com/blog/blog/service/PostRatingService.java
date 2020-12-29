package com.blog.blog.service;

import com.blog.blog.model.Post;
import com.blog.blog.model.PostRating;
import com.blog.blog.model.User;
import com.blog.blog.repository.PostRepository;
import com.blog.blog.repository.PostRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostRatingService {

    PostRatingRepository postRatingRepository;
    PostRepository postRepository;
    UserService userService;

    @Autowired
    public PostRatingService(PostRatingRepository postRatingRepository, PostRepository postRepository, UserService userService) {
        this.postRatingRepository = postRatingRepository;
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public int getPostRatingValue(Post post){
        List <PostRating> postRatings = postRatingRepository.getAllByPost(post);
        int count = 0;
        for (PostRating postRating : postRatings){
            if (postRating.isRating()){
                count++;
            }else{
                count--;
            }
        }
        return count;
    }

    public PostRating findByPostAndUser(Post post, User user){
        return postRatingRepository.findByPostAndUser(post, user);
    }

    public void save(long id, boolean like) {
        Post post = postRepository.findById(id).orElse(null);
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        PostRating postRating = postRatingRepository.findByPostAndUser(post, user);

        if(postRating == null){
            postRating = new PostRating();
            postRating.setUser(user);
            postRating.setPost(post);
            postRating.setRating(like);
            postRatingRepository.save(postRating);
            return;
        }

        if( like == postRating.isRating()){
            postRatingRepository.delete(postRating);
        }else{
            postRating.setRating(like);
            postRatingRepository.save(postRating);
        }
    }
}
