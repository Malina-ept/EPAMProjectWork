package com.example.EPAMProjectWork;


import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EpamProjectWorkApplicationTests {

	private Logger logger = LogManager.getLogger(EpamProjectWorkApplicationTests.class);
	protected static WebDriver driver;


	@Before
	public void StartUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		logger.info("Драйвер поднят");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@After
	public void End() {
		if (driver != null)
			driver.quit();
	}

	@Test
	@Step("View Upcoming Events")
	public void viewUpcomingEvents() {
		logger.info("Тест стaрт");
		driver.get("https://events.epam.com/");
		logger.info("Сайт открыт");
		driver.manage().window().maximize();
		logger.info("Открыто окно браузера на полный экран");

		logger.info("Переходим на вкладку events");
		driver.findElement(By.xpath("//li[@class =  'nav-item events-icon']")).click();
		logger.info("Открыта вкладка events");

		logger.info("Найдем нажатую кнопку Upcoming events");
		WebElement actionButton = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class = 'evnt-tab-link nav-link active']//span[text()='Upcoming events']")));
		String actualButtonText = driver.findElement(By.xpath("//a[@class = 'evnt-tab-link nav-link active']//span[text()='Upcoming events']")).getText();
		assertTrue("Нажатая кнопка не соответствует Upcoming events ", actualButtonText.contains("UPCOMING EVENTS"));
		logger.info("Активная кнопка: Upcoming events");

		logger.info("Возьмем количество карточек на кнопке Upcoming events");
		String countOfCardsOnButton = driver.findElement(By.xpath("//a[@class = 'evnt-tab-link nav-link active']//span[3]")).getText();
		logger.info("Возьмем количество карточек на странице");
		int count = driver.findElements(By.xpath("//div[@class = 'evnt-events-column cell-3']")).size();

		String countOfCardsOnPage1 = String.valueOf(count);

		System.out.println(countOfCardsOnPage1);
		System.out.println(countOfCardsOnButton);

		logger.info("и сравним их количество");
		assertEquals("Кол-во на кнопке не совпадает с кол-вом карточек на странице", countOfCardsOnButton, countOfCardsOnPage1);


	}
}
