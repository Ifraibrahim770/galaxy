package com.ibrahim.galaxy.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable {
    private String href;
    private ArrayList<Data> data;
    private ArrayList<Link> links;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public ArrayList<Link> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }
}
