package com.kadu.exception;

public class TagsReadFailure extends AbstractIOFailure {

    protected static String getDefaultErrorMessage() {
        return "Something bad happened while reading the tags file!";
    }
}
