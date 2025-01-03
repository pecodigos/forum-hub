package com.pecodigos.forumhub.modules.exceptions;

public class TopicNotFoundException extends RuntimeException {
    public TopicNotFoundException(String message) {
        super(message);
    }
}
