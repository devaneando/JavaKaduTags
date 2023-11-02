package com.kadu.exception;

public class TagsReadFailure extends Exception {

    String originalErrorMessage;

    public TagsReadFailure() {
        super("Something bad happened while reading the tags file!");
    }

    public TagsReadFailure(String errorMessage) {
        super(errorMessage.trim());
    }

    public TagsReadFailure(String errorMessage, String originalErrorMessage) {
        super(errorMessage.trim());
        this.originalErrorMessage = originalErrorMessage.trim();
    }

    public String getOriginalErrorMessage() {
        return this.originalErrorMessage;
    }
}
