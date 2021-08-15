package pages.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MessageAboutCookies {

    @FindBy(xpath = "//button[@id = 'onetrust-accept-btn-handler']")
    public WebElement acceptButton;
}
