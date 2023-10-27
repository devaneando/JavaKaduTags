package com.kadu.model;

import java.util.ArrayList;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Configuration
{

    @NotNull
    @Valid
    private final ArrayList<Directory> directories;

    public Configuration()
    {
        this.directories = new ArrayList<>();
    }

    public ArrayList<Directory> getDirectories()
    {
        return this.directories;
    }

    public boolean setDirectories(ArrayList<Directory> directories)
    {
        boolean wasOk = true;

        for (Directory directory : directories) {
            if (!this.addDirectory(directory)) {
                wasOk = false;
            }
        }

        return wasOk;
    }

    public boolean addDirectory(Directory directory)
    {
        if (this.directories.contains(directory)) {
            return false;
        }

        this.directories.add(directory);
        this.sort();

        return true;
    }

    public boolean removeDirectory(Directory directory)
    {
        if (!this.directories.contains(directory)) {
            return false;
        }

        this.directories.remove(directory);
        this.sort();

        return true;
    }

    private void sort()
    {
        this.directories.sort((Directory first, Directory second) -> first.getPath().compareTo(second.getPath()));
    }
}