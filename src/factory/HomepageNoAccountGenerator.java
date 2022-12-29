package factory;

import pages.HomepageNoAccount;
import pages.MainPage;

public class HomepageNoAccountGenerator extends BlackBox {
    @Override
    public MainPage createPage() {
        return new HomepageNoAccount();
    }
}
