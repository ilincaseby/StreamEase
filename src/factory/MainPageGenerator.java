package factory;

import pages.MainPage;

public class MainPageGenerator extends BlackBox {
    @Override
    public MainPage createPage() {
        return new MainPage();
    }
}
