package com.kadu.exception;

public class ConfigurationWriteFailure extends Exception {

    String originalErrorMessage;

    public ConfigurationWriteFailure() {
        super("Something bad happened while reading the configuration file!");
    }

    public ConfigurationWriteFailure(String errorMessage) {
        super(errorMessage.trim());
    }

    public ConfigurationWriteFailure(String errorMessage, String originalErrorMessage) {
        super(errorMessage.trim());
        this.originalErrorMessage = originalErrorMessage.trim();
    }
}
