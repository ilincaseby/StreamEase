package helper;

import actionmaker.FilterActionClass;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.ActionsData;
import io.InputAll;
import io.MovieData;
import io.UserData;
import pages.SeeDetails;
import pages.Visitor;

import java.util.ArrayList;
import java.util.Comparator;

public final class SelectMovies {
    /**
     * Private constructor for utility class
     * **/
    private SelectMovies() { }

    /**
     * helper method to subscribe to a
     * genre included in the current movie
     * **/
    public static void subscribe(final SeeDetails currentPage, final ActionsData action,
                                 final ArrayNode output, final InputAll input,
                                 final UserData user) {
        Visitor visitor = FilterActionClass.getInstance();
        if (user.getSeeDetailsMovie() == null) {
            visitor.setOutput("Error", new ArrayList<>(), null, output);
            return;
        }
        if (!containGenre(user.getSeeDetailsMovie(), action.getSubscribedGenre())) {
            visitor.setOutput("Error", new ArrayList<>(), null, output);
            return;
        }
        if (user.getFavGenres().stream().filter(genre -> action.
                getSubscribedGenre().equals(genre)).findFirst().orElse(null) != null) {
            visitor.setOutput("Error", new ArrayList<>(), null, output);
            return;
        }
        user.getFavGenres().add(action.getSubscribedGenre());
    }

    /**
     * helper method for search action
     * Purpose: modularize the code
     * **/
    public static void searchAction(final UserData user, final ActionsData action,
                                    final ArrayNode output) {
        Visitor visitor = FilterActionClass.getInstance();
        if (checkErrors(user, output, visitor)) {
            return;
        }
        user.setCurrentMovieList(addForStartsWith(user, action.getStartsWith(), user.getAllTime(),
                visitor, output));
    }

    /**
     * helper method for filter action
     * Purpose: modularize the code
     * **/
    public static void filterAction(final UserData user, final ActionsData action,
                                    final ArrayNode output) {
        Visitor visitor = FilterActionClass.getInstance();
        if (checkErrors(user, output, visitor)) {
            return;
        }
        ArrayList<MovieData> moviesAfterSelectWithContain = filterMoviesOnContains(
                user.getAllTime(), action);
        Comparator<MovieData> movieDataComparatorAfterDuration
                = Comparator.comparing(MovieData::getDuration, Comparator.reverseOrder());
        Comparator<MovieData> movieDataComparatorAfterRating
                = Comparator.comparing(MovieData::getFinalRating, Comparator.reverseOrder());
        if (action.getFilters().getSort() != null) {
            if (action.getFilters().getSort().getDuration() != null
                    && action.getFilters().getSort().getRating() == null) {
                durNotNullRatNull(moviesAfterSelectWithContain,
                        movieDataComparatorAfterDuration, action);
            }
            if (action.getFilters().getSort().getDuration() == null
                    && action.getFilters().getSort().getRating() != null) {
                durNullRatNotNull(moviesAfterSelectWithContain,
                        movieDataComparatorAfterRating, action);
            }
            if (action.getFilters().getSort().getRating() != null
                    && action.getFilters().getSort().getDuration() != null) {
                durNotNullRatNotNull(moviesAfterSelectWithContain,
                        movieDataComparatorAfterDuration, movieDataComparatorAfterRating, action);
            }
        }
        user.setCurrentMovieList(moviesAfterSelectWithContain);
        visitor.setOutput(null, user.getCurrentMovieList(), user, output);
    }

    /**
     * Sort depending on some conditions
     * **/
    private static void durNotNullRatNull(final ArrayList<MovieData> filteredMovies,
                                          final Comparator<MovieData> comparator,
                                          final ActionsData action) {
        if (action.getFilters().getSort().getDuration().equals("decreasing")) {
            filteredMovies.sort(comparator);
        }
        if (action.getFilters().getSort().getDuration().equals("increasing")) {
            filteredMovies.sort(comparator.reversed());
        }
    }

