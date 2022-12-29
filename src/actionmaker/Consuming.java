package actionmaker;

import com.fasterxml.jackson.databind.node.ArrayNode;
import helper.BuyClass;
import io.ActionsData;
import io.UserData;
import pages.Visitor;

public abstract class Consuming extends LoginRegister implements Visitor {
    /**
     * Method to call when the user wants to buy tokens
     * **/
    public void buyTokens(final UserData user, final ActionsData action, final ArrayNode output) {
        BuyClass.getTokens(user, action, output);
    }

    /**
     * Method to call when an upgrade to premium account
     * is desired
     * **/
    public void buyPremiumAccount(final UserData user, final ActionsData action,
                                  final ArrayNode output) {
        BuyClass.getPremiumAccount(user, action, output);
    }

    /**
     * Method for purchasing a movie
     * **/
    public void purchase(final UserData user, final ActionsData action, final ArrayNode output) {
        BuyClass.buyMovie(user, action, output);
    }
}
