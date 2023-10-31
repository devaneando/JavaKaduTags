package com.kadu.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kadu.helper.OperationSystem;
import com.kadu.model.Configuration;
import com.kadu.model.Directory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

public class ConfigurationManager {

    private static final String FILE_NAME = ".kaduTags";

    private String configFile;
    private Configuration config;
    private final Validator validator;

    public ConfigurationManager() throws IOException {
        if (null == this.configFile) {
            this.configFile = OperationSystem.concatenate(OperationSystem.homeFolder(), FILE_NAME);
        }

        this.loadConfig();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public ArrayList<Directory> getDirectories() {
        return this.config.getDirectories();
    }

    public ArrayList addDirectory(String path) throws IOException {
        Directory dir = new Directory();
        dir.setPath(path);

        ArrayList errors = new ArrayList();
        if (!this.config.addDirectory(dir)) {
            errors.add("The directory \"" + dir.getPath() + "\" already exists!");

            return errors;
        }
        Set<ConstraintViolation<Directory>> violations = validator.validate(dir);

        for (ConstraintViolation violation : violations) {
            errors.add(violation.getMessage());

            return errors;
        }

        this.saveConfig();

        return errors;
    }

    public ArrayList removeDirectory(String path) throws IOException {
        Directory dir = new Directory();
        dir.setPath(path);

        ArrayList errors = new ArrayList();
        if (!this.config.removeDirectory(dir)) {
            errors.add("The directory \"" + dir.getPath() + "\" was not removed!");

            return errors;
        }

        this.saveConfig();

        return errors;
    }

    private void saveConfig() throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        String json = gson.toJson(this.config);

        FileWriter file = new FileWriter(this.configFile);
        try (BufferedWriter buffer = new BufferedWriter(file)) {
            buffer.write(json);
        }
    }

    private void loadConfig() throws IOException {
        File file = new File(this.configFile);
        if (!file.exists()) {
            this.config = new Configuration();
            this.saveConfig();

            return;
        }

        Path path = Path.of(this.configFile);
        String json = Files.readString(path);
        this.config = new Gson().fromJson(json, Configuration.class);
    }
}
