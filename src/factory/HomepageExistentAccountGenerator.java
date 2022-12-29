package factory;

import pages.HomepageExistentAccount;
import pages.MainPage;

public class HomepageExistentAccountGenerator extends BlackBox {

    @Override
    public MainPage createPage() {
        return new HomepageExistentAccount();
    }
}
