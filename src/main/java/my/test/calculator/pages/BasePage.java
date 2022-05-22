package my.test.calculator.pages;

import my.test.calculator.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    @FindBy(xpath = "//h1[contains(text(),'The greatest')]")
    private WebElement pageTitle;

    @FindBy(xpath = "//button[@id='getFactorial']")
    private WebElement calculateButton;

    @FindBy(xpath = "//input[@id='number']")
    private WebElement inputField;

    @FindBy(xpath = "//p[@id='resultDiv']")
    private WebElement outputField;

    @FindBy(css = "[href='/privacy']")
    private WebElement termsLink;

    @FindBy(css = "[href='/terms']")
    private WebElement privacyLink;


    private WebDriver driver;
    private static final String BASE_URL = "http://qainterview.pythonanywhere.com";

    public BasePage() {
        this.driver = DriverSingleton.getInstance().getDriver();
        PageFactory.initElements(driver, this);
        driver.get(BASE_URL);
    }

    public boolean isCalculatorPageOpened() {
        return new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(pageTitle)).isDisplayed();
    }

    public int getIntResult() {
        calculateButton.click();
        String[] fullValue = outputField.getText().split(":");
        int numberValue = Integer.parseInt(fullValue[1]);
        return numberValue;
    }

    public String getStringResult() {
        calculateButton.click();
        String[] fullValue = outputField.getText().split(":");
        String strValue = fullValue[1];
        return strValue;
    }

    public void calculateNumbersFactorial(int valueNumber) {
        inputField.sendKeys(""+valueNumber);
        calculateButton.click();
    }

    public boolean calculateWordsFactorial(String value) {
        inputField.sendKeys(value);
        calculateButton.click();
        return inputField.getText().equals("Please enter an integer");
    }
}
