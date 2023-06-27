package com.qbs.app.services.impl;

import static java.time.LocalDateTime.now;

import com.qbs.app.aspect.LogExecution;
import com.qbs.app.common.exception.AppUserException;
import com.qbs.app.common.exception.PostException;
import com.qbs.app.domain.*;
import com.qbs.app.domain.enums.AppUserRole;
import com.qbs.app.domain.enums.PostStatus;
import com.qbs.app.domain.requests.PostRequest;
import com.qbs.app.repositories.PostRepository;
import com.qbs.app.services.PostService;
import java.math.BigDecimal;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class PostServiceImpl implements PostService {
  private final PostRepository postRepository;
  private final AppUserService userService;

  @LogExecution
  @Override
  public Optional<Post> findById(final Long Id) {
    return postRepository.findById(Id);
  }

  @LogExecution
  @Override
  public Post createPost(final PostRequest request) {
    log.info("Creating Post.");
    final AppUser user = validateRequest(request);
    log.info("Successfully validated post request.");
    final Post post =
        postRepository.save(
            new Post(
                PostStatus.CREATED,
                request.getServiceDate(),
                request.getJobTags(),
                request.isFinalPropose(),
                request.getServiceAddress(),
                request.getProposedPrice()));
    log.info("Post successfully created.");
    final UserPostId compositeKey =
        UserPostId.builder().postId(post.getId()).userId(user.getId()).build();
    final UserPost userPost =
        UserPost.builder()
            .userPostId(compositeKey)
            .userId(user)
            .postId(post)
            .role(AppUserRole.CUSTOMER)
            .build();
    log.info("Composite Key successfully created.");
    post.getUsers().add(userPost);
    postRepository.saveAndFlush(post);
    return post;
  }

  /**
   * Validates request and returns AppUser object.
   *
   * @param request Request to create Post
   * @return AppUser user
   */
  private AppUser validateRequest(PostRequest request) {
    final Long id = Long.valueOf(request.getCustomerId());
    Optional<AppUser> customer = userService.findUserById(id);
    log.info("Validating post request.");
    final int priceCompare = request.getProposedPrice().compareTo(BigDecimal.ZERO);
    if (request.getServiceDate().isBefore(now())
        || request.getJobTags().isEmpty()
        || request.getServiceAddress().isEmpty()
        || priceCompare != 1) {
      throw new PostException("Input fields cannot be empty!");
    }
    if (customer.isEmpty()) {
      throw new AppUserException("Customer does not exist!");
    }
    return customer.get();
  }

  @LogExecution
  @Override
  public Comment addComment(final Post post, final Comment comment) {
    if (!post.getCommentList().contains(comment)) {
      post.getCommentList().add(comment);
    }
    return comment;
  }

  @LogExecution
  @Override
  public Post addExecutor(final Post post, final AppUser user) {
    final UserPost userPost =
        UserPost.builder().postId(post).userId(user).role(AppUserRole.EXECUTOR).build();
    post.getUsers().add(userPost);
    return post;
  }
}
