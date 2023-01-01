package factory;

import pages.Logout;
import pages.MainPage;

public class LogoutGenerator extends BlackBox {

    /**
     * Method to create the desired page and return it
     * **/
    @Override
    public MainPage createPage() {
        return new Logout();
    }
}
