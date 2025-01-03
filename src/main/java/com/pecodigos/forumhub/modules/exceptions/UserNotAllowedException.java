package com.pecodigos.forumhub.modules.exceptions;

public class UserNotAllowedException extends RuntimeException {
    public UserNotAllowedException(String message) {
        super(message);
    }
}
