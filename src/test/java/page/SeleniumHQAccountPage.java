package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import util.StringHolder;

public class SeleniumHQAccountPage extends AbstractPage {

    private final By withdrawlMenuButtonLocator = new By.ByXPath("//button[@ng-class='btnClass3']");
    private final By withdrawlInputLocator = new By.ByXPath("//input[@placeholder='amount']");
    private final By withdrawButtonLocator = new By.ByXPath("//button[text()='Withdraw']");
    private final By errorOrSuccessesMessageLocator = new By.ByXPath("//span[@class='error ng-binding']");
    private final By selectAccountButtonLocator = new By.ByXPath("//*[@id='accountSelect']");

    public SeleniumHQAccountPage(WebDriver driver){
        super(driver);
    }

    public SeleniumHQAccountPage SubmitWithdrawlMenuButton(){
        WebElement withdrawlMenuButton = WaitForElementLocatedBy(withdrawlMenuButtonLocator);
        withdrawlMenuButton.click();
        return this;
    }

    public SeleniumHQAccountPage TypeAmountWithdraw(String amountWithdraw){
        WebElement withdrawlInput = WaitForElementLocatedBy(withdrawlInputLocator);
        withdrawlInput.sendKeys(amountWithdraw);
        return this;
    }

    public SeleniumHQAccountPage SubmitWithdrawButton(){
        WebElement withdrawButton = WaitForElementLocatedBy(withdrawButtonLocator);
        withdrawButton.click();
        return this;
    }

    public SeleniumHQAccountPage WriteErrorOrSuccessesMessage(StringHolder errorOrSuccessesMessage){
        errorOrSuccessesMessage.setValue( WaitForElementLocatedBy(errorOrSuccessesMessageLocator).getText());
        return this;
    }

    public SeleniumHQAccountPage CheckNewAccountId(StringHolder newAccountIdFromBankManagerPage, StringHolder newAccountId){
    WebElement selectAccountButton = WaitForElementLocatedBy(selectAccountButtonLocator);
    selectAccountButton.click();

    Select selectAccount = new Select(selectAccountButton);
        for (WebElement option:  selectAccount.getOptions()) {
            if(option.getText().equals(newAccountIdFromBankManagerPage.getValue())){
                newAccountId.setValue(option.getText());
                break;
            }
        }
    return this;

    }

    //WithdrawlMenu

    //input amount

    //WithDraw

    //Assert

}
