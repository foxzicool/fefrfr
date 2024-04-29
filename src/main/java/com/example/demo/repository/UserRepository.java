package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.model.UserData;

import java.util.List;

public interface UserRepository extends JpaRepository<UserData, Long> {
    UserData findByUsername(String username);

    @Query("SELECT u.voteContent, COUNT(u) FROM UserData u GROUP BY u.voteContent")
    List<Object[]> countVotes();

    @Query("SELECT COUNT(u) FROM UserData u")
    Long countTotalVotes();
}
