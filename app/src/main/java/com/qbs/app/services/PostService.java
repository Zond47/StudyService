package com.qbs.app.services;

import com.qbs.app.domain.AppUser;
import com.qbs.app.domain.Comment;
import com.qbs.app.domain.Post;
import com.qbs.app.domain.requests.PostRequest;

import java.util.Optional;

public interface PostService {

  Optional<Post> findById(final Long Id);

  Post createPost(final PostRequest postRequest, final String Id);

  Comment addComment(final Post post, final Comment comment);

  Post addExecutor(final Post post, final AppUser user);
}
