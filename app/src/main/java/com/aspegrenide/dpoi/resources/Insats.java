package com.aspegrenide.dpoi.resources;

import java.util.ArrayList;
import java.util.Date;

public class Insats {
    String name;
    Date defaultStart;
    Date defaultStop;
    String description;

    public Insats(String name, Date defaultStart, Date defaultStop) {
        this.name = name;
        this.defaultStart = defaultStart;
        this.defaultStop = defaultStop;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Date getDefaultStart() {
        return defaultStart;
    }

    public Date getDefaultStop() {
        return defaultStop;
    }
}
