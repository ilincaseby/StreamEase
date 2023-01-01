package factory;

import pages.MainPage;
import pages.SeeDetails;

public class SeeDetailsGenerator extends BlackBox {

    /**
     * Method to create the desired page and return it
     * **/
    @Override
    public MainPage createPage() {
        return new SeeDetails();
    }
}
