package actionmaker;

import com.fasterxml.jackson.databind.node.ArrayNode;
import helper.SelectMovies;
import io.ActionsData;
import io.InputAll;
import io.UserData;
import pages.SeeDetails;
import pages.Visitor;

public final class FilterActionClass extends ActionsOnFilms implements Visitor {
    private static FilterActionClass instance = null;
    private FilterActionClass() { }

    /**
     * SingleTon design Pattern
     * **/
    public static FilterActionClass getInstance() {
        if (instance == null) {
            instance = new FilterActionClass();
        }
        return instance;
    }

    @Override
    public UserData doFeatures(final SeeDetails currentPage, final ActionsData action,
                               final ArrayNode output, final InputAll input, final UserData user) {

        return user;
    }

    @Override
    public void searchMovies(final UserData user, final ActionsData action,
                             final ArrayNode output) {
        SelectMovies.searchAction(user, action, output);
    }

    @Override
    public void filterMovies(final UserData user, final ActionsData action,
                             final ArrayNode output) {
        SelectMovies.filterAction(user, action, output);
    }
}
