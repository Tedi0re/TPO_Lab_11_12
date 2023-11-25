package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumHQLoginPage extends AbstractPage {
    //Customer Login
    private final By customerLoginButtonLocator = new By.ByXPath("//button[text()='Customer Login']");
    private final By bankManagerLoginButtonLocator = new By.ByXPath("//button[text()='Bank Manager Login']");
    public SeleniumHQLoginPage(WebDriver driver){
        super(driver);
    }

    public SeleniumHQCustomerPage SubmitCustomerLoginButton(){
        WebElement customerLoginButton = WaitForElementLocatedBy(customerLoginButtonLocator);
        customerLoginButton.click();
        return new SeleniumHQCustomerPage(driver);
    }

    public SeleniumHQBankManagerPage SubmitBankManagerLoginButton(){
        WebElement bankManagerLoginButton = WaitForElementLocatedBy(bankManagerLoginButtonLocator);
        bankManagerLoginButton.click();
        return new SeleniumHQBankManagerPage(this.driver);
    }

}
