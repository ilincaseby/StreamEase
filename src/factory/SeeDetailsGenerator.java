package factory;

import pages.MainPage;
import pages.SeeDetails;

public class SeeDetailsGenerator extends BlackBox {
    @Override
    public MainPage createPage() {
        return new SeeDetails();
    }
}
