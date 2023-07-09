package com.qbs.app.repositories;

import com.qbs.app.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
  Optional<Post> findById(final Long Id);

}
