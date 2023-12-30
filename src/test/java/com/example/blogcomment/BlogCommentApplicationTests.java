package com.example.blogcomment;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.example.blogcomment.controller.CommentController;
import com.example.blogcomment.entities.Comment;
import com.example.blogcomment.repos.CommentRepository;
import com.example.blogcomment.service.CommentService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
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

		List<Comment> result = commentService.findAll();

		Assert.assertEquals(0, result.size());

	}

	@Test
	@Transactional
	void testSave() {
		Comment comment = new Comment();
		comment.setId(3);
		comment.setContent("testContent");
		comment.setPostId(1L);
		comment.setUserId(1L);
		comment.setDatePub(new Date());
		commentService.save(comment);
		Comment savedComment = commentRepository.findById((long) comment.getId()).orElse(null);
		Assert.assertNotNull(savedComment);
	}

	@Test
	@Transactional
	void testFindById() {
		Comment comment = new Comment();
		comment.getContent();
		comment.getDatePub();
		comment.getPostId();
		comment.getUserId();
		commentRepository.save(comment);

		Comment result = commentService.findById((long) comment.getId());

		Assert.assertNotNull(result);
	}

	@Test
	@Transactional
	void testDeleteById() {
		Comment comment = new Comment();
		commentRepository.save(comment);
		Long commentId = (long) comment.getId();

		commentService.deleteById(commentId);

		Comment deletedComment = commentRepository.findById(commentId).orElse(null);

		Assert.assertNull(deletedComment);
	}

	@Test
	@Transactional
	void testFindCommentByPostId() {
		Long postId = 1L;
		List<Comment> comments = new ArrayList<>();
		commentRepository.saveAll(comments);

		List<Comment> result = commentService.findCommentByPostId(postId);

		Assert.assertEquals(2, result.size());
	}

	@Autowired
	private CommentController commentController;

	@Test
	void testFindAll1() {
		Comment comment = new Comment();
		comment.setContent("Test Comment");
		commentRepository.save(comment);

		List<Comment> comments = commentController.findAll();

		assertNotNull(comments);
		assertEquals(2, comments.size());
	}

	@Test
	void testSave1() {
		CommentService commentService = new CommentService(commentRepository); 
		CommentController commentController = new CommentController(commentService);
		Comment comment = new Comment(); 
		commentController.save(comment);

		assertNotEquals(0, comment.getId(), "Comment ID should be a non-zero value");

	}

	@Test
	void testDeleteById1() {
		CommentService commentService = new CommentService(commentRepository); 
		CommentController commentController = new CommentController(commentService);

		Long commentId = 1L;
		commentController.deleteById(commentId);

		Comment deletedComment = commentController.findById(commentId);

		assertNull(deletedComment, "Deleted comment should not be found");

	}
	
	@Test
    void testFindCommentByPostId1() {
        Long postId = 1L;

        Comment comment1 = new Comment();
        comment1.setPostId(postId);
        commentRepository.save(comment1);

        Comment comment2 = new Comment();
        comment2.setPostId(postId);
        commentRepository.save(comment2);

        Comment comment3 = new Comment();
        comment3.setPostId(2L); 
        commentRepository.save(comment3);

        List<Comment> result = commentController.findCommentByPostId(postId);

        assertEquals(2, result.size());
    }
}
