package io;

import java.util.ArrayList;
import java.util.Objects;

public class MovieData {
    private String name;
    private int year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;
    private int numLikes = 0;
    private int rating = 0;
    private int numRatings = 0;
    private int numPeopleRatings = 0;
    public static final int HUNDRED = 100;

    /**
     * Equals method override
     * **/
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MovieData movieData)) {
            return false;
        }
        return Objects.equals(getName(), movieData.getName());
    }

    /**
     * Getter
     * **/
    public int getNumPeopleRatings() {
        return numPeopleRatings;
    }

    /**
     * Setter
     * **/
    public void setNumPeopleRatings(final int numPeopleRatings) {
        this.numPeopleRatings = numPeopleRatings;
    }

    /**
     * Getter
     * **/
    public double getFinalRating() {
        double ret = (double) rating / (double) numRatings;
        int aux = (int) (ret * HUNDRED);
        ret = (double) aux;
        ret /= HUNDRED;
        return ret;
    }

    /**
     * hashCode method override
     * **/
    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    /**
     * Getter
     * **/
    public int getNumLikes() {
        return numLikes;
    }

    /**
     * Setter
     * **/
    public void setNumLikes(final int numLikes) {
        this.numLikes = numLikes;
    }

    /**
     * Getter
     * **/
    public int getRating() {
        return rating;
    }

    /**
     * Setter
     * **/
    public void setRating(final int rating) {
        this.rating = rating;
    }

    /**
     * Getter
     * **/
    public int getNumRatings() {
        return numRatings;
    }

    /**
     * Setter
     * **/
    public void setNumRatings(final int numRatings) {
        this.numRatings = numRatings;
    }

    /**
     * Getter
     * **/
    public String getName() {
        return name;
    }

    /**
     * Setter
     * **/
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Getter
     * **/
    public int getYear() {
        return year;
    }

    /**
     * Setter
     * **/
    public void setYear(final int year) {
        this.year = year;
    }

    /**
     * Getter
     * **/
    public int getDuration() {
        return duration;
    }

    /**
     * Setter
     * **/
    public void setDuration(final int duration) {
        this.duration = duration;
    }

    /**
     * Getter
     * **/
    public ArrayList<String> getGenres() {
        return genres;
    }

    /**
     * Setter
     * **/
    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

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
    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    /**
     * Setter
     * **/
    public void setCountriesBanned(final ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }
}
