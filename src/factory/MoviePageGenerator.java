package factory;

import pages.MainPage;
import pages.Movies;

public class MoviePageGenerator extends BlackBox {

    @Override
    public MainPage createPage() {
        return new Movies();
    }
}
