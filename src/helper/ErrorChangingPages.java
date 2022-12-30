package helper;

import actionmaker.FilterActionClass;
import com.fasterxml.jackson.databind.node.ArrayNode;
import factory.*;
import io.ActionsData;
import io.MovieData;
import io.UserData;
import pages.*;

import java.util.ArrayList;
import java.util.Stack;

public final class ErrorChangingPages {
    /**
     * Private constructor
     * **/
    private ErrorChangingPages() { }

    /**
     * helper method for changing pages to modularize the code
     * **/
    public static MainPage checkNoAccount(final HomepageNoAccount currentPage,
                                          final ActionsData action, final ArrayNode output,
                                          final UserData user) {
        Visitor visitor = FilterActionClass.getInstance();
        BlackBox generator;
        switch (action.getPage()) {
            case "login" -> {
                generator = new LoginGenerator();
            }
            case "register" -> {
                generator = new RegisterGenerator();
            }
            default -> {
                visitor.setOutput("Error", new ArrayList<>(), null, output);
                generator = new HomepageNoAccountGenerator();
            }
        }
        return generator.orderPage();
    }

    /**
     * helper method for changing pages to modularize the code
     * **/
    public static MainPage changeExistentAccount(final HomepageExistentAccount currentPage,
                                                 final ActionsData action, final ArrayNode output,
                                                 final UserData user) {
        Visitor visitor = FilterActionClass.getInstance();
        BlackBox generator;
        switch (action.getPage()) {
            case "logout" -> {
                user.setPage(null);
                user.setPageSt(new Stack<>());
                generator = new HomepageNoAccountGenerator();
            }
            case "register" -> {
                visitor.setOutput("Error", new ArrayList<>(), null, output);
                user.setPage(null);
                user.setPageSt(new Stack<>());
                generator = new HomepageNoAccountGenerator();
            }
            case "movies" -> {
                user.setCurrentMovieList(user.getAllTime());
                visitor.setOutput(null, user.getCurrentMovieList(), user, output);
                user.setPage("movies");
                generator = new MoviePageGenerator();
                user.getPageSt().add("movies");
            }
            case "upgrades" -> {
                user.setPage("upgrades");
                generator = new UpgradesGenerator();
                user.getPageSt().add("upgrades");
            }
            default -> {
                visitor.setOutput("Error", new ArrayList<>(), null, output);
                generator = new HomepageExistentAccountGenerator();
            }
        }
        return generator.orderPage();
    }

    /**
     * helper method for changing pages to modularize the code
     * **/
    public static MainPage changeMovies(final Movies currentPage, final ActionsData action,
                                        final ArrayNode output, final UserData user) {
        Visitor visitor = FilterActionClass.getInstance();
        BlackBox generator;
        switch (action.getPage()) {
            case "logout" -> {
                user.setPage(null);
                user.setPageSt(new Stack<>());
                generator = new HomepageNoAccountGenerator();
            }
            case "movies" -> {
                user.setCurrentMovieList(user.getAllTime());
                visitor.setOutput(null, user.getCurrentMovieList(), user, output);
                generator = new MoviePageGenerator();
                user.getPageSt().add("movies");
            }
            case "see details" -> {
                user.setSeeDetailsMovie(UserData.findByNameIsIn(user.getCurrentMovieList(),
                        action.getMovie()));
                if (user.getSeeDetailsMovie() == null) {
                    visitor.setOutput("Error", new ArrayList<>(), null, output);
                    generator = new MoviePageGenerator();
                    break;
                }
                ArrayList<MovieData> movieAvailable = new ArrayList<>();
                movieAvailable.add(user.getSeeDetailsMovie());
                visitor.setOutput(null, movieAvailable, user, output);
                user.setPage("see details");
                generator = new SeeDetailsGenerator();
                user.getPageSt().add("see details");
            }
            case "register" -> {
                visitor.setOutput("Error", new ArrayList<>(), null, output);
                user.setPage(null);
                user.setPageSt(new Stack<>());
                generator = new HomepageNoAccountGenerator();
            }
            case "homepage" -> {
                user.setPage("homepage");
                visitor.setOutput(null, new ArrayList<>(), user, output);
                generator = new HomepageExistentAccountGenerator();
                user.getPageSt().add("homepage");
            }
            default -> {
                visitor.setOutput("Error", new ArrayList<>(), null, output);
                generator = new MoviePageGenerator();
            }
        }
        return generator.orderPage();
    }

