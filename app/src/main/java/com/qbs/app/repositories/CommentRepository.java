package com.qbs.app.repositories;

import com.qbs.app.domain.Comment;
import com.qbs.app.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  Optional<Comment> findById(final Long Id);
}
