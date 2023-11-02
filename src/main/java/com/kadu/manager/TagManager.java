package com.kadu.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kadu.helper.OperationSystem;
import com.kadu.model.Directory;
import com.kadu.model.KaduTags;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TagManager {

    private static final String FILE_NAME = ".kaduTags";

    public void createFile(Directory directory) {
        File file = new File(OperationSystem.concatenate(directory.getPath(), FILE_NAME));
        if (file.exists()) {
            return;
        }

        try {
            this.saveFile(file, new KaduTags());
        } catch (IOException ex) {
            Logger.getLogger(TagManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveFile(File file, KaduTags kaduTags) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        String json = gson.toJson(kaduTags);

        FileWriter newFile = new FileWriter(file.getAbsolutePath());
        try (BufferedWriter buffer = new BufferedWriter(newFile)) {
            buffer.write(json);
        }
    }

    private KaduTags loadFile(Directory directory) throws IOException {
        File file = new File(OperationSystem.concatenate(directory.getPath(), FILE_NAME));
        if (!file.exists()) {
            KaduTags kaduTags = new KaduTags();
            this.saveFile(file, kaduTags);

            return kaduTags;
        }

        Path path = Path.of(file.getPath());
        String json = Files.readString(path);

        return new Gson().fromJson(json, KaduTags.class);
    }
}
