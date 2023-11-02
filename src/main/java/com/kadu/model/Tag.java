package com.kadu.model;

import com.kadu.validator.ValidFolder;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Tag extends AbstractMd5Model implements Md5Model {

    @NotNull(message = "The tag cannot be null!")
    @NotEmpty(message = "The tag cannot be empty!")
    @ValidFolder()
    private String name;

    private String description;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name.trim();
        this.setHash(name);
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = null != description ? description.trim() : null;
    }

    @Override
    public String getValue() {
        return this.name;
    }
}
