package com.pecodigos.forumhub.modules.exceptions;

public class TopicTitleAlreadyExistsException extends RuntimeException {
    public TopicTitleAlreadyExistsException(String message) {
        super(message);
    }
}
