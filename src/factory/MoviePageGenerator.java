package factory;

import pages.MainPage;
import pages.Movies;

public class MoviePageGenerator extends BlackBox {

    /**
     * Method to create the desired page and return it
     * **/
    @Override
    public MainPage createPage() {
        return new Movies();
    }
}
