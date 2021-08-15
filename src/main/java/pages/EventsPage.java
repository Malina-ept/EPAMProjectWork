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

    @FindBy(xpath = "//a[@class = 'evnt-tab-link nav-link']")
    public WebElement pastEventsButton;

    // Любая карточка на странице
    @FindBy(xpath = "//div[@class = 'evnt-events-column cell-3']")
    public WebElement anyСardOnThePage;

    //Информация на карточке:
    @FindBy(xpath = "(//p[@class = 'language'])[1]//span")
    public WebElement languageOnCard;

    @FindBy(xpath = "(//div[@class = 'evnt-event-name'])[1]//span")
    public WebElement eventNameOnCard;

    @FindBy(xpath = "(//div[@class = 'evnt-dates-cell dates'])[1]//span[1]")
    public WebElement dateOnCard;

    @FindBy(xpath = "(//div[@class = 'evnt-dates-cell dates'])[1]//span[2]")
    public WebElement registrationInformationOnCard;

    @FindBy(xpath = "(//div[@class = 'speakers-wrapper'])[1]//img")
    public WebElement speakersOnCard;
}
