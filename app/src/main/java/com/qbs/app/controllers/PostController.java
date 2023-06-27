package com.qbs.app.controllers;

import com.qbs.app.domain.AppUser;
import com.qbs.app.domain.Post;
import com.qbs.app.domain.requests.PostRequest;
import com.qbs.app.services.PostService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/posts")
public class PostController {
  private final PostService postService;

  @PostMapping
  public Post createPost(@RequestBody final PostRequest request) {
    return postService.createPost(request);
  }

  @PutMapping
  public Post addExecutor(final Post post, final AppUser user) {
    return postService.addExecutor(post, user);
  }

  @GetMapping
  public Optional<Post> findById(@RequestParam("id") final Long Id) {
    return postService.findById(Id);
  }
}
