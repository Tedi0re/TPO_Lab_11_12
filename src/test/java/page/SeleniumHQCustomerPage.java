package page;

import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SeleniumHQCustomerPage extends AbstractPage{

    private final By selectUserButtonLocator = new By.ByXPath("//select[@id='userSelect']");
    private final By logInButtonLocator = new By.ByXPath("//button[text()='Login']");

    public SeleniumHQCustomerPage(WebDriver driver){
        super(driver);
    }
    public SeleniumHQCustomerPage ChoiceUser(User user){
        WebElement selectUserButton = WaitForElementLocatedBy(selectUserButtonLocator);
        selectUserButton.click();

        Select selectUser = new Select(selectUserButton);
        selectUser.selectByVisibleText(user.getUserName());
        return this;
    }
    //Choice User
   public SeleniumHQAccountPage SubmitLogInButton(){
       WebElement logInButton = WaitForElementLocatedBy(logInButtonLocator);
       logInButton.click();
       return new SeleniumHQAccountPage(driver);
   }
    //Login

}
