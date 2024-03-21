package step_defs;

import org.openqa.selenium.WebDriver;
import pages.BasePage;
import utils.DriverManager;
import utils.Pages;

public abstract class BaseStep {
    protected final WebDriver DRIVER ;

    protected final Pages PAGES;

    public BaseStep(){
        DRIVER = DriverManager.getDriver();
        PAGES = new Pages();
    }

}
