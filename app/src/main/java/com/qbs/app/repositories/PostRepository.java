package com.qbs.app.repositories;

import com.qbs.app.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
  Optional<Post> findById(final Long Id);

}
