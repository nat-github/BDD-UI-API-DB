package StepDefs;

import CommonUtils.UIUtils;
import UILocators.*;
import com.microsoft.playwright.Playwright;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class UIFeatureStepDefinitions {
    Playwright playwright = Playwright.create();
    UIUtils utils = new UIUtils();

    @Given("Initiate the browser {string}")
    public void initiateTheBrowser(String browser) {
        utils.setDriver(browser);
    }
    @Given("User successfully login to webpage using username {string} and password {string}")
    public void userSuccessfullyLoginToWebpageUsingUsernameAndPassword(String userName, String passWord) {
        utils.navigate("https://parabank.parasoft.com/parabank/index.htm");
        utils.type(LoginPage._userName,userName);
        utils.type(LoginPage._password,passWord);
        utils.click(LoginPage._login);
    }
    @And("user navigates to Open new account")
    public void userNavigatesToOpenNewAccount() {
        utils.click(HomePage._openNewAccount);
    }

    @Then("user fills out necessary details and open the new account")
    public void userFillsOutNecessaryDetailsAndOpenTheNewAccount() {
        utils.selectOptionByText(OpenNewAccount._accountType,"SAVINGS");
        utils.selectOptionByText(OpenNewAccount._amount,"14565");
        utils.click(OpenNewAccount._openNewAccount);
    }
    @Then("user fills out necessary details and open the new account {string} {string}")
    public void userFillsOutNecessaryDetailsAndOpenTheNewAccount(String accountType,String amount) {
        utils.selectOptionByText(OpenNewAccount._accountType,accountType);
        utils.selectOptionByText(OpenNewAccount._amount,amount);
        utils.click(OpenNewAccount._openNewAccount);
    }

    @And("Verify the new account with creation of new account number")
    public void verifyTheNewAccountWithCreationOfNewAccountNumber() {
        System.out.println(utils.getText(OpenNewAccount._accountNumber));
    }

    @And("Navigate to Account overview and click on account number {string}")
    public void navigateToAccountOverviewAndClickOnAccountNumber(String accountNumber) {
        utils.click(HomePage._accountOverview);
        utils.click(String.format(AccountOverview._account,accountNumber));
    }

    @Then("Verify the {string} and {string}")
    public void verifyTheAnd(String balance, String availableBalance) {
        assert utils.getText(AccountDetails._balance).equals(balance);
        assert utils.getText(AccountDetails._availableBalance).equals(availableBalance);
    }
}
