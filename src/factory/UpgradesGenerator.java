package factory;

import pages.MainPage;
import pages.Upgrades;

public class UpgradesGenerator extends BlackBox {

    /**
     * Method to create the desired page and return it
     * **/
    @Override
    public MainPage createPage() {
        return new Upgrades();
    }
}
