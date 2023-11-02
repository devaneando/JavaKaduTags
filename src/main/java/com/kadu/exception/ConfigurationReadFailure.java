package com.kadu.exception;

public class ConfigurationReadFailure extends Exception {

    String originalErrorMessage;

    public ConfigurationReadFailure() {
        super("Something bad happened while writing the configuration file!");
    }

    public ConfigurationReadFailure(String errorMessage) {
        super(errorMessage.trim());
    }

    public ConfigurationReadFailure(String errorMessage, String originalErrorMessage) {
        super(errorMessage.trim());
        this.originalErrorMessage = originalErrorMessage.trim();
    }

    public String getOriginalErrorMessage() {
        return this.originalErrorMessage;
    }
}
