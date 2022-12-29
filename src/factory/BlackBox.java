package factory;

import pages.MainPage;

public abstract class BlackBox {
    public MainPage orderPage() {
        MainPage newPage = createPage();
        return newPage;
    }

    public abstract MainPage createPage();
}
