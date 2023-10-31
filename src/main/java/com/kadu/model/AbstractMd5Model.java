package com.kadu.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

abstract public class AbstractMd5Model implements Md5Model {

    @NotNull(message = "The hash cannot be null!")
    @NotEmpty(message = "The hash cannot be empty!")
    private String hash;

    @Override
    public String getHash() {
        return this.hash;
    }

    protected void setHash(String value) {
        this.hash = Md5Model.calculateHash(value.trim());
    }
}
