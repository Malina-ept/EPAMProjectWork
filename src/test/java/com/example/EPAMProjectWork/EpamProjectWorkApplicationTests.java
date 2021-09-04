package com.example.EPAMProjectWork;


import com.codeborne.selenide.logevents.SelenideLogger;
import com.example.EPAMProjectWork.helpers.Browsers;
import com.example.EPAMProjectWork.helpers.WDFactory;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.EventsPage;
import pages.HomePage;
import pages.VideoPage;
import pages.VideoPageCard;
import pages.elements.Filters;
import pages.elements.MessageAboutCookies;

import javax.swing.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import static com.example.EPAMProjectWork.helpers.WebDriverInit.initDriver;
import static org.junit.Assert.*;

public class EpamProjectWorkApplicationTests {


    private Logger logger = LogManager.getLogger(EpamProjectWorkApplicationTests.class);
    protected WebDriver driver;

    @BeforeClass
    @Step("Set up environment")
    public static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @Before
    public void startUp() {
// Для использования Selenide
//        driver = initDriver();

// Для использования WDFactory:
        driver = WDFactory.createDriver(Browsers.CHROME);

        logger.info("Драйвер поднят");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @After
    public void end() {
        if (driver != null)
            driver.quit();
    }


    @Test
    @Epic("EPAM")
    @Feature("Upcoming Events")
    @Story("View upcoming events from events.epam.com")
    @Description("Comparison of the number of cards on the button and on the page")
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
    @Epic("EPAM")
    @Feature("Past Events")
    @Story("Check past event cards from events.epam.com")
    @Description("Check that the event card is not empty")
    public void checkPastEventCards() throws InterruptedException {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        EventsPage eventsPage = PageFactory.initElements(driver, EventsPage.class);

        logger.info("Тест стaрт");
        homePage.openHomePage();
        homePage.clickOnTheTabEvents();

        logger.info("Нажимаем на кнопку Past Events");
        eventsPage.pastEventsButton.click();
        Thread.sleep(1000);

        logger.info("Посмотрим инфу в одной карточке");
        System.out.println(eventsPage.languageOnCard.getText());
        System.out.println(eventsPage.eventNameOnCard.getText());
        System.out.println(eventsPage.dateOnCard.getText());
        System.out.println(eventsPage.registrationInformationOnCard.getText());
        System.out.println(eventsPage.speakersOnCard.getTagName());

        logger.info("Проверяем что информация о мероприятии не пуста");
        assertNotNull("Язык мероприятия пуст", eventsPage.languageOnCard.getText());
        assertNotNull("В названии мероприятия пусто", eventsPage.eventNameOnCard.getText());
        assertNotNull("Дата мероприятия пуста", eventsPage.dateOnCard.getText());
        assertNotNull("Нет информации о регистрации", eventsPage.registrationInformationOnCard.getText());
        assertNotNull("Нет списка спикеров", eventsPage.speakersOnCard.getTagName());
        logger.info("Поля: Язык, Название, Дата мероприятия, Информация о регистрации, Спикеры не пусты");
    }


    @Test
    @Step("Сheck the dates of upcoming events")
    @Epic("EPAM")
    @Feature("Upcoming Events")
    @Story("Check the dates of upcoming events from events.epam.com")
    @Description("Check that the dates of the events are greater than or equal to the current date")
    public void checkTheDatesOfUpcomingEvents() throws InterruptedException {
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
        eventsPage.anyСardOnThePage.getLocation();
        logger.info("На странице есть карточки событий");
    }

    @Test
    @Step("Viewing past events in Canada")
    @Epic("EPAM")
    @Feature("Past Events")
    @Story("Check past events in Canada from events.epam.com")
    @Description("Checking out past events in Canada")
    public void checkPastEventsInCanada() throws InterruptedException {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        EventsPage eventsPage = PageFactory.initElements(driver, EventsPage.class);
        Filters filters = PageFactory.initElements(driver, Filters.class);

        logger.info("Тест стaрт");
        homePage.openHomePage();
        homePage.clickOnTheTabEvents();

        logger.info("Нажимаем на кнопку Past Events");
        eventsPage.pastEventsButton.click();
        logger.info("Нажимаем на Location в блоке фильтров");
        filters.locationInFilter.click();
        logger.info("И выбираем Canada в выпадающем списке");
        filters.сanadaInFilterOfLocation.click();
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

        System.out.println("Ура");
    }

    @Test
    @Step("Сheck the Filtering of reports by category")
    @Epic("EPAM")
    @Feature("Talks Library (Video)")
    @Story("Check the filtering of reports by category from events.epam.com")
    @Description("Check the filtering and its results")
    public void checkTheFilteringOfReportsByCategory() throws InterruptedException {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        VideoPage videoPage = PageFactory.initElements(driver, VideoPage.class);
        Filters filters = PageFactory.initElements(driver, Filters.class);
        MessageAboutCookies messageAboutCookies = PageFactory.initElements(driver, MessageAboutCookies.class);
        VideoPageCard videoPageCard = PageFactory.initElements(driver, VideoPageCard.class);

        logger.info("Тест стaрт");
        homePage.openHomePage();
        logger.info("Переходим на вкладку Talks Library (Video)");
        homePage.clickOnTheTabVideo();
        logger.info("Нажимаем на More Filters");
        filters.moreFiltersButton.click();
        logger.info("Выбираем на вкладке фильтров: Category – Testing");
        filters.categoryInFilter.click();

        //Крутим скролл вниз
        JScrollPane jScrollPane1 = new findElement(By.xpath("//div[@class='evnt-filter-menu evnt-dropdown-menu dropdown-menu with-arrow show']//div[@class='evnt-filter-menu-scroll']"));
        JScrollBar vsb = jScrollPane1.getVerticalScrollBar();
        vsb.setValue(vsb.getMaximum());
        //Выбираем Testing
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click()", filters.testingInCategoryInFilter);

        logger.info("Выбираем на вкладке фильтров: Location – Belarus");
        filters.locationInFilter.click();
        filters.belarusInFilterOfLocation.click();
        logger.info("Выбираем на вкладке фильтров: Language – English");
        filters.languageInFilter.click();
        filters.englishInLanguageInFilter.click();
        System.out.println("Ура");
        logger.info("Проверим, что на странице отображаются карточки соответствующие правилам выбранных фильтров");
        logger.info("Перейдем в одну из них");

        logger.info("Жмем принять на сообщении про куки, чтобы они пропали, будь они не ладны!");
        messageAboutCookies.acceptButton.click();

        logger.info("А вот теперь, наконец-то, перейдем в одну из них");
        videoPage.cardOnTheVideoPage.click();

        String address = videoPageCard.adressOnCard.getText();
        System.out.println(address);
        String language = videoPageCard.languageOnCard.getText();
        System.out.println(language);
        String category = videoPageCard.categoryOnCardTesting.getText();
        System.out.println(category);

        assertTrue(address.contains("Belarus"));
        assertTrue(language.contains("ENGLISH"));
        assertTrue(category.contains("Testing"));
        System.out.println("Ура, на странице отображаются карточки соответствующие правилам выбранных фильтров");
    }

    @Test
    @Step("Search for reports by keyword")
    @Epic("EPAM")
    @Feature("Talks Library (Video)")
    @Story("Search for reports by keyword from events.epam.com")
    @Description("Check the filtering by keywords")
    public void searchForReportsByKeyword() throws InterruptedException {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        MessageAboutCookies messageAboutCookies = PageFactory.initElements(driver, MessageAboutCookies.class);
        Filters filters = PageFactory.initElements(driver, Filters.class);
        VideoPage videoPage = PageFactory.initElements(driver, VideoPage.class);
        VideoPageCard videoPageCard = PageFactory.initElements(driver, VideoPageCard.class);

        logger.info("Тест стaрт");
        homePage.openHomePage();
        logger.info("Переходим на вкладку Talks Library (Video)");
        homePage.clickOnTheTabVideo();

        logger.info("Кликаем на поиск по ключевому слову в фильтре и вводим ключевое слово QA в поле поиска");
        filters.searchByKeyword.sendKeys("QA");
        logger.info("Проверяем, что на странице отображаются доклады, содержащие в названии ключевое слово поиска");

        logger.info("Жмем принять на сообщении про куки, чтобы они пропали, будь они не ладны!");
        messageAboutCookies.acceptButton.click();

        logger.info("Перейдем в одну из них");
        videoPage.cardOnTheVideoPage.click();

        String category = videoPageCard.categoryOnCardQA.getText();
        System.out.println(category);
        assertTrue(category.contains("QA"));
        System.out.println("Ура, на странице отображаются доклады, содержащие в названии ключевое слово поиска");

    }

    @Test
    @Epic("EPAM")
    @Feature("Upcoming Events")
    @Story("View upcoming events from events.epam.com")
    @Description("Let's check that the dates of the events are greater than the current date")
    public void checkDateUpcomingEvents() throws InterruptedException {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        EventsPage eventsPage = PageFactory.initElements(driver, EventsPage.class);
        Calendar cal = Calendar.getInstance();
        Long currentDate = cal.getTime().getTime();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        logger.info("Тест стaрт");
        homePage.openHomePage();
        homePage.clickOnTheTabEvents();

        logger.info("Возьмем даты из карточки события");
        String dateForCheckUpcoming = eventsPage.dateOnCard.getText();

        logger.info("Удалим в них пробелы");
        String[] parts = dateForCheckUpcoming.split(" ");
        System.out.println(dateForCheckUpcoming);

        String year = parts[5];
        String dayStart = parts[0];
        String dayEnd = parts[3];
        String monthStart = parts[1];
        String monthEnd = parts[4];

        System.out.println(year);
        System.out.println(dayStart);
        System.out.println(dayEnd);
        System.out.println(monthStart);
        System.out.println(monthEnd);


        Calendar start = new GregorianCalendar(Integer.parseInt(year),
                castMonthToIntUpcoming(monthStart), Integer.parseInt(dayStart));
        Long startDate = start.getTime().getTime();

        Calendar end = new GregorianCalendar(Integer.parseInt(year),
                castMonthToIntUpcoming(monthEnd), Integer.parseInt(dayEnd));
        Long endDate = end.getTime().getTime();


        System.out.println(startDate);
        System.out.println(endDate);

        System.out.println(currentDate);

        logger.info("Проверим, что даты проведения мероприятий больше или равны текущей дате");
        System.out.println(currentDate >= endDate);
        assertTrue("Даты проведения мероприятий меньше текущей даты", currentDate <= endDate);
    }

    @org.jetbrains.annotations.Contract(pure = true)
    public static Integer castMonthToIntUpcoming(String month) {
        Integer currentMonth = 0;
        switch (month) {
            case ("Jan"):
                currentMonth = 0;
                break;
            case ("Feb"):
                currentMonth = 1;
                break;
            case ("Mar"):
                currentMonth = 2;
                break;
            case ("Apr"):
                currentMonth = 3;
                break;
            case ("May"):
                currentMonth = 4;
                break;
            case ("Jun"):
                currentMonth = 5;
                break;
            case ("Jul"):
                currentMonth = 6;
                break;
            case ("Aug"):
                currentMonth = 7;
                break;
            case ("Sep"):
                currentMonth = 8;
                break;
            case ("Oct"):
                currentMonth = 9;
                break;
            case ("Nov"):
                currentMonth = 10;
                break;
            case ("Dec"):
                currentMonth = 11;
                break;
        }
        return currentMonth;
    }

    @Test
    @Epic("EPAM")
    @Feature("Past Events")
    @Story("View past events from events.epam.com")
    @Description("Let's check that the dates of the events held are less than the current date")
    public void checkDatePastEvents() throws InterruptedException {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        EventsPage eventsPage = PageFactory.initElements(driver, EventsPage.class);
        Calendar cal = Calendar.getInstance();
        Long currentDate = cal.getTime().getTime();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


        logger.info("Тест стaрт");
        homePage.openHomePage();
        homePage.clickOnTheTabEvents();


        logger.info("Нажимаем на кнопку Past Events");
        eventsPage.pastEventsButton.click();

        logger.info("Ждем загрузки карточек на странице Past Events");
        Thread.sleep(1000);

        logger.info("Возьмем даты из карточки события");
        String dateForCheckPast = eventsPage.dateOnCard.getText();

        logger.info("Удалим в них пробелы");
        String[] parts = dateForCheckPast.split(" ");
        System.out.println(dateForCheckPast);

        String year = parts[5];
        String dayStart = parts[0];
        String dayEnd = parts[3];
        String monthStart = parts[1];
        String monthEnd = parts[4];

        System.out.println(year);
        System.out.println(dayStart);
        System.out.println(dayEnd);
        System.out.println(monthStart);
        System.out.println(monthEnd);


        Calendar start = new GregorianCalendar(Integer.parseInt(year),
                castMonthToIntPast(monthStart), Integer.parseInt(dayStart));
        Long startDate = start.getTime().getTime();

        Calendar end = new GregorianCalendar(Integer.parseInt(year),
                castMonthToIntPast(monthEnd), Integer.parseInt(dayEnd));
        Long endDate = end.getTime().getTime();


        System.out.println(startDate);
        System.out.println(endDate);

        System.out.println(currentDate);

        logger.info("Проверим, что даты проведенных мероприятий меньше текущей даты");
        System.out.println(currentDate >= endDate);
        assertTrue("Даты проведенных мероприятий больше текущей даты", currentDate >= endDate);
    }

    @org.jetbrains.annotations.Contract(pure = true)
    public static Integer castMonthToIntPast(String month) {
        Integer currentMonth = 0;
        switch (month) {
            case ("Jan"):
                currentMonth = 0;
                break;
            case ("Feb"):
                currentMonth = 1;
                break;
            case ("Mar"):
                currentMonth = 2;
                break;
            case ("Apr"):
                currentMonth = 3;
                break;
            case ("May"):
                currentMonth = 4;
                break;
            case ("Jun"):
                currentMonth = 5;
                break;
            case ("Jul"):
                currentMonth = 6;
                break;
            case ("Aug"):
                currentMonth = 7;
                break;
            case ("Sep"):
                currentMonth = 8;
                break;
            case ("Oct"):
                currentMonth = 9;
                break;
            case ("Nov"):
                currentMonth = 10;
                break;
            case ("Dec"):
                currentMonth = 11;
                break;
        }
        return currentMonth;
    }

    private class findElement extends JScrollPane {
        public findElement(By xpath) {
        }
    }
}