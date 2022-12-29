package factory;

import pages.MainPage;
import pages.Register;

public class RegisterGenerator extends BlackBox {
    @Override
    public MainPage createPage() {
        return new Register();
    }
}
