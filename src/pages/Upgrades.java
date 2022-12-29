package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import io.ActionsData;
import io.UserData;

public class Upgrades extends HomepageExistentAccount {
    /**
     * Method for calling the right function/method of changing pages
     * DO NOT MODIFY
     * **/
    public MainPage accept(final Visitor visitor, final ActionsData action, final ArrayNode output,
                           final UserData user) {
        return visitor.changePage(this, action, output, user);
    }
}
