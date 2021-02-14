package ru.appline.framework.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static ru.appline.framework.managers.DriverManager.getDriver;

public class IpotekaPage extends BasePage {

    @FindBy(xpath = "//input[@class=\"dc-input__input-4-9-1\"]")
    private List<WebElement> ipotekaParams;
    @FindBy(xpath = "//div[@class=\"_3rbcis5ZAL0q48G5LAkGwD\"]")
    private WebElement ramka;
    @FindBy(xpath = "//span[@class=\"_1ZfZYgvLm4KBWPL41DOSO\"]")
    List<WebElement> roleButtons;
    private String roleTurnXpath = "./../..//input[@class=\"switch-input-2-4-0\"]";
    private String actualFieldXpath ="./../div";
    @FindBy(xpath = "//div[@class=\"_3Pp08MW5wHBQuRCfhHvrPx\"]")
    WebElement ramka2;
    @FindBy(xpath = "//div[@class=\"_37omFZ9kUbEb7hrAdTbQ99\"]//span[@class=\"_270UmDDpslAzQsVny6n-hx\"]")
    List<WebElement> payData;
    private String dataNumber = "./../span[2]/span";

    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return IpotekaPage - т.е. остаемся на этой странице
     */
    @Step("Проверка открытия страницы '{title}'")
    public IpotekaPage checkOpenPage(String title) {
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                title, getDriver().getTitle());
        return this;
    }
    /**
     * Заполнение полей страницы данными
     *
     * @return IpotekaPage - т.е. остаемся на этой странице
     */
    @Step("Заполнение страницы данными")
    public IpotekaPage insertParams(String totalPrice, String firstPay, String yearsAmount)  {
        getDriver().switchTo().frame(0);
        for (WebElement w : ipotekaParams) {
            switch (w.findElement(By.xpath(actualFieldXpath)).getText()){
                case ("Стоимость недвижимости"):fillInputField(w,ramka,totalPrice);break;
                case ("Первоначальный взнос"): fillInputField(w,ramka,firstPay);break;
                case ("Срок кредита"):fillInputField(w,ramka,yearsAmount);break;
            }
        }
        return this;
    }
    /**
     * Выбираем дополнительные условия кредитования
     * путем нажатия на соответсвующие "ролики"
     *
     * @return IpotekaPage - т.е. остаемся на этой странице
     */
    @Step("Нажимаем на кнопки-ролики")
    public IpotekaPage checkRollButton(String param,boolean status){
        scrollToElementJs(ramka2);
        for(WebElement w : roleButtons)
            if (w.getText().equalsIgnoreCase(param))
                if (w.findElement(By.xpath(roleTurnXpath)).getAttribute("aria-checked").equalsIgnoreCase(Boolean.toString(!status)))
                    w.findElement(By.xpath(roleTurnXpath)).click();
        return this;
    }
    @Step("Проверяем данные расчета ипотеки")
    public IpotekaPage checkPayData(String request, String val){
        scrollToElementJs(ramka);
        for(WebElement w : payData)
            if (w.getText().equalsIgnoreCase(request)) {
                try {
                    wait.until(ExpectedConditions.textToBePresentInElement(w.findElement(By.xpath(dataNumber)), getPriceString(Integer.parseInt(val))));
                }catch (TimeoutException e){

                    Assert.assertTrue("Значение " + w.getText() + " не совпадает!", (val.equals(getNumber(w.findElement(By.xpath(dataNumber))))));
                }
            }
                return this;
    }
}
