package ru.yandex.Scooter.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WhoIsTheScooterForPage {
    //Заголовок страницы "Для кого самокат"
    private By orderHeader  = By.className("Order_Header__BZXOb");
    //Поле имя
    private By name  = By.xpath(".//input[@placeholder = '* Имя']");
    //Поле Фамимлия
    private By surname  = By.xpath(".//input[@placeholder = '* Фамилия']");
    //Поле адрес
    private By address  = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");
    //Поле станция метро
    private By metroStation  = By.xpath(".//input[@placeholder = '* Станция метро']");
    //Выпадающий список метро
    private By dropDownListMetroStation = By.className("select-search__select");
    //Поле телефон
    private By telephone  = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");
    //Кнопка далее
    private By buttonNext = By.cssSelector(".Button_Middle__1CSJM");

    private final WebDriver driver;

    public WhoIsTheScooterForPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод загрузки страницы "Для кого самокат"
    public WhoIsTheScooterForPage  waitForLoadOrderPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver1 -> (driver.findElement(orderHeader).getText() !=null && !driver.findElement(orderHeader).getText().isEmpty()));

        return this;
    }

    //Метод заполнения поля "Имя"
    public WhoIsTheScooterForPage setName(String newName) {
        driver.findElement(name).sendKeys(newName);

        return this;
    }

    //Метод заполнения поля "Фамилия"
    public WhoIsTheScooterForPage setSurname(String newSurname) {
        driver.findElement(surname).sendKeys(newSurname);

        return this;
    }

    //Метод заполнения поля "Адрес"
    public WhoIsTheScooterForPage setAddress(String newAddress) {
        driver.findElement(address).sendKeys(newAddress);

        return this;
    }

    //Метод выборастанции Метро
    public WhoIsTheScooterForPage setMetroStation(int stateNumber) {
        driver.findElement(metroStation).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(dropDownListMetroStation));

        WebElement element = driver.findElement(By.xpath("//button[@value = '"+stateNumber+"']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

        driver.findElement(By.xpath("//button[@value = '"+stateNumber+"']")).click();

        return this;
    }

    //Метод заполнения поля "Телефон"
    public WhoIsTheScooterForPage setTelephone(String newTelephone) {
        driver.findElement(telephone).sendKeys(newTelephone);

        return this;
    }

    //Метод нажатия на кнопку "Далее"
    public WhoIsTheScooterForPage clickButtonNext() {
        driver.findElement(buttonNext).click();

        return this;
    }

}


