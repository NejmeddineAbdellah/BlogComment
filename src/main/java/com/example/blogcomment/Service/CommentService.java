package com.example.blogcomment.Service;

import com.example.blogcomment.entities.Comment;
import com.example.blogcomment.repos.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public void save(Comment comment) {

        comment.setDatePub(new Date());
        commentRepository.save(comment);
    }

    public Comment findById(Long id) {
        return commentRepository.findById(id).get();
    }

    public void deleteById(Long aLong) {
        commentRepository.deleteById(aLong);
    }

    public List<Comment> findCommentByPostId(Long id) {
        return commentRepository.findCommentByPostId(id);
    }
}
