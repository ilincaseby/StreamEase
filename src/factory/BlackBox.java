package factory;

import pages.MainPage;

public abstract class BlackBox {
    /**
     * Main method for creating pages, everything being
     * left in the secondary method
     * **/
    public MainPage orderPage() {
        MainPage newPage = createPage();
        return newPage;
    }

    /**
     * Method that changes form along with different objects
     * **/
    public abstract MainPage createPage();
}
