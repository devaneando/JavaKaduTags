package com.kadu.model;

import com.kadu.validator.ValidFolder;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Directory extends AbstractMd5Model implements Md5Model {

    @NotNull(message = "The directory cannot be null!")
    @NotEmpty(message = "The directory cannot be empty!")
    @ValidFolder()
    private String path;

    public Directory() {
        // Do nothing
    }

    public Directory(String path) {
        if (!path.isEmpty()) {
            this.path = path.trim();
            this.setHash(path);
        }
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path.trim();
        this.setHash(path);
    }

    @Override
    public String getValue() {
        return this.path;
    }
}
