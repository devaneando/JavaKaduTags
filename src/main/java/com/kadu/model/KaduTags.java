package com.kadu.model;

import java.util.ArrayList;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class KaduTags {

    @NotNull
    @Valid
    private final ArrayList<Tag> tags;

    public KaduTags() {
        this.tags = new ArrayList<>();
    }

    public boolean addTag(Tag tag) {
        if (this.contains(tag)) {
            return false;
        }

        this.tags.add(tag);
        this.tags.sort((Tag first, Tag second) -> first.getName().compareTo(second.getName()));

        return true;
    }

    public boolean removeTag(Tag tag) {
        int index = this.indexOf(tag);
        if (-1 == index) {
            return false;
        }

        this.tags.remove(index);

        return true;
    }

    public boolean contains(Tag tag) {
        return -1 != this.indexOf(tag);
    }

    public int indexOf(Tag tag) {
        for (int a = 0; a < this.tags.size(); a++) {
            if (tag.getHash().equals(this.tags.get(a).getHash())) {
                return a;
            }
        }

        return -1;
    }
}
