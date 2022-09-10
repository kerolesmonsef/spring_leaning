package com.example.qgame.repositories;

import com.example.qgame.Models.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
    @Query("SELECT o FROM Option o WHERE o.title IN :titles")
    List<Option> getByListOfTitle(List<String> titles);
}
