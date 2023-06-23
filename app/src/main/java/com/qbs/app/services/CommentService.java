package com.qbs.app.services;

import com.qbs.app.domain.Comment;
import com.qbs.app.domain.requests.CommentRequest;

import java.util.Optional;

public interface CommentService {

  Optional<Comment> findById(final Long Id);

  Comment createComment(final CommentRequest commentRequest);
}
