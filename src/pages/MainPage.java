package pages;

import actionmaker.FilterActionClass;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.ActionsData;
import io.InputAll;
import io.UserData;

import java.util.ArrayList;

public class MainPage implements Visitable {
    /**
     * Method for calling the right function/method of changing pages
     * DO NOT MODIFY
     * This one returns null because it's a "root" especially
     * made for extension
     * **/
    @Override
    public MainPage accept(final Visitor visitor, final ActionsData action, final ArrayNode output,
                           final UserData user, final InputAll input) {
        return null;
    }

    /**
     * Method for calling the right function/method of login/register
     * DO NOT MODIFY
     * **/
    @Override
    public UserData log(final Visitor visitor, final ActionsData action, final ArrayNode output,
                        final UserData user, final InputAll input) {
        Visitor filterActionClass = FilterActionClass.getInstance();
        filterActionClass.setOutput("Error", new ArrayList<>(), null, output);
        if (action.getFeature().equals("register")) {
            return null;
        }
        return user;
    }
}
