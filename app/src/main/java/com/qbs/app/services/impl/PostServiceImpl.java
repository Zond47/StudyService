package com.qbs.app.services.impl;

import com.qbs.app.aspect.LogExecution;
import com.qbs.app.common.exception.PostException;
import com.qbs.app.domain.Comment;
import com.qbs.app.domain.Post;
import com.qbs.app.domain.enums.PostStatus;
import com.qbs.app.domain.requests.PostRequest;
import com.qbs.app.repositories.PostRepository;
import com.qbs.app.services.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static java.time.LocalDateTime.now;

@AllArgsConstructor
@Service
@Slf4j
public class PostServiceImpl implements PostService {
  private final PostRepository postRepository;

  @LogExecution
  @Override
  public Optional<Post> findById(final Long Id) {
    return postRepository.findById(Id);
  }

  @LogExecution
  @Override
  public Post createPost(final PostRequest request) {
    log.info("Creating Post.");
    validateRequest(request);
    log.info("Successfully validated post request.");
    return postRepository.save(new Post(
            request.getCustomerId(),
            PostStatus.CREATED,
            request.getServiceDate(),
            request.getJobTags(),
            request.isFinalPropose(),
            request.getServiceAddress(),
            request.getProposedPrice()
    ));
  }

  private void validateRequest(PostRequest request) {
    log.info("Validating post request.");
    final int priceCompare = request.getProposedPrice().compareTo(BigDecimal.ZERO);
    // TODO: Change to isBefore
    if (request.getServiceDate().isAfter(now())
            || request.getJobTags().isEmpty()
            || request.getServiceAddress().isEmpty()
            || priceCompare != 1) {
      throw new PostException("Input fields cannot be empty!");
    }
  }

  @LogExecution
  @Override
  public Comment addComment(final Post post,
                            final Comment comment) {
    post.addComment(comment);
    return comment;
  }
}
