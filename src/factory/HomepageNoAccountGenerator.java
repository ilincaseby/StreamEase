package factory;

import pages.HomepageNoAccount;
import pages.MainPage;

public class HomepageNoAccountGenerator extends BlackBox {

    /**
     * Method to create the desired page and return it
     * **/
    @Override
    public MainPage createPage() {
        return new HomepageNoAccount();
    }
}
