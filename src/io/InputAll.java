package io;

import java.util.ArrayList;

public class InputAll {
    private ArrayList<UserData> users;
    private ArrayList<MovieData> movies;
    private ArrayList<ActionsData> actions;

    /**
     * Getter
     * **/
    public ArrayList<UserData> getUsers() {
        return users;
    }

    /**
     * Setter
     * **/
    public void setUsers(final ArrayList<UserData> users) {
        this.users = users;
    }

    /**
     * Getter
     * **/
    public ArrayList<MovieData> getMovies() {
        return movies;
    }

    /**
     * Setter
     * **/
    public void setMovies(final ArrayList<MovieData> movies) {
        this.movies = movies;
    }

    /**
     * Getter
     * **/
    public ArrayList<ActionsData> getActions() {
        return actions;
    }

    /**
     * Setter
     * **/
    public void setActions(final ArrayList<ActionsData> actions) {
        this.actions = actions;
    }

    /**
     * Find an user by his name using streams
     * **/
    public static UserData userByName(final ArrayList<UserData> users, final String name) {
        return users.stream().filter(user -> name.equals(user.getCredentials()
                .getName())).findFirst().orElse(null);
    }
}
