package com.kadu.model;

import java.util.ArrayList;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AbstractTagsCollectionModel {

    @NotNull
    @Valid
    private ArrayList<Tag> tags;

    public static int indexOfInCollection(ArrayList<Tag> tags, Tag tag) {
        for (int a = 0; a < tags.size(); a++) {
            if (tag.getHash().equals(tags.get(a).getHash())) {
                return a;
            }
        }

        return -1;
    }

    public static boolean containsInCollection(ArrayList<Tag> tags, Tag tag) {
        return -1 != indexOfInCollection(tags, tag);
    }

    public static ArrayList<Tag> addTagToCollection(ArrayList<Tag> tags, Tag tag) {
        if (containsInCollection(tags, tag)) {
            return tags;
        }

        tags.add(tag);
        tags.sort((Tag first, Tag second) -> first.getValue().compareTo(second.getValue()));

        return tags;
    }

    public static ArrayList<Tag> removeTagFromCollection(ArrayList<Tag> tags, Tag tag) {
        int index = indexOfInCollection(tags, tag);
        if (-1 == index) {
            return tags;
        }

        tags.remove(index);

        return tags;
    }

    public AbstractTagsCollectionModel() {
        this.tags = new ArrayList<>();
    }

    public ArrayList<Tag> getTags() {
        return this.tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        for (Tag tag : tags) {
            this.addTag(tag);
        }
    }

    public boolean addTag(Tag tag) {
        var count = this.tags.size();
        this.tags = addTagToCollection(this.tags, tag);

        return count < this.tags.size();
    }

    public boolean removeTag(Tag tag) {
        var count = this.tags.size();
        this.tags = removeTagFromCollection(this.tags, tag);

        return this.tags.size() < count;
    }

    public boolean contains(Tag tag) {
        return -1 != this.indexOf(tag);
    }

    public int indexOf(Tag tag) {
        return indexOfInCollection(this.tags, tag);
    }
}
