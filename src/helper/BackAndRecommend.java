package helper;

import actionmaker.FilterActionClass;
import com.fasterxml.jackson.databind.node.ArrayNode;
import factory.*;
import io.InputAll;
import io.MovieData;
import io.Notifications;
import io.UserData;
import pages.MainPage;
import pages.Visitor;

import java.util.ArrayList;
import java.util.Comparator;

public final class BackAndRecommend {
    /**
     * Private Constructor
     * for utility classes
     * **/
    private BackAndRecommend() { }

    public static MainPage backToOldPage(final UserData user, final MainPage actualPage,
                                         final ArrayNode output) {
        Visitor visitor = FilterActionClass.getInstance();
        if (user == null) {
            visitor.setOutput("Error", new ArrayList<>(), null, output);
            return actualPage;
        }
        if (user.getPageSt().size() == 1) {
            visitor.setOutput("Error", new ArrayList<>(), null, output);
            return actualPage;
        }
        user.getPageSt().pop();
        BlackBox generator;
        switch (user.getPageSt().peek()) {
            case "movies" -> {
                user.setCurrentMovieList(user.getAllTime());
                visitor.setOutput(null, user.getCurrentMovieList(), user, output);
                user.setPage("movies");
                generator = new MoviePageGenerator();
            }
            case "upgrades" -> {
                user.setPage("upgrades");
                generator = new UpgradesGenerator();
            }
            case "see details" -> {
                user.setPage("see details");
                generator = new SeeDetailsGenerator();
                ArrayList<MovieData> movieAvailable = new ArrayList<>();
                movieAvailable.add(user.getSeeDetailsMovie());
                visitor.setOutput(null, movieAvailable, user, output);
            }
            default -> {
                user.setPage("homepage");
                generator = new HomepageExistentAccountGenerator();
            }
        }
        return generator.orderPage();
    }

    /**
     * Method that implements the algorithm for recommending
     * a movie to the user and also push the movie to
     * notifications queue
     * **/
    public static void recommendMovie(final UserData user, final ArrayNode output,
                                      final InputAll input) {
        if (user == null) {
            return;
        }
        if (user.getCredentials().getAccountType().equals("standard")) {
            return;
        }
        Visitor visitor = FilterActionClass.getInstance();
        ArrayList<GenreLikes> listOfGenres = new ArrayList<>();
        for (int i = 0; i < user.getLikedMovies().size(); ++i) {
            for (int j = 0; j < user.getLikedMovies().get(i).getGenres().size(); ++j) {
                int finalI = i;
                int finalJ = j;
                GenreLikes aux = listOfGenres.stream().filter(gl -> gl.getGenre().equals(user.
                        getLikedMovies().get(finalI).getGenres().get(finalJ))).findFirst().
                        orElse(null);
                if (aux == null) {
                    GenreLikes topG = new GenreLikes(1, user.getLikedMovies().get(i).
                            getGenres().get(j));
                    listOfGenres.add(topG);
                    continue;
                }
                aux.setNumLikes(aux.getNumLikes() + 1);
            }
        }
        listOfGenres.sort(Comparator.comparingInt(GenreLikes::getNumLikes).reversed().
                thenComparing((o1, o2) -> o1.getGenre().compareTo(o2.getGenre())));
        user.setMoviesForUser(input.getMovies());
        user.getCurrentMovieList().sort(Comparator.comparingInt(MovieData::getNumLikes).reversed());
        for (int i = 0; i < listOfGenres.size(); ++i) {
            for (int j = 0; j < user.getCurrentMovieList().size(); ++j) {
                int finalI = i;
                if (user.getCurrentMovieList().get(j).getGenres().stream().
                        filter(genre -> genre.equals(listOfGenres.get(finalI).getGenre())).
                        findFirst().orElse(null) != null && !user.getWatchedMovies().
                        contains(user.getCurrentMovieList().get(j))) {
                    Notifications notifications = new Notifications(user.getCurrentMovieList().
                            get(j).getName(), "Recommendation");
                    user.getNotifications().add(notifications);
                    visitor.setOutput(null, null, user, output);
                    return;
                }
            }
        }
        Notifications notifications = new Notifications("No recommendation", "Recommendation");
        user.getNotifications().add(notifications);
        visitor.setOutput(null, null, user, output);
    }
}

class GenreLikes {
    private int numLikes;
    private String genre;

    public GenreLikes(final int numLikes, final String genre) {
        this.numLikes = numLikes;
        this.genre = genre;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(final int numLikes) {
        this.numLikes = numLikes;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(final String genre) {
        this.genre = genre;
    }
}
