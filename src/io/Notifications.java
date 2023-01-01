package io;

public class Notifications {
    private String movieName;
    private String message;

    /**
     * Constructor with parameters
     * **/
    public Notifications(final String movieName, final String message) {
        this.movieName = movieName;
        this.message = message;
    }

    /**
     * Getter
     * **/
    public String getMovieName() {
        return movieName;
    }

    /**
     * Setter
     * **/
    public void setMovieName(final String movieName) {
        this.movieName = movieName;
    }

    /**
     * Getter
     * **/
    public String getMessage() {
        return message;
    }

    /**
     * Setter
     * **/
    public void setMessage(final String message) {
        this.message = message;
    }
}
