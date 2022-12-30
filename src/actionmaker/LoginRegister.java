package actionmaker;

import com.fasterxml.jackson.databind.node.ArrayNode;
import io.ActionsData;
import io.InputAll;
import io.UserData;
import pages.Login;
import pages.Register;
import pages.Visitor;

import java.util.ArrayList;

public abstract class LoginRegister extends ChangePageClass implements Visitor {
    /**
     * Method for login feature
     * **/
    public UserData doFeatures(final Login currentPage, final ActionsData action,
                               final ArrayNode output, final InputAll input, final UserData user) {
        if (user != null) {
            setOutput("Error", new ArrayList<>(), null, output);
            return user;
        }
        if (action.getFeature().equals("register")) {
            setOutput("Error", new ArrayList<>(), null, output);
            return null;
        }
        if (action.getCredentials() == null) {
            setOutput("Error", new ArrayList<>(), null, output);
            return null;
        }
        UserData userToRet = InputAll.userByName(input.getUsers(),
                action.getCredentials().getName());
        if (userToRet == null) {
            setOutput("Error", new ArrayList<>(), null, output);
            return null;
        }
        if (!userToRet.getCredentials().getPassword()
                .equals(action.getCredentials().getPassword())) {
            setOutput("Error", new ArrayList<>(), null, output);
            return null;
        }
        setOutput(null, new ArrayList<>(), userToRet, output);
        userToRet.setPage("homepage");
        userToRet.getPageSt().add("homepage");
        return userToRet;
    }

    /**
     * Method for register feature
     * **/
    public UserData doFeatures(final Register currentPage, final ActionsData action,
                               final ArrayNode output, final InputAll input, final UserData user) {
        if (user != null) {
            setOutput("Error", new ArrayList<>(), null, output);
            return null;
        }
        if (action.getCredentials() == null) {
            setOutput("Error", new ArrayList<>(), null, output);
            return null;
        }
        if (InputAll.userByName(input.getUsers(), action.getCredentials().getName()) != null) {
            setOutput("Error", new ArrayList<>(), null, output);
            return null;
        }
        UserData userToRet = new UserData(action.getCredentials());
        input.getUsers().add(userToRet);
        userToRet.setIfPremium();
        userToRet.setMoviesForUser(input.getMovies());
        userToRet.setPage("homepage");
        userToRet.setMoviesAllTime(input.getMovies());
        setOutput(null, new ArrayList<>(), userToRet, output);
        userToRet.getPageSt().add("homepage");
        return userToRet;
    }

}
