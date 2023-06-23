package com.qbs.app.services;

import com.qbs.app.domain.Comment;
import com.qbs.app.domain.Post;
import com.qbs.app.domain.requests.PostRequest;

import java.util.Optional;

public interface PostService {

  Optional<Post> findById(final Long Id);

  Post createPost(final PostRequest postRequest);

  Comment addComment(final Post post,
                  final Comment comment);
}
