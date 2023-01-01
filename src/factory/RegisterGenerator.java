package factory;

import pages.MainPage;
import pages.Register;

public class RegisterGenerator extends BlackBox {

    /**
     * Method to create the desired page and return it
     * **/
    @Override
    public MainPage createPage() {
        return new Register();
    }
}
