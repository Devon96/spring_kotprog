package com.blog.blog.repository;

import com.blog.blog.model.Post;
import com.blog.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleLikeAndContentLikeAndCreatedDateBetweenAndCreatedBy(String title, String content, Date from, Date to, String user);
    List<Post> findByTitleLikeAndContentLikeAndCreatedDateBetween(String title, String content, Date from, Date to);
    List<Post> findByTitleLikeAndContentLikeAndCreatedBy(String title, String content, String user);
    List<Post> findByTitleLikeAndContentLike(String title, String content);

    @Modifying
    @Query("SELECT createdBy FROM Post GROUP BY createdBy ORDER BY (createdBy)")
    List<String> getAllWriters ();

    Optional<Post> findByTitle(String title);
}
