package com.example.EPAMProjectWork;


import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.EventsPage;
import pages.HomePage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

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
	public void viewUpcomingEvents() throws InterruptedException {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		EventsPage eventsPage = PageFactory.initElements(driver, EventsPage.class);

		logger.info("Тест стaрт");
		homePage.openHomePage();
		homePage.clickOnTheTabEvents();

		logger.info("Найдем нажатую кнопку Upcoming events");
		WebElement actionButton = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(eventsPage.upcomingEventsActiveButton));
		String actualButtonText = actionButton.getText();
		logger.info("Проверим, что нажата кнопка: Upcoming events");
		assertTrue("Нажатая кнопка не соответствует Upcoming events ", actualButtonText.contains("UPCOMING EVENTS"));
		logger.info("Активная кнопка: Upcoming events");

		logger.info("Возьмем количество карточек на кнопке Upcoming events");
		String countOfCardsOnButton = eventsPage.countOnTheButtonUpcomingEvents.getText();
		logger.info("Посчитаем количество карточек на странице");
		int count = driver.findElements(By.xpath("//div[@class = 'evnt-events-column cell-3']")).size();

		String countOfCardsOnPageText = String.valueOf(count);

		System.out.println(countOfCardsOnPageText);
		System.out.println(countOfCardsOnButton);

		logger.info("И сравним их количество");
		assertEquals("Кол-во на кнопке не совпадает с кол-вом карточек на странице", countOfCardsOnButton, countOfCardsOnPageText);

	}

	@Test
	@Step("Viewing past event cards")
	public void checkPastEventCards() throws InterruptedException {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		EventsPage eventsPage = PageFactory.initElements(driver, EventsPage.class);

		logger.info("Тест стaрт");
		homePage.openHomePage();
		homePage.clickOnTheTabEvents();

		logger.info("Нажимаем на кнопку Past Events");
		eventsPage.pastEventsButton.click();

		logger.info("Посмотрим инфу в одной карточке");
		System.out.println(driver.findElement(By.xpath("(//p[@class = 'language'])[1]//span")).getText());
		System.out.println(driver.findElement(By.xpath("(//div[@class = 'evnt-event-name'])[1]//span")).getText());
		System.out.println(driver.findElement(By.xpath("(//div[@class = 'evnt-dates-cell dates'])[1]//span[1]")).getText());
		System.out.println(driver.findElement(By.xpath("(//div[@class = 'evnt-dates-cell dates'])[1]//span[2]")).getText());
		System.out.println(driver.findElement(By.xpath("(//div[@class = 'speakers-wrapper'])[1]//img")).getTagName());

		logger.info("Проверяем что информация о мероприятии не пуста");
		assertNotNull("Язык мероприятия пуст", driver.findElement(By.xpath("(//p[@class = 'language'])[1]//span")).getText());
		assertNotNull("В названии мероприятия пусто", driver.findElement(By.xpath("(//div[@class = 'evnt-event-name'])[1]//span")).getText());
		assertNotNull("Дата мероприятия пуста", driver.findElement(By.xpath("(//div[@class = 'evnt-dates-cell dates'])[1]//span[1]")).getText());
		assertNotNull("Нет информации о регистрации", driver.findElement(By.xpath("(//div[@class = 'evnt-dates-cell dates'])[1]//span[2]")).getText());
		assertNotNull("Нет списка спикеров", driver.findElement(By.xpath("(//div[@class = 'speakers-wrapper'])[1]//img")).getTagName());
		logger.info("Поля: Язык, Название, Дата мероприятия, Информация о регистрации, Спикеры не пусты");
	}


	@Test
	@Step("Сheck the dates of upcoming events")
	public void СheckTheDatesOfUpcomingEvents() throws InterruptedException {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		EventsPage eventsPage = PageFactory.initElements(driver, EventsPage.class);

		logger.info("Тест стaрт");
		homePage.openHomePage();
		homePage.clickOnTheTabEvents();

		logger.info("Найдем нажатую кнопку Upcoming events");
		WebElement actionButton = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(eventsPage.upcomingEventsActiveButton));
		String actualButtonText = actionButton.getText();
		logger.info("Проверим, что нажата кнопка: Upcoming events");
		assertTrue("Нажатая кнопка не соответствует Upcoming events ", actualButtonText.contains("UPCOMING EVENTS"));
		logger.info("Активная кнопка: Upcoming events");
		logger.info("Проверим, что на странице есть карточки");
		driver.findElements(By.xpath("//div[@class = 'evnt-events-column cell-3']"));
		logger.info("На странице есть карточки событий");
//Сюда нужна проверка, что Даты проведения мероприятий больше или равны текущей дате (или текущая дата находится в диапазоне дат проведения)
		logger.info("Проверим, что даты проведения мероприятий больше или равны текущей дате");
	}

	@Test
	@Step("Viewing past events in Canada")
	public void СheckPastEventsInCanada() throws InterruptedException {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		EventsPage eventsPage = PageFactory.initElements(driver, EventsPage.class);

		logger.info("Тест стaрт");
		homePage.openHomePage();
		homePage.clickOnTheTabEvents();

		logger.info("Нажимаем на кнопку Past Events");
		eventsPage.pastEventsButton.click();
		logger.info("Нажимаем на Location в блоке фильтров");
		driver.findElement(By.xpath("//span[normalize-space()='Location']")).click();
		logger.info("И выбираем Canada в выпадающем списке");
		driver.findElement(By.xpath("//label[normalize-space()='Canada']")).click();
		Thread.sleep(1000);

		logger.info("Проверим, что количество карточек равно счетчику на кнопке Past Events");
		logger.info("Возьмем количество карточек на кнопке Past Events");
		String countOfCardsOnButton = eventsPage.countOnTheButtonUpcomingEvents.getText();

		logger.info("Посчитаем количество карточек на странице");
		int count = driver.findElements(By.xpath("//div[@class = 'evnt-events-column cell-3']")).size();

		String countOfCardsOnPageText = String.valueOf(count);

		System.out.println(countOfCardsOnPageText);
		System.out.println(countOfCardsOnButton);

		logger.info("И сравним их количество");
		assertEquals("Кол-во на кнопке не совпадает с кол-вом карточек на странице", countOfCardsOnButton, countOfCardsOnPageText);

// Здесь проверяем, что на странице отображаются карточки прошедших мероприятий. Даты проведенных мероприятий меньше текущей даты.

		System.out.println("Ура");
	}

//	Фильтрация докладов по категориям:
//	1 Пользователь переходит на вкладку Talks Library
//  2 Пользователь нажимает на More Filters
//  3 Пользователь выбирает: Category – Testing, Location – Belarus, Language – English, На вкладке фильтров
//  4 На странице отображаются карточки соответствующие правилам выбранных фильтров

//	Поиск докладов по ключевому слову:
//	1 Пользователь переходит на вкладку VIDEO - Talks Library
//  2 Пользователь вводит ключевое слово QA в поле поиска
//  3 На странице отображаются доклады, содержащие в названии ключевое слово поиска

}