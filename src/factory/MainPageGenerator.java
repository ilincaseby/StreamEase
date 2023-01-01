package factory;

import pages.MainPage;

public class MainPageGenerator extends BlackBox {

    /**
     * Method to create the desired page and return it
     * **/
    @Override
    public MainPage createPage() {
        return new MainPage();
    }
}
