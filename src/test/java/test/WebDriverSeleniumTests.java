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
    public void GetLessMoneyThanAllowedFromBankAccount(){
        StringHolder errorOrSuccessesMessage = new StringHolder();

         new SeleniumHQLoginPage(DRIVER)
                 .SubmitCustomerLoginButton()
                 .ChoiceUser(UserCreator.WithCredentialsFromProperty())
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
                .ChoiceUser(UserCreator.WithCredentialsFromProperty())
                .ChoiceCurrency(AccountCurrency.Dollar)
                .SubmitProcessButton()
                .MemorizeIdAccountFromAlertWindow(newAccountIdFromBankManagerPage)//!
                .SubmitOkAlertButton()
                .SubmitHomeButton()
                .SubmitCustomerLoginButton()
                .ChoiceUser(UserCreator.WithCredentialsFromProperty())
                .SubmitLogInButton()
                .CheckNewAccountId(newAccountIdFromBankManagerPage,newAccountIdFromAccountPage);

        Assert.assertTrue(
                newAccountIdFromAccountPage.getValue() != null && newAccountIdFromBankManagerPage.getValue() != null &&
                        !newAccountIdFromAccountPage.getValue().equals("") && !newAccountIdFromBankManagerPage.getValue().equals("") &&
                        newAccountIdFromAccountPage.getValue().equals(newAccountIdFromBankManagerPage.getValue())
        );
    }

    @Test
    public void CheckSortingUsersByFirstNameField(){
        StringHolder FirstNameByUnsortedList = new StringHolder();
        StringHolder FirstNameBySortedList = new StringHolder();

        new SeleniumHQLoginPage(DRIVER)
                .SubmitBankManagerLoginButton()
                .SubmitCustomersButton()
                .MemorizeFirstNameByList(FirstNameByUnsortedList)
                .SubmitFirstNameButton()
                .SubmitFirstNameButton()
                .MemorizeFirstNameByList(FirstNameBySortedList);

        Assert.assertTrue(
                FirstNameBySortedList.getValue() != null && FirstNameByUnsortedList.getValue() != null &&
                        !FirstNameBySortedList.getValue().equals("") && !FirstNameByUnsortedList.getValue().equals("") &&
                        FirstNameBySortedList.getValue().charAt(0) <=  FirstNameByUnsortedList.getValue().charAt(0)
        );
    }

    @Test
    public void GetMoreMoneyThanAllowedFromBankAccount(){
        StringHolder errorOrSuccessesMessage = new StringHolder();

        new SeleniumHQLoginPage(DRIVER)
                .SubmitCustomerLoginButton()
                .ChoiceUser(UserCreator.WithCredentialsFromProperty())
                .SubmitLogInButton()
                .SubmitWithdrawlMenuButton()
                .TypeAmountWithdraw(TestDataReader.GetAmountWithdraw() + "00000")
                .SubmitWithdrawButton()
                .WriteErrorOrSuccessesMessage(errorOrSuccessesMessage);

        Assert.assertEquals(errorOrSuccessesMessage.getValue(),"Transaction Failed. You can not withdraw amount more than the balance.");
    }

    @Test
    public void CheckingUserSearchByFirstName(){
        StringHolder firstNameByList = new StringHolder();

        new SeleniumHQLoginPage(DRIVER)
                .SubmitBankManagerLoginButton()
                .SubmitCustomersButton()
                .InputSearchLine(UserCreator.WithCredentialsFromProperty().getUserName().split(" ")[0])
                .MemorizeFirstNameByList(firstNameByList);

        Assert.assertTrue(
                firstNameByList.getValue() != null &&
                        !firstNameByList.getValue().equals("") &&
                        firstNameByList.getValue().equals(UserCreator.WithCredentialsFromProperty().getUserName().split(" ")[0])
        );
    }

    @Test void CheckingUserSearchByFullName(){
        StringHolder fullNameByList = new StringHolder();

        new SeleniumHQLoginPage(DRIVER)
                .SubmitBankManagerLoginButton()
                .SubmitCustomersButton()
                .InputSearchLine(UserCreator.WithCredentialsFromProperty().getUserName())
                .MemorizeFullNameByList(fullNameByList);


        Assert.assertFalse(
                fullNameByList.getValue() != null &&
                        !fullNameByList.getValue().equals("") &&
                        fullNameByList.getValue().equals(UserCreator.WithCredentialsFromProperty().getUserName().split(" ")[1])
        );
    }

    @Test
    public void CheckingUserSearchByLastName(){
        StringHolder lastNameByList = new StringHolder();

        new SeleniumHQLoginPage(DRIVER)
                .SubmitBankManagerLoginButton()
                .SubmitCustomersButton()
                .InputSearchLine(UserCreator.WithCredentialsFromProperty().getUserName().split(" ")[1])
                .MemorizeLastNameByList(lastNameByList);

        Assert.assertTrue(
                lastNameByList.getValue() != null &&
                        !lastNameByList.getValue().equals("") &&
                        lastNameByList.getValue().equals(UserCreator.WithCredentialsFromProperty().getUserName().split(" ")[1])
        );
    }

    @Test
    public void CheckingUserSearchByPostCode(){
        StringHolder postCodeByList = new StringHolder();

        new SeleniumHQLoginPage(DRIVER)
                .SubmitBankManagerLoginButton()
                .SubmitCustomersButton()
                .InputSearchLine(UserCreator.WithCredentialsFromProperty().getUserCode())
                .MemorizeLastNameByList(postCodeByList);

        Assert.assertTrue(
                postCodeByList.getValue() != null &&
                        !postCodeByList.getValue().equals("") &&
                        postCodeByList.getValue().equals(UserCreator.WithCredentialsFromProperty().getUserName().split(" ")[1])
        );
    }

    @Test
    public void CheckSortingUsersByLastNameField(){
        StringHolder FirstNameByUnsortedList = new StringHolder();
        StringHolder FirstNameBySortedList = new StringHolder();

        new SeleniumHQLoginPage(DRIVER)
                .SubmitBankManagerLoginButton()
                .SubmitCustomersButton()
                .MemorizeLastNameByList(FirstNameByUnsortedList)
                .SubmitLastNameButton()
                .SubmitLastNameButton()
                .MemorizeLastNameByList(FirstNameBySortedList);

        Assert.assertTrue(
                FirstNameBySortedList.getValue() != null && FirstNameByUnsortedList.getValue() != null &&
                        !FirstNameBySortedList.getValue().equals("") && !FirstNameByUnsortedList.getValue().equals("") &&
                        FirstNameBySortedList.getValue().charAt(0) <=  FirstNameByUnsortedList.getValue().charAt(0)
        );
    }

    @Test
    public void OpenTwoNewBankAccounts(){
        StringHolder newFirstAccountIdFromBankManagerPage = new StringHolder();
        StringHolder newSecondAccountIdFromBankManagerPage = new StringHolder();
        StringHolder newFirstAccountIdFromAccountPage = new StringHolder();
        StringHolder newSecondAccountIdFromAccountPage = new StringHolder();

        new SeleniumHQLoginPage(DRIVER)
                .SubmitBankManagerLoginButton()
                .SubmitOpenAccountButton()
                .ChoiceUser(UserCreator.WithCredentialsFromProperty())
                .ChoiceCurrency(AccountCurrency.Dollar)
                .SubmitProcessButton()
                .MemorizeIdAccountFromAlertWindow(newFirstAccountIdFromBankManagerPage)//!
                .SubmitOkAlertButton()
                .ChoiceUser(UserCreator.WithCredentialsFromProperty())
                .ChoiceCurrency(AccountCurrency.Dollar)
                .SubmitProcessButton()
                .MemorizeIdAccountFromAlertWindow(newSecondAccountIdFromBankManagerPage)//!
                .SubmitOkAlertButton()
                .SubmitHomeButton()
                .SubmitCustomerLoginButton()
                .ChoiceUser(UserCreator.WithCredentialsFromProperty())
                .SubmitLogInButton()
                .CheckNewAccountId(newFirstAccountIdFromBankManagerPage,newFirstAccountIdFromAccountPage)
                .CheckNewAccountId(newSecondAccountIdFromBankManagerPage,newSecondAccountIdFromAccountPage);

        Assert.assertTrue(
                (newFirstAccountIdFromAccountPage.getValue() != null && newFirstAccountIdFromBankManagerPage.getValue() != null &&
                        !newFirstAccountIdFromAccountPage.getValue().equals("") && !newFirstAccountIdFromBankManagerPage.getValue().equals("") &&
                        newFirstAccountIdFromAccountPage.getValue().equals(newFirstAccountIdFromBankManagerPage.getValue()))
                &&
                        ((newSecondAccountIdFromAccountPage.getValue() != null && newSecondAccountIdFromBankManagerPage.getValue() != null &&
                                !newSecondAccountIdFromAccountPage.getValue().equals("") && !newSecondAccountIdFromBankManagerPage.getValue().equals("") &&
                                newSecondAccountIdFromAccountPage.getValue().equals(newSecondAccountIdFromBankManagerPage.getValue())))
        );
    }


}
