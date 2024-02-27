package org.example.wireMock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.github.tomakehurst.wiremock.standalone.MappingsLoader;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class WireMockConfig {
    public static WireMockServer server;
    public static void startServer(){
            server = new WireMockServer
                    (options().port(8083)
                    .notifier(new ConsoleNotifier(true)));
            server.start();
    }
    public static void accountBalance()
   {
        server.stubFor(get(urlEqualTo(Constants.GET_ACCOUNT_BALANCE))
                .willReturn(WireMock.aResponse()
                        .withBodyFile("GET_accountBalance.json")
                        .withStatus(200)));

    }
    public static void accountDetails()
    {
        server.stubFor(get(urlEqualTo(Constants.GET_DETAILS))
                .willReturn(WireMock.aResponse()
                        .withBodyFile("GET_accountBalanceAdditionalinformation.json")
                        .withStatus(200)));

    }
    public static void addCustomer(){
        server.stubFor(post(urlEqualTo(Constants.POST_ADDCUSTOMER))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type","application/json")
                        .withStatus(200)
                        .withBodyFile("POST_AddCustomerDetails.json")));
    }

    public static void getAccountNumber(){
        server.stubFor(get(urlEqualTo(Constants.GET_ACCOUNT_NUMBER))
                .willReturn(WireMock.aResponse()
                        .withBodyFile("GET_accountBalance.json")
                        .withStatus(200)));

    }
    public static void getAccountDetails(String accountNumber){
        server.stubFor(get(urlEqualTo(Constants.GET_ACCOUNTDETAILS+"?accountNumber="+accountNumber))
                        .willReturn(WireMock.aResponse()
                        .withBodyFile("GET_accountBalanceAdditionalinformation.json")
                        .withStatus(200)));

    }
    public static void bearerAuth(){
        server.stubFor(get(urlEqualTo(Constants.BEARER_AUTH))
                .withHeader("Authorization",equalTo("Bearer BANK123"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withBody("BASIC Authorization authentication")));
    }

    public static void stopServer(WireMockServer server) {
        server.stop();
    }

}
