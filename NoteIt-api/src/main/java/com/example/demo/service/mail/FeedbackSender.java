package com.example.demo.service.mail;

public interface FeedbackSender {
    void sendFeedback(String from, String name, String feedback);
}