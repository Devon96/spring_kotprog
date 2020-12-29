package com.blog.blog.repository;

import com.blog.blog.model.Post;
import com.blog.blog.model.PostRating;
import com.blog.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRatingRepository extends JpaRepository<PostRating, Long> {

    List<PostRating> getAllByPost(Post post);
    PostRating findByPostAndUser(Post post, User user);



}
