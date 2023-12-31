package com.example.blogcomment.controller;

import com.example.blogcomment.service.CommentService;
import com.example.blogcomment.entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/comment")
public class CommentController {

	private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/all")
    public List<Comment> findAll() {
        return commentService.findAll();
    }

    @PostMapping("/save")
    public void save(@RequestBody Comment comment) {
        commentService.save(comment);
    }

    @GetMapping("/id/{id}")
    public Comment findById(@PathVariable Long id) {
        return commentService.findById(id);
    }

    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable Long aLong) {
        commentService.deleteById(aLong);
    }

    @GetMapping("/findcomment/id/{id}")
    public List<Comment> findCommentByPostId(@PathVariable Long id) {
        return commentService.findCommentByPostId(id);
    }
}
