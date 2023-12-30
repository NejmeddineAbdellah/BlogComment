package com.example.blogcomment;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.blogcomment.controller.CommentController;
import com.example.blogcomment.entities.Comment;
import com.example.blogcomment.repos.CommentRepository;
import com.example.blogcomment.service.CommentService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BlogCommentApplicationTests {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentService commentService;

    @Test
    @Transactional
    void testFindAll() {
       // List<Comment> comments = new ArrayList<>();
        //commentRepository.saveAll(comments);

        List<Comment> result = commentService.findAll();

        Assert.assertEquals(0, result.size());
       
    }
    
    
    
    @Test
    @Transactional
    void testSave() {
        Comment comment = new Comment();
        commentService.save(comment);
        Comment savedComment = commentRepository.findById((long) comment.getId()).orElse(null);
        Assert.assertNotNull(savedComment);
    }
    @Test
    @Transactional
    void testFindById() {
        Comment comment = new Comment();
        // Set test data for comment
        commentRepository.save(comment);

        Comment result = commentService.findById((long) comment.getId());

        // Assert the results
        Assert.assertNotNull(result);
        // Add more assertions based on your actual data
    }

    @Test
    @Transactional
    void testDeleteById() {
        Comment comment = new Comment();
        // Set test data for comment
        commentRepository.save(comment);
        Long commentId = (long) comment.getId();

        commentService.deleteById(commentId);

        // Try to find the comment after deletion
        Comment deletedComment = commentRepository.findById(commentId).orElse(null);

        // Assert that the comment is deleted
        Assert.assertNull(deletedComment);
    }

    @Test
    @Transactional
    void testFindCommentByPostId() {
        Long postId = 1L;
        List<Comment> comments = new ArrayList<>();
        // Add test data to comments list
        commentRepository.saveAll(comments);

        List<Comment> result = commentService.findCommentByPostId(postId);

        // Assert the results
        Assert.assertEquals(comments.size(), result.size());
        // Add more assertions based on your actual data
    }
    @Autowired
    private CommentController commentController;
    
    @Test
    void testFindAll1() {
        // Given
        Comment comment = new Comment();
        comment.setContent("Test Comment");
        commentRepository.save(comment);

        // When
        List<Comment> comments = commentController.findAll();

        // Then
        assertNotNull(comments);
        assertEquals(2, comments.size());
        assertEquals("Test Comment", comments.get(0).getContent());


        // Additional tests as needed
    }
    
    @Test
    void testSave1() {
        // Given
        Comment comment = new Comment();
        comment.setContent("Test Comment");
        

        // When
        commentService.save(comment);

        // Then
        // Récupérez le commentaire enregistré dans la base de données
        Comment savedComment = commentRepository.findById((long) comment.getId()).orElse(null);

        // Vérifiez si le commentaire enregistré est non nul et correspond à celui que vous avez enregistré
        assertNotNull(savedComment);
        assertEquals("Test Comment", savedComment.getContent());
    }
    
}
