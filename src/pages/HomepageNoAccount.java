package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import io.ActionsData;
import io.InputAll;
import io.UserData;

public class HomepageNoAccount extends MainPage {
    /**
     * Method for calling the right function/method of changing pages
     * DO NOT MODIFY
     * **/
    public MainPage accept(final Visitor visitor, final ActionsData action, final ArrayNode output,
                           final UserData user, final InputAll input) {
        return visitor.changePage(this, action, output, user);
    }
}
