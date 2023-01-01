package actionmaker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import helper.ErrorChangingPages;
import io.ActionsData;
import io.MovieData;
import io.Notifications;
import io.UserData;
import pages.*;

import java.util.ArrayList;

public abstract class ChangePageClass implements Visitor {
    /**
     * Method to call when changing the page is desired
     * **/
    public MainPage changePage(final HomepageNoAccount currentPage, final ActionsData action,
                               final ArrayNode output, final UserData user) {
        return ErrorChangingPages.checkNoAccount(currentPage, action, output, user);
    }

    /**
     * Method to call when changing the page is desired
     * **/
    public MainPage changePage(final HomepageExistentAccount currentPage, final ActionsData action,
                               final ArrayNode output, final UserData user) {
        return ErrorChangingPages.changeExistentAccount(currentPage, action, output, user);
    }

    /**
     * Method to call when changing the page is desired
     * **/
    public MainPage changePage(final Movies currentPage, final ActionsData action,
                               final ArrayNode output, final UserData user) {
        return ErrorChangingPages.changeMovies(currentPage, action, output, user);
    }

    /**
     * Method to call when changing the page is desired
     * **/
    public MainPage changePage(final Upgrades currentPage, final ActionsData action,
                               final ArrayNode output, final UserData user) {
        return ErrorChangingPages.changeUpgrades(currentPage, action, output, user);
    }

    /**
     * Method to call when changing the page is desired
     * **/
    public MainPage changePage(final SeeDetails currentPage, final ActionsData action,
                               final ArrayNode output, final UserData user) {
        return ErrorChangingPages.changeSeeDetails(currentPage, action, output, user);
    }

    /**
     * Method to call when changing the page is desired
     * **/
    public MainPage changePage(final Login currentPage, final ActionsData action,
                               final ArrayNode output, final UserData user) {
        return ErrorChangingPages.changeLoginOrRegister(currentPage, action, output, user);
    }

    /**
     * Method to call when changing the page is desired
     * **/
    public MainPage changePage(final Register currentPage, final ActionsData action,
                               final ArrayNode output, final UserData user) {
        return ErrorChangingPages.changeLoginOrRegister(currentPage, action, output, user);
    }

    /**
     * Add to output a message
     * **/
    public void setOutput(final String error, final ArrayList<MovieData> moviesList,
                          final UserData user, final ArrayNode output) {
        ObjectNode node = (new ObjectMapper()).createObjectNode();
        node.put("error", error);
        if (moviesList != null) {
            ArrayNode currentMovieList = (new ObjectMapper()).createArrayNode();
            addMovieToOutput(moviesList, currentMovieList);
            node.set("currentMoviesList", currentMovieList);
        }
        if (moviesList == null) {
            node.put("currentMoviesList", (String) null);
        }
        if (user == null) {
            node.put("currentUser", (String) null);
            output.add(node);
            return;
        }
        ObjectNode userNode = (new ObjectMapper()).createObjectNode();
        ObjectNode credential = (new ObjectMapper()).createObjectNode();
        credential.put("name", user.getCredentials().getName());
        credential.put("password", user.getCredentials().getPassword());
        credential.put("accountType", user.getCredentials().getAccountType());
        credential.put("country", user.getCredentials().getCountry());
        credential.put("balance", user.getCredentials().getBalance());
        userNode.set("credentials", credential);
        userNode.put("tokensCount", user.getTokensCount());
        userNode.put("numFreePremiumMovies", user.getNumFreePremiumMovies());
        ArrayNode purchased = (new ObjectMapper()).createArrayNode();
        addMovieToOutput(user.getPurchasedMovies(), purchased);
        userNode.set("purchasedMovies", purchased);
        ArrayNode watched = (new ObjectMapper()).createArrayNode();
        addMovieToOutput(user.getWatchedMovies(), watched);
        userNode.set("watchedMovies", watched);
        ArrayNode liked = (new ObjectMapper()).createArrayNode();
        addMovieToOutput(user.getLikedMovies(), liked);
        userNode.set("likedMovies", liked);
        ArrayNode rated = (new ObjectMapper()).createArrayNode();
        addMovieToOutput(user.getRatedMovies(), rated);
        userNode.set("ratedMovies", rated);
        ArrayNode outNotifications = (new ObjectMapper()).createArrayNode();
        addNotification(user.getNotifications(), outNotifications);
        userNode.set("notifications", outNotifications);
        node.set("currentUser", userNode);
        output.add(node);
    }

    private void addNotification(final ArrayList<Notifications> notifications,
                                 final ArrayNode outNotifications) {
        for (int i = 0; i < notifications.size(); ++i) {
            ObjectNode newNode = (new ObjectMapper()).createObjectNode();
            newNode.put("movieName", notifications.get(i).getMovieName());
            newNode.put("message", notifications.get(i).getMessage());
            outNotifications.add(newNode);
        }
    }

    /**
     * helper method for setOutput to modularize the code
     * **/
    private void addMovieToOutput(final ArrayList<MovieData> moviesList,
                                  final ArrayNode currentMovieList) {
        for (int i = 0; i < moviesList.size(); ++i) {
            ObjectNode newNode = (new ObjectMapper()).createObjectNode();
            newNode.put("name", moviesList.get(i).getName());
            newNode.put("year", String.valueOf(moviesList.get(i).getYear()));
            newNode.put("duration", moviesList.get(i).getDuration());
            ArrayNode genres = (new ObjectMapper()).createArrayNode();
            for (int j = 0; j < moviesList.get(i).getGenres().size(); ++j) {
                genres.add(moviesList.get(i).getGenres().get(j));
            }
            ArrayNode countriesBanned = (new ObjectMapper()).createArrayNode();
            for (int j = 0; j < moviesList.get(i).getCountriesBanned().size(); ++j) {
                countriesBanned.add(moviesList.get(i).getCountriesBanned().get(j));
            }
            ArrayNode actors = (new ObjectMapper()).createArrayNode();
            for (int j = 0; j < moviesList.get((i)).getActors().size(); ++j) {
                actors.add(moviesList.get(i).getActors().get(j));
            }
            newNode.set("genres", genres);
            newNode.set("actors", actors);
            newNode.set("countriesBanned", countriesBanned);
            newNode.put("numLikes", moviesList.get(i).getNumLikes());
            newNode.put("rating", moviesList.get(i).getFinalRating());
            newNode.put("numRatings", moviesList.get(i).getNumPeopleRatings());
            currentMovieList.add(newNode);
        }
    }
}
