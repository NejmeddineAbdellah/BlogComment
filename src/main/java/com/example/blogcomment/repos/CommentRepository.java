package com.example.blogcomment.repos;

import com.example.blogcomment.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query("from Comment c where c.postId= ?1")
    List<Comment> findCommentByPostId(Long id);

}
