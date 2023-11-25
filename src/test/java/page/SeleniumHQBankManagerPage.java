package page;

import util.AccountCurrency;
import model.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import util.StringHolder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeleniumHQBankManagerPage extends  AbstractPage{

    private final By openAccountMenuButtonLocator = new By.ByXPath("/html/body/div/div/div[2]/div/div[1]/button[2]");
    private final By selectUserButtonLocator = new By.ByXPath("//*[@id='userSelect']");
    private final By selectCurrencyLocator = new By.ByXPath("//*[@id='currency']");
    private final By processButtonLocator = new By.ByXPath("//button[text()='Process']");
    private final By homeButtonLocator = new By.ByXPath("/html/body/div/div/div[1]/button[1]");

    public SeleniumHQBankManagerPage(WebDriver driver){
        super(driver);
    }

    public SeleniumHQBankManagerPage SubmitOpenAccountButton(){

        WebElement openAccountMenuButton = WaitForElementLocatedBy(openAccountMenuButtonLocator);
        openAccountMenuButton.click();
        return this;
    }

    public SeleniumHQBankManagerPage ChoiceUser(User user){
        WebElement selectCurrencyButton = WaitForElementLocatedBy(selectUserButtonLocator);
        selectCurrencyButton.click();

        Select selectUser = new Select(selectCurrencyButton);
        selectUser.selectByVisibleText(user.getUserName());
        return this;
    }

    public SeleniumHQBankManagerPage ChoiceCurrency(AccountCurrency currency){
        WebElement selectCurrencyButton = WaitForElementLocatedBy(selectCurrencyLocator);
        selectCurrencyButton.click();

        Select selectUser = new Select(selectCurrencyButton);
        selectUser.selectByIndex(currency.ordinal() + 1);
        return this;
    }

    public SeleniumHQBankManagerPage SubmitProcessButton(){
        WebElement processButton = WaitForElementLocatedBy(processButtonLocator);
        processButton.click();

        return this;
    }

    public SeleniumHQBankManagerPage MemorizeIdAccountFromAlertWindow(StringHolder newAccountId){
        Alert alert = driver.switchTo().alert();
        Pattern pattern = Pattern.compile(":([0-9]+)");
        Matcher matcher = pattern.matcher(alert.getText());
        if (matcher.find()) {
            newAccountId.setValue(matcher.group(1));
        }
        return this;
    }

    public SeleniumHQBankManagerPage SubmitOkAlertButton(){
        Alert alert = driver.switchTo().alert();
        alert.accept();
        return this;
    }

    public SeleniumHQLoginPage SubmitHomeButton(){
        WebElement homeButton = WaitForElementLocatedBy(homeButtonLocator);
        homeButton.click();
        return new SeleniumHQLoginPage(this.driver);
    }




}
