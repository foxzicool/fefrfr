package com.example.demo.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_data")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "vote_content")
    private String voteContent;

    @Column(name = "vote_timestamp")
    private Timestamp voteTimestamp;

    // Getters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getVoteContent() {
        return voteContent;
    }

    public Timestamp getVoteTimestamp() {
        return voteTimestamp;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVoteContent(String voteContent) {
        this.voteContent = voteContent;
    }

    public void setVoteTimestamp(Timestamp voteTimestamp) {
        this.voteTimestamp = voteTimestamp;
    }
}
