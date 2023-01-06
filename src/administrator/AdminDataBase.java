package administrator;

import actionmaker.FilterActionClass;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.ActionsData;
import io.InputAll;
import pages.Visitor;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public final class AdminDataBase extends Observable {
    /**
     * method to add a movie
     * **/
    public void addMovie(final InputAll input, final ArrayNode output,
                         final ActionsData action) {
        Visitor visitor = FilterActionClass.getInstance();
        if (input.getMovies().stream().filter(movie -> action.getAddedMovie().getName().
                equals(movie.getName())).findFirst().orElse(null) != null) {
            visitor.setOutput("Error", new ArrayList<>(), null, output);
            return;
        }
        input.getMovies().add(action.getAddedMovie());
        setChanged();
        notifyObservers(action.getAddedMovie());
    }

    /**
     * Make all the users observe changes about movie list
     * **/
    public void addObserversToWatch(final InputAll input) {
        for (int i = 0; i < input.getUsers().size(); ++i) {
            addObserver(input.getUsers().get(i));
        }
    }

    /**
     * method to delete a certain
     * movie being given the title
     * **/
    public void deletedMovie(final InputAll input, final ArrayNode output,
                                    final ActionsData action) {
        Visitor visitor = FilterActionClass.getInstance();
        if (input.getMovies().stream().filter(movie -> action.getDeletedMovie().
                equals(movie.getName())).findFirst().orElse(null) == null) {
            visitor.setOutput("Error", new ArrayList<>(), null, output);
            return;
        }
        for (int i = 0; i < input.getUsers().size(); ++i) {
            input.getUsers().get(i).deleteAndNotify(action.getDeletedMovie());
        }
    }
}
