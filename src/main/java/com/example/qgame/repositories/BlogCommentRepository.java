package com.example.qgame.repositories;

import com.example.qgame.Models.Blog;
import com.example.qgame.Models.BlogComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Size;
import java.util.List;

@Repository
public interface BlogCommentRepository extends JpaRepository<BlogComment, Long> {
    List<BlogComment> getAllByBlog(Blog blog);
}
