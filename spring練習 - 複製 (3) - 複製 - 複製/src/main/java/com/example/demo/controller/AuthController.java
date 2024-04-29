package com.example.demo.controller;

import com.example.demo.model.UserData;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        UserData user = userRepository.findByUsername(loginRequest.username);
        if (user != null && user.getPassword().equals(loginRequest.password)) {
            return ResponseEntity.ok("Logged in successfully!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password!");
    }

    @PostMapping("/api/vote")
    public ResponseEntity<String> vote(@RequestBody VoteRequest voteRequest) {
        UserData user = userRepository.findByUsername(voteRequest.username);
        if (user != null) {
            user.setVoteContent(voteRequest.voteContent);
            user.setVoteTimestamp(new Timestamp(System.currentTimeMillis()));
            userRepository.save(user);
            return ResponseEntity.ok("Vote recorded successfully!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found!");
    }

    @GetMapping("/api/vote-counts")
    public ResponseEntity<List<Object[]>> getVoteCounts() {
        List<Object[]> voteCounts = userRepository.countVotes();
        return ResponseEntity.ok(voteCounts);
    }

    @GetMapping("/api/total-votes")
    public ResponseEntity<Long> getTotalVotes() {
        Long totalVotes = userRepository.countTotalVotes();
        return ResponseEntity.ok(totalVotes);
    }

    static class LoginRequest {
        public String username;
        public String password;
    }

    static class VoteRequest {
        public String username;
        public String voteContent;
    }
}
