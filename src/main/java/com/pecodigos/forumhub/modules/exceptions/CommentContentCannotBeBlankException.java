package com.pecodigos.forumhub.modules.exceptions;

public class CommentContentCannotBeBlankException extends RuntimeException {
    public CommentContentCannotBeBlankException(String message) {
        super(message);
    }
}
