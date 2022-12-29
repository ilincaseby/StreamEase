package factory;

import pages.MainPage;
import pages.Upgrades;

public class UpgradesGenerator extends BlackBox {
    @Override
    public MainPage createPage() {
        return new Upgrades();
    }
}
