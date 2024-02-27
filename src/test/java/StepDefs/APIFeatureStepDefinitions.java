package StepDefs;

import CommonUtils.APIUtils;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.example.wireMock.Constants;
import org.example.wireMock.WireMockConfig;

import java.io.IOException;
import java.util.Map;

public class APIFeatureStepDefinitions {
    Playwright playwright = Playwright.create();
    APIRequestContext apiRequestContext;
    static APIResponse response, orchestrateResponse;
    APIUtils utils = new APIUtils();
    static String baseURL,orchesrtatedBaseURL;

    static String accountNumber = null;

    @Given("User access the endpoint {string}")
    public void userAccesstheEndpoint(String url) {
        utils.createPlaywright();
        baseURL = "http://localhost:8083" + url;
        utils.setApiRequestContext(baseURL, null);
    }

    @And("I have an account with the number {string}")
    public void accountBalance(String accountNumber) throws IOException {
        response = utils.getRequest(baseURL);
        System.out.println(response.text());
    }

    @And("Verify the account balance is {int}")
    public void verifyTheAccountBalanceIs(int arg0) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(response.text());
        int _balance = Integer.parseInt(json.get("balance").toString());
        assert _balance == arg0;
    }

    @And("Verify the currency is {string}")
    public void verifyTheCurrencyIs(String arg0) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(response.text());
        String _currency = json.getAsString("currency");
        assert _currency.equals(arg0);
    }

    @And("Verify the accountType is {string}")
    public void verifyTheAccountTypeIs(String arg0) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(response.text());
        String type = json.getAsString("type");
        assert type.equals(arg0);
    }

    @And("Verify the PendingTransaction amount is {int}")
    public void verifyThePendingTransactionAmountIsPendingTransaction(int num) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(response.text());
        int _balance = Integer.parseInt(json.get("pendingTransactions").toString());
        assert _balance == num;
    }

    @And("the available balance is {int}")
    public void theAvailableBalanceIsAvailableBalance(int num) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(response.text());
        int _balance = Integer.parseInt(json.get("availableBalance").toString());
        assert _balance == num;
    }

    @Then("User add below details for account creation")
    public void userAddBelowDetailsForAccountCreation(DataTable dataTable) {
        int statusCode = response.status();
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            JSONObject json = new JSONObject();
            json.put("firstName", row.get("firstName"));
            json.put("lastName", row.get("lastName"));
            json.put("email", row.get("email"));
            json.put("phoneNumber", row.get("phoneNumber"));
            json.put("dateOfBirth", row.get("DOB"));
            json.put("preferredLanguage", row.get("language"));
            json.put("statusCode", row.get("statusCode"));
            //utils.postRequest(baseURL,new RequestOptions().setData(json.toJSONString()));
        }

    }


    @Then("get the account  number from API")
    public void getTheAccountNumberFromAPI() throws ParseException {
        response = utils.getRequest(baseURL);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(response.text());
        accountNumber = json.getAsString("accountNumber");
    }

    @Then("Input the account number to fetch the detailed account information")
    public void inputTheAccountNumberToFetchTheDetailedAccountDetails() throws ParseException {
        WireMockConfig.getAccountDetails(accountNumber);
        orchesrtatedBaseURL = "http://localhost:8083"+Constants.GET_ACCOUNTDETAILS+"?accountNumber="+accountNumber;
        response = utils.getRequest(orchesrtatedBaseURL);
    }

    @Then("user validates the response {string}")
    public void userValidatesTheResponse(String exepctedValue) throws ParseException {
        assert response.text().equals(exepctedValue);
    }

    @Given("user access the Auth {string}")
    public void userAccessTheAuth(String arg0) {
        String authURL = "http://localhost:8083"+arg0;
        utils.createPlaywright();
        utils.setApiRequestContext(authURL, null);
        response = utils.getRequest(authURL,"Bearer BANK123");
    }
}