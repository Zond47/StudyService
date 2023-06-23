package com.qbs.app.controllers;

import com.qbs.app.domain.Post;
import com.qbs.app.domain.requests.PostRequest;
import com.qbs.app.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/posts")
public class PostController {
  private final PostService postService;

  @PostMapping
  public Post create(@RequestBody final PostRequest request) {
    return postService.createPost(request);
  }

  @GetMapping
  public Optional<Post> findById(@RequestParam("id") final Long Id) {
    return postService.findById(Id);
  }
}
