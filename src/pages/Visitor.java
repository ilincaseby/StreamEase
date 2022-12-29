package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import io.ActionsData;
import io.InputAll;
import io.MovieData;
import io.UserData;

import javax.swing.*;
import java.util.ArrayList;

public interface Visitor {
    /**
     * Change the page when the current is a homepage with no account logged
     * **/
    MainPage changePage(HomepageNoAccount currentPage, ActionsData action, ArrayNode output,
                        UserData user);

    /**
     * Change the page when the current is a homepage with an account logged
     * **/
    MainPage changePage(HomepageExistentAccount currentPage, ActionsData action, ArrayNode output,
                        UserData user);

    /**
     * Change the page when the current is a movie page
     * **/
    MainPage changePage(Movies currentPage, ActionsData action, ArrayNode output, UserData user);

    /**
     * Change the page when the current is a page with upgrade possibilities
     * **/
    MainPage changePage(Upgrades currentPage, ActionsData action, ArrayNode output, UserData user);

    /**
     * Change the page when the current is a page for purchase, watch, like, rate a movie
     * **/
    MainPage changePage(SeeDetails currentPage, ActionsData action, ArrayNode output,
                        UserData user);

    /**
     * Change the page when the current is a homepage with no account logged in the login section
     * **/
    MainPage changePage(Login currentPage, ActionsData action, ArrayNode output, UserData user);

    /**
     * Change the page when the current is a homepage with no account logged in the register section
     * **/
    MainPage changePage(Register currentPage, ActionsData action, ArrayNode output, UserData user);

    /**
     * Login feature
     * **/
    UserData doFeatures(Login currentPage, ActionsData action, ArrayNode output, InputAll input,
                        UserData user);

    /**
     * Register feature
     * **/
    UserData doFeatures(Register currentPage, ActionsData action, ArrayNode output, InputAll input,
                        UserData user);

    /**
     * Method to make the subsscribe action possible
     * **/
    UserData doFeatures(SeeDetails currentPage, ActionsData action, ArrayNode output,
                        InputAll input, UserData user);

    /**
     * Movie searching method
     * **/
    void searchMovies(UserData user, ActionsData action, ArrayNode output);

    /**
     * Movie filter method
     * **/
    void filterMovies(UserData user, ActionsData action, ArrayNode output);

    /**
     * Mehtod to call when the user wants to buy tokens
     * **/
    void buyTokens(UserData user, ActionsData action, ArrayNode output);

    /**
     * Method to call when the user with a standard account
     * wants to purchase a premium one
     * CAUTION: The method works even if the user already have
     * a premium account, but all it will do is show an error, so
     * the edge case is treated
     * **/
    void buyPremiumAccount(UserData user, ActionsData action, ArrayNode output);

    /**
     * Method to call when an user wants to buy a movie
     * Edge cases like the user do not have enough
     * tokens or no free movies at all are treated and signaled
     * with an error
     * **/
    void purchase(UserData user, ActionsData action, ArrayNode output);

    /**
     * Mehtod to call when an user wants to watch a specific movie
     * Not yet purchased edge case is treated
     * **/
    void watch(UserData user, ActionsData action, ArrayNode output);

    /**
     * Method to call when an user wants to show his appreciation
     * by like option
     * Not yet watched movie edge is treated
     * **/
    void like(UserData user, ActionsData action, ArrayNode output);

    /**
     * Method to call when an user wants to rate a movie
     * Not yet watched movie edge case is treated
     * **/
    void rate(UserData user, ActionsData action, ArrayNode output);

    /**
     * helper method used for output messages in json format
     * The method is present in the visitor pattern interface
     * for possible future implementation when a different
     * output with different things contained is desired in the
     * second phase of the project
     * **/
    void setOutput(String error, ArrayList<MovieData> moviesList, UserData user, ArrayNode output);
}
