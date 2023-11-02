package com.kadu.exception;

public class ConfigurationWriteFailure extends AbstractIOFailure {

    protected static String getDefaultErrorMessage() {
        return "Something bad happened while reading the configuration file!";
    }
}
