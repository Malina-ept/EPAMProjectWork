package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private final WebDriver driver;
    private Logger logger = LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openHomePage() throws InterruptedException {
//        Thread.sleep(1000);
        driver.get("https://events.epam.com/");
        logger.info("Открыта домашняя страница https://events.epam.com/");
        driver.manage().window().maximize();
        logger.info("Открыто окно браузера на полный экран");
            }

    public void clickOnTheTabEvents(){
        driver.findElement(By.xpath("//li[@class =  'nav-item events-icon']")).click();
        logger.info("Открыта вкладка events");


    }

    public void clickOnTheTabVideo(){
        driver.findElement(By.xpath("//a[@class='nav-link'][normalize-space()='Video']")).click();
        logger.info("Открыта вкладка Video");


    }


}
