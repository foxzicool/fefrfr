package com.example.demo.controller;

import com.example.demo.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/question")
    public void receiveQuestion(ChatMessage question) {
        System.out.println("Received question: " + question.getContent()); // Log received question
        String answer = answerQuestion(question.getContent());
        System.out.println("Sending answer: " + answer); // Log answer
        simpMessagingTemplate.convertAndSend("/topic/answers", answer);
    }

    private String answerQuestion(String question) {
        if (question.equalsIgnoreCase("hello")) {
            return "Hello! How can I help you?";
        } else if (question.equalsIgnoreCase("how are you?")) {
            return "I'm a bot, so I don't have feelings, but I'm here to help!";
        } else {
            return "I'm sorry, I don't understand the question.";
        }
    }
}
