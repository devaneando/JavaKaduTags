package com.kadu.exception;

public class ConfigurationReadFailure extends AbstractIOFailure {

    protected static String getDefaultErrorMessage() {
        return "Something bad happened while writing the configuration file!";
    }
}
