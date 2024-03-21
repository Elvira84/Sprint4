package ru.yandex.Scooter.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.cssSelector;

public class HomePageScooter {

    //Кнопка Куки
    private By cookie = By.id("rcc-confirm-button");
    //Заголовок главной страницы
    private By homeHeader = className("Home_Header__iJKdX");
    //Кнопка заказа сверху
    private By orderButtonUp = className("Button_Button__ra12g");
    //Кнопка заказа внизу
    private By orderButtonDown = cssSelector(".Button_Middle__1CSJM");
    //раздел «Вопросы о важном»
    private By importantQuestionsSection = className("accordion");
    //список выпадающих ответов в разделу «Вопросы о важном»
    private final String faqQuestions = "accordion__heading-";
    private final String faqResponses = "accordion__panel-";
    private By getFaqQuestions(int number) {return By.id(faqQuestions + number);};
    private By getFaqResponses(int number) {return By.id(faqResponses + number);};


    private WebDriver driver;

    public HomePageScooter(WebDriver driver){
        this.driver = driver;
    }

    //метод ожидания загрузки главной страницы
    public HomePageScooter waitForLoadHeader(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(homeHeader));

        return this;
    }

    //Метода нажатия на кнопку "Куки"
    public HomePageScooter clickCookie () {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(cookie));
        driver.findElement(cookie).click();

        return this;
    }

    // метод нажатие на указанную кнопку "Заказать"
    public HomePageScooter clickOrderButton (String orderButton){
        switch (orderButton) {
            case "Up":
                driver.findElement(orderButtonUp).click();
                break;
            case "Down":
                WebElement element = driver.findElement(orderButtonDown);
                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
                driver.findElement(orderButtonDown).click();
                break;
        }
        return new HomePageScooter(driver);
    }

    //метод для прокрутки страницы  к разделу «Вопросы о важном»
    public HomePageScooter  scrollOnTheQuestion () {
        WebElement element = driver.findElement(importantQuestionsSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }
    //Метод нажатия на вопрос
    public HomePageScooter clickOnTheQuestion(int number) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(getFaqQuestions(number)));
        driver.findElement(getFaqQuestions(number)).click();

        return this;
    }

    //метод получения текста ответа
    public String gettingTheResponseText(int number) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(getFaqResponses(number)));
        return driver.findElement(getFaqResponses(number)).getText();
    }

}
