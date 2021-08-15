package pages.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Filters {

    @FindBy(xpath = "//input[@placeholder='Search by Talk Name']")
    public WebElement searchByKeyword;

    @FindBy(xpath = "//span[normalize-space()='More Filters']")
    public WebElement moreFiltersButton;

    //Фильтры и их значения

    @FindBy(xpath = "//span[normalize-space()='Location']")
    public WebElement locationInFilter;

    @FindBy(xpath = "//label[normalize-space()='Canada']")
    public WebElement сanadaInFilterOfLocation;

    @FindBy(xpath = "//label[normalize-space()='Belarus']")
    public WebElement belarusInFilterOfLocation;

    @FindBy(xpath = "//div[@id='filter_category']")
    public WebElement categoryInFilter;


//    @FindBy(xpath = "//div[@class='evnt-filter-menu evnt-dropdown-menu dropdown-menu with-arrow show']//div[@class='evnt-filter-menu-scroll']")
//    public WebElement scrollBarInCategoryInFilter;

    @FindBy(xpath = "//label[normalize-space()='Testing']")
    public WebElement testingInCategoryInFilter;

    @FindBy(xpath = "//div[@id='filter_language']")
    public WebElement languageInFilter;

    @FindBy(xpath = "//label[normalize-space()='ENGLISH']")
    public WebElement englishInLanguageInFilter;


}
