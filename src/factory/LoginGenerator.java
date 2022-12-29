package factory;

import pages.Login;
import pages.MainPage;

public class LoginGenerator extends BlackBox {
    @Override
    public MainPage createPage() {
        return new Login();
    }
}
