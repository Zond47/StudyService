package com.qbs.app.services.impl;

import com.qbs.app.aspect.LogExecution;
import com.qbs.app.common.exception.AppUserException;
import com.qbs.app.common.exception.CommentException;
import com.qbs.app.common.exception.PostException;
import com.qbs.app.domain.AppUser;
import com.qbs.app.domain.Comment;
import com.qbs.app.domain.Post;
import com.qbs.app.domain.enums.CommentStatus;
import com.qbs.app.domain.requests.CommentRequest;
import com.qbs.app.repositories.CommentRepository;
import com.qbs.app.services.CommentService;
import com.qbs.app.services.PostService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private final PostService postService;
  private final AppUserService userService;

  @LogExecution
  @Override
  public Optional<Comment> findById(final Long Id) {
    return commentRepository.findById(Id);
  }

  @LogExecution
  @Override
  public Comment createComment(final CommentRequest commentRequest,
                               final String id,
                               final String postId) {
    log.info("Creating comment.");
    final Post post = validateRequest(id, postId);
    log.info("Successfully validated comment request.");
    final AppUser executor =
        userService.findUserById(Long.valueOf(id)).get();
    final Comment comment =
        commentRepository.save(
            new Comment(post, executor, commentRequest.getProposedPrice(), CommentStatus.CREATED));
    postService.addComment(post, comment);
    userService.addComment(executor.getId(), comment);
    return comment;
  }

  /**
   * Validates request and returns Post object.
   *
   * @param id User Id
   * @param postId Post id
   * @return Post
   */
  private Post validateRequest(final String id,
                               final String postId) {
    log.info("Validating comment request.");
    final Optional<Post> post = postService.findById(Long.valueOf(postId));
    final Optional<AppUser> appUser = userService.findUserById(Long.valueOf(id));
    if (appUser.isEmpty()) {
      throw new AppUserException("No user found!");
    }
    if (post.isEmpty()) {
      throw new PostException("No post found!");
    }
    if (post.get().isFinalPropose()) {
      throw new CommentException("Post is with final propose!");
    }
    // TODO: fix
    if (post.get().getUsers().equals(id)) {
      throw new CommentException("Customer cannot leave negotiation comment to own post!");
    }
    return post.get();
  }
}
