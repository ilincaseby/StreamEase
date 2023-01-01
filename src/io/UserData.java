package io;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

public class UserData {
    private CredentialsData credentials;

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
    private int tokensCount = 0;
    private int numFreePremiumMovies;
    private ArrayList<MovieData> purchasedMovies;
    private ArrayList<MovieData> watchedMovies;
    private ArrayList<MovieData> likedMovies;
    private ArrayList<MovieData> ratedMovies;
    private ArrayList<MovieData> currentMovieList;
    private ArrayList<MovieData> allTime;
    private ArrayList<Notifications> notifications;
    private MovieData seeDetailsMovie;
    private String page;
    private ArrayList<String> favGenres;
    private Stack<String> pageSt;
    public static final int FIFTEEN = 15;

    public UserData() {
        notifications = new ArrayList<>();
        purchasedMovies = new ArrayList<>();
        watchedMovies = new ArrayList<>();
        likedMovies = new ArrayList<>();
        ratedMovies = new ArrayList<>();
        currentMovieList = new ArrayList<>();
        favGenres = new ArrayList<>();
        pageSt = new Stack<>();
        seeDetailsMovie = null;
        page = null;
        allTime = new ArrayList<>();
    }

    /**
     * Getter
     * **/
    public Stack<String> getPageSt() {
        return pageSt;
    }

    /**
     * Setter
     * **/
    public void setPageSt(final Stack<String> pageSt) {
        this.pageSt = pageSt;
    }

    /**
     * Getter
     * **/
    public ArrayList<Notifications> getNotifications() {
        return notifications;
    }

    /**
     * Setter
     * **/
    public void setNotifications(final ArrayList<Notifications> notifications) {
        this.notifications = notifications;
    }

    /**
     * Getter
     * **/
    public ArrayList<String> getFavGenres() {
        return favGenres;
    }

    /**
     * Setter
     * **/
    public void setFavGenres(final ArrayList<String> favGenres) {
        this.favGenres = favGenres;
    }

    /**
     * Getter
     * **/
    public ArrayList<MovieData> getAllTime() {
        return allTime;
    }

    /**
     * Setter
     * **/
    public void setAllTime(final ArrayList<MovieData> allTime) {
        this.allTime = allTime;
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
    public MovieData getSeeDetailsMovie() {
        return seeDetailsMovie;
    }

    /**
     * Setter
     * **/
    public void setSeeDetailsMovie(final MovieData seeDetailsMovie) {
        this.seeDetailsMovie = seeDetailsMovie;
    }

    /**
     * Return the name wanted by name
     * Return null in case of unavailability
     * **/
    public static MovieData findByNameIsIn(final Collection<MovieData> movies,
                                           final String nameIsIn) {
        return movies.stream().filter(movie -> nameIsIn.equals(movie.getName()))
                .findFirst().orElse(null);
    }

    /**
     * Method to call everytime before working with a user
     * to set its available movies
     * **/
    public void setMoviesForUser(final ArrayList<MovieData> movies) {
        this.currentMovieList = new ArrayList<>();
        for (MovieData movie : movies) {
            if (!movie.getCountriesBanned().contains(this.credentials.getCountry())) {
                this.currentMovieList.add(movie);
            }
        }
    }

    /**
     * Method to call everytime before working with a user
     * to set its available movies
     * CAUTION! After the method is called please do NOT
     * change the allTime!!!
     * **/
    public void setMoviesAllTime(final ArrayList<MovieData> movies) {
        for (MovieData movie : movies) {
            if (!movie.getCountriesBanned().contains(this.credentials.getCountry())) {
                this.allTime.add(movie);
            }
        }
    }

    /**
     * Initiate free movies if the new account is premium
     * **/
    public void setIfPremium() {
        this.numFreePremiumMovies = FIFTEEN;
    }

    /**
     * Getter
     * **/
    public ArrayList<MovieData> getCurrentMovieList() {
        return currentMovieList;
    }

    /**
     * Setter
     * **/
    public void setCurrentMovieList(final ArrayList<MovieData> currentMovieList) {
        this.currentMovieList = currentMovieList;
    }

    /**
     * Getter
     * **/
    public int getTokensCount() {
        return tokensCount;
    }

    /**
     * Setter
     * **/
    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    /**
     * Getter
     * **/
    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    /**
     * Setter
     * **/
    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    /**
     * Getter
     * **/
    public ArrayList<MovieData> getPurchasedMovies() {
        return purchasedMovies;
    }

    /**
     * Setter
     * **/
    public void setPurchasedMovies(final ArrayList<MovieData> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    /**
     * Getter
     * **/
    public ArrayList<MovieData> getWatchedMovies() {
        return watchedMovies;
    }

    /**
     * Setter
     * **/
    public void setWatchedMovies(final ArrayList<MovieData> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    /**
     * Getter
     * **/
    public ArrayList<MovieData> getLikedMovies() {
        return likedMovies;
    }

    /**
     * Setter
     * **/
    public void setLikedMovies(final ArrayList<MovieData> likedMovies) {
        this.likedMovies = likedMovies;
    }

    /**
     * Getter
     * **/
    public ArrayList<MovieData> getRatedMovies() {
        return ratedMovies;
    }

    /**
     * Setter
     * **/
    public void setRatedMovies(final ArrayList<MovieData> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    /**
     * Auxiliary Constructor for just Credentials given
     * **/
    public UserData(final CredentialsData credentials) {
        this();
        this.credentials = credentials;
    }

    /**
     * Notify a user about an added movie
     * which contains a subscribed genre
     * **/
    public void addAndNotify(final MovieData movie) {
        if (movie.getCountriesBanned().stream().filter(country -> this.getCredentials().
                getCountry().equals(country)).findFirst().orElse(null) != null) {
            return;
        }
        this.allTime.add(movie);
        for (String favGenre : this.favGenres) {
            if (movie.getGenres().stream().filter(favGenre::equals).
                    findFirst().orElse(null) != null) {
                Notifications notifications = new Notifications(movie.getName(), "ADD");
                this.notifications.add(notifications);
                break;
            }
        }
    }

    /**
     * Method to notify a user that a movie is deleted
     * **/
    public void deleteAndNotify(final String title) {
        if (this.allTime.stream().filter(movie -> movie.getName().equals(title)).
                findFirst().orElse(null) == null) {
            return;
        }
        if (this.purchasedMovies.stream().filter(movie -> movie.getName().equals(title)).
                findFirst().orElse(null) != null) {
            Notifications notifications = new Notifications(title, "DELETE");
            this.notifications.add(notifications);
            if (this.credentials.getAccountType().equals("standard")) {
                this.tokensCount += 2;
            }
            if (this.credentials.getAccountType().equals("premium")) {
                this.numFreePremiumMovies++;
            }
        }
        for (int i = 0; i < this.purchasedMovies.size(); ++i) {
            if (this.purchasedMovies.get(i).getName().equals(title)) {
                this.purchasedMovies.remove(i);
                break;
            }
        }
        for (int i = 0; i < this.watchedMovies.size(); ++i) {
            if (this.watchedMovies.get(i).getName().equals(title)) {
                this.watchedMovies.remove(i);
                break;
            }
        }
        for (int i = 0; i < this.likedMovies.size(); ++i) {
            if (this.likedMovies.get(i).getName().equals(title)) {
                this.likedMovies.remove(i);
                break;
            }
        }
        for (int i = 0; i < this.ratedMovies.size(); ++i) {
            if (this.ratedMovies.get(i).getName().equals(title)) {
                this.ratedMovies.remove(i);
                break;
            }
        }
    }
}
