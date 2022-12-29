package io;

import java.util.ArrayList;

public class ContainData {
    private ArrayList<String> actors;
    private ArrayList<String> genre;

    /**
     * Getter
     * **/
    public ArrayList<String> getActors() {
        return actors;
    }

    /**
     * Setter
     * **/
    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    /**
     * Getter
     * **/
    public ArrayList<String> getGenre() {
        return genre;
    }

    /**
     * Setter
     * **/
    public void setGenre(final ArrayList<String> genre) {
        this.genre = genre;
    }
}
