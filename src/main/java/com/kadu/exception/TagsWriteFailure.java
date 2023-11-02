package com.kadu.exception;

public class TagsWriteFailure extends AbstractIOFailure {

    protected static String getDefaultErrorMessage() {
        return "Something bad happened while writing the tags file!";
    }
}
