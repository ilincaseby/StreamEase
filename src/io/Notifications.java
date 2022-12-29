package io;

public class Notifications {
    private String movieName;
    private String message;

    public Notifications(String movieName, String message) {
        this.movieName = movieName;
        this.message = message;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
