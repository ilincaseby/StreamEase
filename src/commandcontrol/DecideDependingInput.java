package commandcontrol;

import actionmaker.FilterActionClass;
import administrator.AdminDataBase;
import com.fasterxml.jackson.databind.node.ArrayNode;
import factory.BlackBox;
import factory.HomepageExistentAccountGenerator;
import factory.HomepageNoAccountGenerator;
import helper.BackAndRecommend;
import io.InputAll;
import io.UserData;
import pages.HomepageExistentAccount;
import pages.HomepageNoAccount;
import pages.MainPage;
import pages.Visitor;

import java.util.ArrayList;

public final class DecideDependingInput {
    /**
     * Private constructor
     * **/
    private DecideDependingInput() { }
    /**
     * Method to start interpreting the commands
     * **/
    public static void takeAction(final InputAll input, final ArrayNode output) {
        setMoviesFree(input);
        UserData user = null;
        MainPage page = new HomepageNoAccount();
        Visitor visitor = FilterActionClass.getInstance();

        for (int i = 0; i < input.getActions().size(); ++i) {
            switch (input.getActions().get(i).getType()) {
                case "database" -> {
                    switch (input.getActions().get(i).getFeature()) {
                        case "add" -> AdminDataBase.addMovie(input, output, input.getActions().get(i));
                        case "delete" -> AdminDataBase.deletedMovie(input, output, input.getActions().get(i));
                    }
                }
                case "back" -> page = BackAndRecommend.backToOldPage(user, page, output);
                case "subscribed" -> user = page.log(visitor, input.getActions().get(i), output, user, input);
                case "change page" -> {
                    page = page.accept(visitor, input.getActions().get(i), output, user, input);
                    if (user != null) {
                        if (user.getPage() == null) {
                            user = null;
                        }
                    }
                }
                case "on page" -> {
                    switch (input.getActions().get(i).getFeature()) {
                        case "search" -> visitor.searchMovies(user, input.getActions().get(i),
                                output);
                        case "filter" -> visitor.filterMovies(user, input.getActions().get(i),
                                output);
                        case "buy tokens" -> visitor.buyTokens(user, input.getActions().get(i),
                                output);
                        case "buy premium account" -> visitor.buyPremiumAccount(user,
                                input.getActions().get(i), output);
                        case "purchase" -> visitor.purchase(user, input.getActions().get(i),
                                output);
                        case "watch" -> visitor.watch(user, input.getActions().get(i),
                                output);
                        case "like" -> visitor.like(user, input.getActions().get(i),
                                output);
                        case "rate" -> visitor.rate(user, input.getActions().get(i),
                                output);
                        case "login" -> {
                            user = page.log(visitor, input.getActions().get(i), output, user,
                                    input);
                            if (user == null) {
                                page = new HomepageNoAccount();
                            }
                            if (user != null) {
                                if (user.getPage().equals("homepage")) {
                                    page = new HomepageExistentAccount();
                                }
                            }
                        }
                        default -> {
                            user = page.log(visitor, input.getActions().get(i), output, user,
                                    input);
                            BlackBox generate = null;
                            if (user != null) {
                                generate = new HomepageExistentAccountGenerator();
                            }
                            if (user == null) {
                                generate = new HomepageNoAccountGenerator();
                            }
                            page = generate.orderPage();
                        }
                    }
                }
                default -> visitor.setOutput("Error", new ArrayList<>(), null, output);
            }
        }
        BackAndRecommend.recommendMovie(user, output, input);
    }

    /**
     * Method to initiate for each user their movie list
     * and the number of free movies
     * **/
    public static void setMoviesFree(final InputAll input) {
        for (int i = 0; i < input.getUsers().size(); ++i) {
            input.getUsers().get(i).setIfPremium();
            input.getUsers().get(i).setMoviesForUser(input.getMovies());
            input.getUsers().get(i).setMoviesAllTime(input.getMovies());
        }
    }
}
