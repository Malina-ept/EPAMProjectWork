package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VideoPageCard {

    @FindBy(xpath = "//div[@class = 'evnt-talk-details location evnt-now-past-talk']//span")
    public WebElement adressOnCard;

    @FindBy(xpath = "//div[@class='evnt-talk-details language evnt-now-past-talk']//span")
    public WebElement languageOnCard;

    @FindBy(xpath = "//label[normalize-space()='Testing']")
    public WebElement categoryOnCardTesting;

    @FindBy(xpath = "//label[normalize-space()='QA']")
    public WebElement categoryOnCardQA;

}
