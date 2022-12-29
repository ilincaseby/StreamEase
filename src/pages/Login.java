package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import io.ActionsData;
import io.InputAll;
import io.UserData;

public class Login extends HomepageNoAccount {
    /**
     * Method for calling the right function/method of login/register
     * DO NOT MODIFY
     * **/
    public UserData log(final Visitor visitor, final ActionsData action, final ArrayNode output,
                        final UserData user, final InputAll input) {
        return visitor.doFeatures(this, action, output, input, user);
    }

    /**
     * Method for calling the right function/method of changing pages
     * DO NOT MODIFY
     * **/
    public MainPage accept(final Visitor visitor, final ActionsData action, final ArrayNode output,
                           final UserData user, final InputAll input) {
        return visitor.changePage(this, action, output, user);
    }
}
