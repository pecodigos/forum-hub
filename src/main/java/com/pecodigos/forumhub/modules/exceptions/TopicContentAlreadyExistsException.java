package com.pecodigos.forumhub.modules.exceptions;

public class TopicContentAlreadyExistsException extends RuntimeException {
    public TopicContentAlreadyExistsException(String message) {
        super(message);
    }
}
