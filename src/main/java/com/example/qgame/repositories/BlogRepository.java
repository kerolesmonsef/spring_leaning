package com.example.qgame.repositories;

import com.example.qgame.Models.Blog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Query("SELECT b FROM Blog b ORDER BY b.id DESC")
    List<Blog> getRecent(Pageable pageable);
}
