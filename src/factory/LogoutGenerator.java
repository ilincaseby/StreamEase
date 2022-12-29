package factory;

import pages.Logout;
import pages.MainPage;

public class LogoutGenerator extends BlackBox {

    @Override
    public MainPage createPage() {
        return new Logout();
    }
}
