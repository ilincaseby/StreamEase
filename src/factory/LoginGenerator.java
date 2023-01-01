package factory;

import pages.Login;
import pages.MainPage;

public class LoginGenerator extends BlackBox {

    /**
     * Method to create the desired page and return it
     * **/
    @Override
    public MainPage createPage() {
        return new Login();
    }
}
