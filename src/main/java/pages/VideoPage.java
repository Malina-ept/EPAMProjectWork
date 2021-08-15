package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VideoPage {

    @FindBy(xpath = "//div[@class = 'evnt-talks-column cell-6']")
    public WebElement cardOnTheVideoPage;
}
