package com.qbs.app.controllers;

import com.qbs.app.domain.Comment;
import com.qbs.app.domain.requests.CommentRequest;
import com.qbs.app.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/comments")
public class CommentController {
  private final CommentService commentService;

  @PostMapping
  public Comment create(@RequestBody final CommentRequest request,
                        @RequestParam("id") final String id,
                        @RequestParam("post") final String postId) {
    return commentService.createComment(request, id ,postId);
  }

  @GetMapping
  public Optional<Comment> findById(@RequestParam("id") final Long Id) {
    return commentService.findById(Id);
  }


}
