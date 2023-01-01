package factory;

import pages.HomepageExistentAccount;
import pages.MainPage;

public class HomepageExistentAccountGenerator extends BlackBox {

    /**
     * Method to create the desired page and return it
     * **/
    @Override
    public MainPage createPage() {
        return new HomepageExistentAccount();
    }
}
