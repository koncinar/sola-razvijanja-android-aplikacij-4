package com.comtrade.sounddemo.app;

/**
 * <p>The sound entity that holds the title and the sound resource id.</p>
 * <p>Created by <a href="mailto:rok.koncina@comtrade.com">Rok Koncina</a> on 3.10.2014.</p>
 */
public class Sound {
    private int resourceId;
    private String name;

    public Sound(int resourceId, String name) {
        this.resourceId = resourceId;
        this.name = name;
    }

    public int getResourceId() {
        return resourceId;
    }

    public String getName() {
        return name;
    }
}
