package StepDefs;

import CommonUtils.APIUtils;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.apache.hc.client5.http.impl.Wire;
import org.example.wireMock.WireMockConfig;
import org.testng.annotations.AfterTest;

public class GeneralStepDefinitions {
    APIUtils utils = new APIUtils();
    @Given("Start the Wiremock Server and load the stubs")
    public void startServer(){
        WireMockConfig.startServer();
        WireMockConfig.accountBalance();
        WireMockConfig.accountDetails();
        WireMockConfig.addCustomer();
        WireMockConfig.getAccountNumber();
        WireMockConfig.bearerAuth();
    }
    @And("Stop the server")
    public static void stopServer(){
        WireMockConfig.stopServer(WireMockConfig.server);
    }
    @AfterTest
    public void teardown(){
        utils.closePlaywright();
        utils.disposeAPIRequestContext();
    }
}
