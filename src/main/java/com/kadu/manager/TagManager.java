package com.kadu.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kadu.exception.TagsWriteFailure;
import com.kadu.helper.OperationSystem;
import com.kadu.model.Directory;
import com.kadu.model.KaduTags;
import com.kadu.model.Tag;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Validation;
import javax.validation.Validator;

public class TagManager {

    private static final String FILE_NAME = ".kaduTags";

    private Validator validator;

    public TagManager() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public void createFile(Directory directory) throws TagsWriteFailure {
        File file = new File(OperationSystem.concatenate(directory.getPath(), FILE_NAME));
        if (file.exists()) {
            return;
        }

        this.saveFile(file, new KaduTags());

    }

    public ArrayList<Tag> loadAll(ArrayList<Directory> directories) throws TagsWriteFailure {
        ArrayList<Tag> found = new ArrayList<>();

        if (directories.isEmpty()) {
            return found;
        }

        for (Directory directory : directories) {
            String path = OperationSystem.concatenate(directory, FILE_NAME);
            File file = new File(path);
            if (!file.exists()) {
                return found;
            }

            var kaduTags = this.loadFile(directory);
            if (kaduTags.getTags().isEmpty()) {
                continue;
            }

            for (Tag foundTag : kaduTags.getTags()) {
                found = KaduTags.addTagToCollection(found, foundTag);
            }
        }

        return found;
    }

    private void saveFile(File file, KaduTags kaduTags) throws TagsWriteFailure {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        String json = gson.toJson(kaduTags);

        try {
            FileWriter FileWriter = new FileWriter(file.getAbsolutePath());
            FileWriter.write(json);
            FileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(TagManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new TagsWriteFailure(
                    "It was not possible to update the tags: \"" + file.getPath() + "\"!",
                    ex.getMessage()
            );
        }
    }

    private KaduTags loadFile(Directory directory) throws TagsWriteFailure {
        File file = new File(OperationSystem.concatenate(directory.getPath(), FILE_NAME));
        if (!file.exists()) {
            KaduTags kaduTags = new KaduTags();
            this.saveFile(file, kaduTags);

            return kaduTags;
        }

        Path path = Path.of(file.getPath());
        String json;
        try {
            json = Files.readString(path);
        } catch (IOException ex) {
            Logger.getLogger(TagManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new TagsWriteFailure(
                    "It was not possible to read the tags: \"" + file.getPath() + "\"!",
                    ex.getMessage()
            );
        }

        return new Gson().fromJson(json, KaduTags.class);
    }
}
