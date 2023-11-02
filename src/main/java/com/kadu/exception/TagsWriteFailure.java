package com.kadu.exception;

public class TagsWriteFailure extends Exception {

    String originalErrorMessage;

    public TagsWriteFailure() {
        super("Something bad happened while writing the tags file!");
    }

    public TagsWriteFailure(String errorMessage) {
        super(errorMessage.trim());
    }

    public TagsWriteFailure(String errorMessage, String originalErrorMessage) {
        super(errorMessage.trim());
        this.originalErrorMessage = originalErrorMessage.trim();
    }

    public String getOriginalErrorMessage() {
        return this.originalErrorMessage;
    }
}
