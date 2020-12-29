package com.blog.blog.repository;

import com.blog.blog.model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {
    Authorities findByName(String name);
}
