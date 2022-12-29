package helper;

import actionmaker.FilterActionClass;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.ActionsData;
import io.MovieData;
import io.UserData;
import pages.Visitor;

import java.util.ArrayList;

public final class BuyClass {
    public static final int TEN = 10;
    /**
     * Private constructor
     * **/
    private BuyClass() { }
    /**
     * helper method for buying tokens action
     * Purpose: modularize the code
     * **/
    public static void getTokens(final UserData user, final ActionsData action,
                                 final ArrayNode output) {
        Visitor visitor = FilterActionClass.getInstance();
        if (checkForErrorsGeneral(user, "upgrades", output, visitor)) {
            return;
        }
        int balance = Integer.parseInt(user.getCredentials().getBalance());
        int tokenToBuy = Integer.parseInt(action.getCount());
        if (balance < tokenToBuy) {
            visitor.setOutput("Error", new ArrayList<>(), null, output);
            return;
        }
        user.setTokensCount(user.getTokensCount() + tokenToBuy);
        balance -= tokenToBuy;
        user.getCredentials().setBalance(Integer.toString(balance));
    }

    /**
     * helper method for upgrading for a premium account action
     * Purpose: modularize the code
     * **/
    public static void getPremiumAccount(final UserData user, final ActionsData action,
                                         final ArrayNode output) {
        Visitor visitor = FilterActionClass.getInstance();
        if (checkForErrorsGeneral(user, "upgrades", output, visitor)) {
            return;
        }
        int balance = user.getTokensCount();
        if (user.getCredentials().getAccountType().equals("premium") || balance < TEN) {
            visitor.setOutput("Error", new ArrayList<>(), null, output);
            return;
        }
        user.getCredentials().setAccountType("premium");
        user.setTokensCount(balance - TEN);
    }

    /**
     * helper method for buying a movie action
     * Purpose: modularize the code
     * **/
    public static void buyMovie(final UserData user, final ActionsData action,
                                final ArrayNode output) {
        Visitor visitor = FilterActionClass.getInstance();
        if (checkForErrorsGeneral(user, "see details", output, visitor)) {
            return;
        }
        if (checkAvailabilityAndAccType(user, output, visitor)) {
            return;
        }
        user.setTokensCount(user.getTokensCount() - 2);
        user.getPurchasedMovies().add(user.getSeeDetailsMovie());
        ArrayList<MovieData> movie = new ArrayList<>();
        movie.add(user.getSeeDetailsMovie());
        visitor.setOutput(null, movie, user, output);
    }

    /**
     * Auxiliary method to check things that are in common
     * for getTokens, getPremiumAccount and buyMovie methods
     * **/
    private static boolean checkForErrorsGeneral(final UserData user, final String pageToCheck,
                                                 final ArrayNode output, final Visitor visitor) {
        if (user == null) {
            visitor.setOutput("Error", new ArrayList<>(), null, output);
            return true;
        }
        if (!user.getPage().equals(pageToCheck)) {
            visitor.setOutput("Error", new ArrayList<>(), null, output);
            return true;
        }
        return false;
    }

    /**
     * Auxiliary method for buy a movie action that
     * treats edge cases
     * **/
    private static boolean checkAvailabilityAndAccType(final UserData user, final ArrayNode output,
                                                       final Visitor visitor) {
        if (user.getSeeDetailsMovie() == null) {
            visitor.setOutput("Error", new ArrayList<>(), null, output);
            return true;
        }
        if (user.getSeeDetailsMovie().equals(UserData.findByNameIsIn(user.getPurchasedMovies(),
                user.getSeeDetailsMovie().getName()))) {
            visitor.setOutput("Error", new ArrayList<>(), null, output);
            return true;
        }
        if (user.getNumFreePremiumMovies() > 0
                && user.getCredentials().getAccountType().equals("premium")) {
            user.getPurchasedMovies().add(user.getSeeDetailsMovie());
            user.setNumFreePremiumMovies(user.getNumFreePremiumMovies() - 1);
            ArrayList<MovieData> movie = new ArrayList<>();
            movie.add(user.getSeeDetailsMovie());
            visitor.setOutput(null, movie, user, output);
            return true;
        }
        if (user.getTokensCount() < 2) {
            visitor.setOutput("Error", new ArrayList<>(), null, output);
            return true;
        }
        return false;
    }
}
