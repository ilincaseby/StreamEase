package helper;

import actionmaker.FilterActionClass;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.ActionsData;
import io.MovieData;
import io.UserData;
import pages.Visitor;

import java.util.ArrayList;

public final class WatchLikeRateClass {
    public static final int FIVE = 5;
    /**
     * Private constructor for utility class
     * **/
    private WatchLikeRateClass() { }

    /**
     * helper method for watching a movie to modularize the code
     * **/
    public static void watchHelper(final UserData user, final ActionsData action,
                                   final ArrayNode output) {
        Visitor visitor = FilterActionClass.getInstance();
        boolean isOkToContinue = auxiliaryCheck(user, output, visitor, user.getPurchasedMovies());
        if (!isOkToContinue) {
            return;
        }
        if (!user.getSeeDetailsMovie().equals(UserData.findByNameIsIn(user.getWatchedMovies(),
                user.getSeeDetailsMovie().getName()))) {
            user.getWatchedMovies().add(user.getSeeDetailsMovie());
        }
        ArrayList<MovieData> movie = new ArrayList<>();
        movie.add(user.getSeeDetailsMovie());
        visitor.setOutput(null, movie, user, output);
    }

    /**
     * helper method for watching a movie to modularize the code
     * **/
    public static void likeHelper(final UserData user, final ActionsData action,
                                  final ArrayNode output) {
        Visitor visitor = FilterActionClass.getInstance();
        boolean isOkToContinue = auxiliaryCheck(user, output, visitor, user.getWatchedMovies());
        if (!isOkToContinue) {
            return;
        }
        if (user.getSeeDetailsMovie().equals(UserData.findByNameIsIn(user.getLikedMovies(),
                user.getSeeDetailsMovie().getName()))) {
            visitor.setOutput("Error", new ArrayList<>(), null, output);
            return;
        }
        user.getLikedMovies().add(user.getSeeDetailsMovie());
        user.getSeeDetailsMovie().setNumLikes(user.getSeeDetailsMovie().getNumLikes() + 1);
        ArrayList<MovieData> movie = new ArrayList<>();
        movie.add(user.getSeeDetailsMovie());
        visitor.setOutput(null, movie, user, output);
    }

    /**
     * helper method for watching a movie to modularize the code
     * **/
    public static void rateHelper(final UserData user, final ActionsData action,
                                  final ArrayNode output) {
        Visitor visitor = FilterActionClass.getInstance();
        boolean isOkToContinue = auxiliaryCheck(user, output, visitor, user.getWatchedMovies());
        if (!isOkToContinue) {
            return;
        }
//        if (user.getSeeDetailsMovie().equals(UserData.findByNameIsIn(user.getRatedMovies(),
//                user.getSeeDetailsMovie().getName()))) {
//            visitor.setOutput("Error", new ArrayList<>(), null, output);
//            return;
//        }
        if (action.getRate() > FIVE || action.getRate() < 0) {
            visitor.setOutput("Error", new ArrayList<>(), null, output);
            return;
        }
        if (!user.getSeeDetailsMovie().equals(UserData.findByNameIsIn(user.getRatedMovies(),
                user.getSeeDetailsMovie().getName()))) {
            user.getRatedMovies().add(user.getSeeDetailsMovie());
            user.getSeeDetailsMovie().setNumPeopleRatings(user.getSeeDetailsMovie().
                    getNumPeopleRatings() + 1);
        }
        user.getSeeDetailsMovie().setNumRatings(user.getSeeDetailsMovie().getNumRatings() + 1);
        user.getSeeDetailsMovie().setRating(
                user.getSeeDetailsMovie().getRating() + action.getRate());
        ArrayList<MovieData> movie = new ArrayList<>();
        movie.add(user.getSeeDetailsMovie());
        visitor.setOutput(null, movie, user, output);
    }

    /**
     * Method to do the checks that are in common
     * for watch, like, rate
     * **/
    private static boolean auxiliaryCheck(final UserData user, final ArrayNode output,
                                         final Visitor visitor,
                                         final ArrayList<MovieData> moviesToCheck) {
        if (user == null) {
            visitor.setOutput("Error", new ArrayList<>(), null, output);
            return false;
        }
        if (!user.getPage().equals("see details")) {
            visitor.setOutput("Error", new ArrayList<>(), null, output);
            return false;
        }
        if (!user.getSeeDetailsMovie().equals(UserData.findByNameIsIn(moviesToCheck,
                user.getSeeDetailsMovie().getName()))) {
            visitor.setOutput("Error", new ArrayList<>(), null, output);
            return false;
        }
        return true;
    }
}
