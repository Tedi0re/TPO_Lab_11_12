package test;

import service.TestDataReader;
import service.UserCreator;
import util.AccountCurrency;
import driver.DriverSingleton;
import model.User;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.SeleniumHQLoginPage;
import util.StringHolder;
import util.TestListener;

@Listeners({TestListener.class})
public class WebDriverSeleniumTests {
    private static WebDriver DRIVER;
    private static TestDataReader testDataReader = new TestDataReader();

    @BeforeMethod(alwaysRun = true)
    public void browserSetup(){
        DRIVER = DriverSingleton.getDriver();
        DRIVER.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown(){
        DriverSingleton.closeDriver();
    }

    @Test
    public void getLessMoneyThanAllowedFromBankAccount(){
        StringHolder errorOrSuccessesMessage = new StringHolder();

         new SeleniumHQLoginPage(DRIVER)
                 .SubmitCustomerLoginButton()
                 .ChoiceUser(UserCreator.withCredentialsFromProperty())
                 .SubmitLogInButton()
                 .SubmitWithdrawlMenuButton()
                 .TypeAmountWithdraw(TestDataReader.GetAmountWithdraw())
                 .SubmitWithdrawButton()
                 .WriteErrorOrSuccessesMessage(errorOrSuccessesMessage);

        Assert.assertEquals(errorOrSuccessesMessage.getValue(),"Transaction successful");
    }

    @Test
    public void OpenNewBankAccount(){
        StringHolder newAccountIdFromBankManagerPage = new StringHolder();
        StringHolder newAccountIdFromAccountPage = new StringHolder();

        new SeleniumHQLoginPage(DRIVER)
                .SubmitBankManagerLoginButton()
                .SubmitOpenAccountButton()
                .ChoiceUser(UserCreator.withCredentialsFromProperty())
                .ChoiceCurrency(AccountCurrency.Dollar)
                .SubmitProcessButton()
                .MemorizeIdAccountFromAlertWindow(newAccountIdFromBankManagerPage)//!
                .SubmitOkAlertButton()
                .SubmitHomeButton()
                .SubmitCustomerLoginButton()
                .ChoiceUser(UserCreator.withCredentialsFromProperty())
                .SubmitLogInButton()
                .CheckNewAccountId(newAccountIdFromBankManagerPage,newAccountIdFromAccountPage);

        Assert.assertTrue(
                newAccountIdFromAccountPage.getValue() != null && newAccountIdFromBankManagerPage.getValue() != null &&
                        !newAccountIdFromAccountPage.getValue().equals("") && !newAccountIdFromBankManagerPage.getValue().equals("") &&
                        newAccountIdFromAccountPage.getValue().equals(newAccountIdFromBankManagerPage.getValue())
        );

    }

}