    /**
     * Sort depending on some conditions
     * **/
    private static void durNullRatNotNull(final ArrayList<MovieData> filteredMovies,
                                          final Comparator<MovieData> comparator,
                                          final ActionsData action) {
        if (action.getFilters().getSort().getRating().equals("decreasing")) {
            filteredMovies.sort(comparator);
        }
        if (action.getFilters().getSort().getRating().equals("increasing")) {
            filteredMovies.sort(comparator.reversed());
        }
    }

    /**
     * Sort depending on some conditions
     * **/
    private static void durNotNullRatNotNull(final ArrayList<MovieData> filteredMovies,
                                             final Comparator<MovieData> compDur,
                                             final Comparator<MovieData> compRat,
                                             final ActionsData action) {
        if (action.getFilters().getSort().getRating().equals("increasing")
                && action.getFilters().getSort().getDuration().equals("increasing")) {
            filteredMovies.sort(compDur.reversed().thenComparing(compRat.reversed()));
        }
        if (action.getFilters().getSort().getRating().equals("decreasing")
                && action.getFilters().getSort().getDuration().equals("increasing")) {
            filteredMovies.sort(compDur.reversed().thenComparing(compRat));
        }
        if (action.getFilters().getSort().getRating().equals("decreasing")
                && action.getFilters().getSort().getDuration().equals("decreasing")) {
            filteredMovies.sort(compDur.thenComparing(compRat));
        }
        if (action.getFilters().getSort().getRating().equals("increasing")
                && action.getFilters().getSort().getDuration().equals("decreasing")) {
            filteredMovies.sort(compDur.thenComparing(compRat.reversed()));
        }
    }

    /**
     * Auxiliary method that checks common
     * errors for searchAction and filterAction methods
     * **/
    private static boolean checkErrors(final UserData user, final ArrayNode output,
                                       final Visitor visitor) {
        if (user == null) {
            visitor.setOutput("Error", new ArrayList<>(), null, output);
            return true;
        }
        if (!user.getPage().equals("movies")) {
            visitor.setOutput("Error", new ArrayList<>(), null, output);
            return true;
        }
        return false;
    }

    /**
     * Add movies that respect a condition
     * **/
    private static ArrayList<MovieData> addForStartsWith(final UserData user, final String prefix,
                                                         final ArrayList<MovieData> movies,
                                                         final Visitor visitor,
                                                         final ArrayNode output) {
        ArrayList<MovieData> newCurrentMovieList = new ArrayList<>();
        for (MovieData movie : movies) {
            if (movie.getName().startsWith(prefix)) {
                newCurrentMovieList.add(movie);
            }
        }
        visitor.setOutput(null, newCurrentMovieList, user, output);
        return newCurrentMovieList;
    }

    /**
     * Add movies that respect some condition
     * **/
    private static ArrayList<MovieData> filterMoviesOnContains(final ArrayList<MovieData> movies,
                                                               final ActionsData action) {
        ArrayList<MovieData> filtered = new ArrayList<>();
        for (MovieData movie : movies) {
            if (containAllRequirements(movie, action)) {
                filtered.add(movie);
            }
        }
        return filtered;
    }

    /**
     * helper for filterMoviesOnContains method
     * **/
    private static boolean containAllRequirements(final MovieData movie,
                                                  final ActionsData action) {
        if (action.getFilters().getContains() == null) {
            return true;
        }
        if (action.getFilters().getContains().getActors() != null) {
            for (int i = 0; i < action.getFilters().getContains().getActors().size(); ++i) {
                if (!movie.getActors().contains(action.getFilters()
                        .getContains().getActors().get(i))) {
                    return false;
                }
            }
        }
        if (action.getFilters().getContains().getGenre() != null) {
            for (int i = 0; i < action.getFilters().getContains().getGenre().size(); ++i) {
                if (!movie.getGenres().contains(action.getFilters()
                        .getContains().getGenre().get(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * auxiliary method to check if the genre
     * is contained by a film
     * **/

    private static boolean containGenre(final MovieData movie, final String genre) {
        return movie.getGenres().contains(genre);
    }
}
