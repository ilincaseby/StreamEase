package administrator;

import actionmaker.FilterActionClass;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.ActionsData;
import io.InputAll;
import pages.Visitor;

import java.util.ArrayList;

public final class AdminDataBase {
    /**
     * Private constructor for utility
     * classes
     * **/
    private AdminDataBase() { }

    /**
     * method to add a movie
     * **/
    public static void addMovie(InputAll input, ArrayNode output, ActionsData action) {
        Visitor visitor = FilterActionClass.getInstance();
        if (input.getMovies().stream().filter(movie -> action.getAddedMovie().getName().
                equals(movie.getName())).findFirst().orElse(null) != null) {
            visitor.setOutput("Error", new ArrayList<>(), null, output);
            return;
        }
        input.getMovies().add(action.getAddedMovie());
        for (int i = 0; i < input.getUsers().size(); ++i) {
            input.getUsers().get(i).addAndNotify(action.getAddedMovie());
        }
    }

    /**
     * method to delete a certain
     * movie being given the title
     * **/
    public static void deletedMovie(InputAll input, ArrayNode output, ActionsData action) {
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