    /**
     * helper method for changing pages to modularize the code
     * **/
    public static MainPage changeUpgrades(final Upgrades currentPage, final ActionsData action,
                                          final ArrayNode output, final UserData user) {
        Visitor visitor = FilterActionClass.getInstance();
        BlackBox generator;
        switch (action.getPage()) {
            case "logout" -> {
                user.setPage(null);
                user.setPageSt(new Stack<>());
                generator = new HomepageNoAccountGenerator();
            }
            case "register" -> {
                visitor.setOutput("Error", new ArrayList<>(), null, output);
                user.setPage(null);
                user.setPageSt(new Stack<>());
                generator = new HomepageNoAccountGenerator();
            }
            case "movies" -> {
                user.setCurrentMovieList(user.getAllTime());
                visitor.setOutput(null, user.getCurrentMovieList(), user, output);
                user.setPage("movies");
                generator = new MoviePageGenerator();
                user.getPageSt().add("movies");
            }
            case "homepage" -> {
                user.setPage("homepage");
                generator = new HomepageExistentAccountGenerator();
                user.getPageSt().add("homepage");
            }
            default -> {
                visitor.setOutput("Error", new ArrayList<>(), null, output);
                generator = new UpgradesGenerator();
            }
        }
        return generator.orderPage();
    }

    /**
     * helper method for changing pages to modularize the code
     * **/
    public static MainPage changeSeeDetails(final SeeDetails currentPage, final ActionsData action,
                                            final ArrayNode output, final UserData user) {
        Visitor visitor = FilterActionClass.getInstance();
        BlackBox generator;
        switch (action.getPage()) {
            case "logout" -> {
                user.setPage(null);
                user.setPageSt(new Stack<>());
                generator = new HomepageNoAccountGenerator();
            }
            case "register" -> {
                visitor.setOutput("Error", new ArrayList<>(), null, output);
                user.setPage(null);
                user.setPageSt(new Stack<>());
                generator = new HomepageNoAccountGenerator();
            }
            case "movies" -> {
                user.setCurrentMovieList(user.getAllTime());
                visitor.setOutput(null, user.getCurrentMovieList(), user, output);
                user.setPage("movies");
                generator = new MoviePageGenerator();
                user.getPageSt().add("movies");
            }
            case "homepage" -> {
                user.setPage("homepage");
                generator = new HomepageExistentAccountGenerator();
                user.getPageSt().add("homepage");
            }
            case "upgrades" -> {
                user.setPage("upgrades");
                generator = new UpgradesGenerator();
                user.getPageSt().add("upgrades");
            }
            default -> {
                visitor.setOutput("Error", new ArrayList<>(), null, output);
                generator = new SeeDetailsGenerator();
            }
        }
        return generator.orderPage();
    }

    /**
     * helper method for changing pages to modularize the code
     * **/
    public static MainPage changeLoginOrRegister(final MainPage currentPage,
                                                 final ActionsData action, final ArrayNode output,
                                                 final UserData user) {
        Visitor visitor = FilterActionClass.getInstance();
        BlackBox generator;
        switch (action.getPage()) {
            case "logout" -> {
                visitor.setOutput("Error", new ArrayList<>(), null, output);
                generator = new HomepageNoAccountGenerator();
            }
            default -> {
                visitor.setOutput("Error", new ArrayList<>(), null, output);
                return currentPage;
            }
        }
        return generator.orderPage();
    }
}
