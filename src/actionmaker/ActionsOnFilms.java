package actionmaker;

import com.fasterxml.jackson.databind.node.ArrayNode;
import helper.WatchLikeRateClass;
import io.ActionsData;
import io.UserData;
import pages.Visitor;

public abstract class ActionsOnFilms extends Consuming implements Visitor {
    /**
     * Method to call when the user wants to watch a movie
     * **/
    public void watch(final UserData user, final ActionsData action, final ArrayNode output) {
        WatchLikeRateClass.watchHelper(user, action, output);
    }
    /**
     * Method to call when the user wants to like a movie
     * **/
    public void like(final UserData user, final ActionsData action, final ArrayNode output) {
        WatchLikeRateClass.likeHelper(user, action, output);
    }

    /**
     * Method to call when the user wants to rate a movie
     * **/
    @Override
    public void rate(final UserData user, final ActionsData action, final ArrayNode output) {
        WatchLikeRateClass.rateHelper(user, action, output);
    }
}
