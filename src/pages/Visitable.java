package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import io.ActionsData;
import io.InputAll;
import io.UserData;

public interface Visitable {
    /**
     * Method which safely calls the method with a specific page object
     * when the browsing through pages is desired
     * **/
    MainPage accept(Visitor visitor, ActionsData action, ArrayNode output, UserData user,
                    InputAll input);

    /**
     * Method which safely calls the right method of login/register
     * **/
    UserData log(Visitor visitor, ActionsData action, ArrayNode output, UserData user,
                 InputAll input);
}
