package com.kadu.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kadu.helper.OperationSystem;
import com.kadu.model.Configuration;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigurationManager
{

    private static final String FILE_NAME = ".kaduTags";
    private String configFile;
    private Configuration config;

    public ConfigurationManager() throws IOException
    {
        if (null == this.configFile) {
            String[] elements = {
                OperationSystem.homeFolder(),
                FILE_NAME
            };
            this.configFile = OperationSystem.concatenate(elements);
        }

        this.loadConfig();
    }

    private void saveConfig() throws IOException
    {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        String json = gson.toJson(this.config);

        FileWriter file = new FileWriter(this.configFile);
        try (BufferedWriter buffer = new BufferedWriter(file)) {
            buffer.write(json);
        }
    }

    private void loadConfig() throws IOException
    {
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