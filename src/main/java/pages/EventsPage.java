package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EventsPage {

    private final WebDriver driver;
    private Logger logger = LogManager.getLogger(EventsPage.class);
    public EventsPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//a[@class = 'evnt-tab-link nav-link active']//span[text()='Upcoming events']")
    public WebElement upcomingEventsActiveButton;

    @FindBy(xpath = "//a[@class = 'evnt-tab-link nav-link active']//span[3]")
    public WebElement countOnTheButtonUpcomingEvents;

//    @FindBy(xpath = "//div[@class = 'evnt-events-column cell-3']")
//    public int countOfCardsOnPage;

    @FindBy(xpath = "//a[@class = 'evnt-tab-link nav-link']")
    public WebElement pastEventsButton;


    }
