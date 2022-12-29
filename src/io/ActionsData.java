package io;

public class ActionsData {
    private String type;
    private String page;
    private String movie;
    private String feature;
    private CredentialsData credentials;
    private MovieData addedMovie;
    private String deletedMovie;
    private String startsWith;
    private String count;
    private int rate;
    private FilterData filters;
    private String subscribedGenre;

    public MovieData getAddedMovie() {
        return addedMovie;
    }

    public void setAddedMovie(MovieData addedMovie) {
        this.addedMovie = addedMovie;
    }

    public String getDeletedMovie() {
        return deletedMovie;
    }

    public void setDeletedMovie(String deletedMovie) {
        this.deletedMovie = deletedMovie;
    }

    public String getSubscribedGenre() {
        return subscribedGenre;
    }

    public void setSubscribedGenre(String subscribedGenre) {
        this.subscribedGenre = subscribedGenre;
    }

    /**
     * Getter
     * **/
    public String getType() {
        return type;
    }

    /**
     * Setter
     * **/
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Getter
     * **/
    public String getPage() {
        return page;
    }

    /**
     * Setter
     * **/
    public void setPage(final String page) {
        this.page = page;
    }

    /**
     * Getter
     * **/
    public String getMovie() {
        return movie;
    }

    /**
     * Setter
     * **/
    public void setMovie(final String movie) {
        this.movie = movie;
    }

    /**
     * Getter
     * **/
    public String getFeature() {
        return feature;
    }

    /**
     * Setter
     * **/
    public void setFeature(final String feature) {
        this.feature = feature;
    }

    /**
     * Getter
     * **/
    public CredentialsData getCredentials() {
        return credentials;
    }

    /**
     * Setter
     * **/
    public void setCredentials(final CredentialsData credentials) {
        this.credentials = credentials;
    }

    /**
     * Getter
     * **/
    public String getStartsWith() {
        return startsWith;
    }

    /**
     * Setter
     * **/
    public void setStartsWith(final String startsWith) {
        this.startsWith = startsWith;
    }

    /**
     * Getter
     * **/
    public String getCount() {
        return count;
    }

    /**
     * Setter
     * **/
    public void setCount(final String count) {
        this.count = count;
    }

    /**
     * Getter
     * **/
    public int getRate() {
        return rate;
    }

    /**
     * Setter
     * **/
    public void setRate(final int rate) {
        this.rate = rate;
    }

    /**
     * Getter
     * **/
    public FilterData getFilters() {
        return filters;
    }

    /**
     * Setter
     * **/
    public void setFilters(final FilterData filters) {
        this.filters = filters;
    }
}
