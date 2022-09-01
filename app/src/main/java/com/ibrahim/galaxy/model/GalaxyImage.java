package com.ibrahim.galaxy.model;

import java.io.Serializable;

public class GalaxyImage implements Serializable {
    private Collection collection;

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }
}
