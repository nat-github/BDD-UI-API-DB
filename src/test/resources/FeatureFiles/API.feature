@APITesting
Feature: Banking API Testing

  Background: Start the wiremockServer
    Given Start the Wiremock Server and load the stubs

  @BearerAuth
  Scenario Outline: Signing in with Bearer token
    Given user access the Auth "<endpoint>"
    Then user validates the response "BASIC Authorization authentication"
    Examples:
      |endpoint|
      |/api/bearerAuth|

  @BalanceCheck @Regression
  Scenario: Checking account balance API testing
    Given User access the endpoint "/accountBalance"
    And I have an account with the number "1234567890"
    And Verify the account balance is 1000
    And Verify the currency is "EUR"
    Then Stop the server

  @AccountType @Smoke
  Scenario Outline: Checking account details with various parameters
    Given User access the endpoint "<endpoint>"
    And I have an account with the number "<Acc.Num>"
    And Verify the account balance is <Balance>
    And Verify the accountType is "<AccountType>"
    And Verify the PendingTransaction amount is <PendingTransaction>
    And the available balance is <AvailableBalance>
    Then Stop the server
    Examples:
    |Acc.Num   |Balance|AccountType|PendingTransaction|AvailableBalance|endpoint|
    |1234567890|1000   |Checking   |50                |950             |/accountDetails|
    |1234567890|1000   |Checking   |10                |50              |/accountDetails|


  @AddCustomerDetails
  Scenario Outline: Add new Customer details
    Given User access the endpoint "<endpoint>"
    Then User add below details for account creation
    |firstName|lastName|email|phoneNumber|DOB|language|statusCode|
    |John     |Doe     |johndoe@example.com|+1234567890|1980-01-01|en-US|200|
    Then Stop the server
    Examples:
    |endpoint|
    |/addCustomer|

  @APIOrchestration
  Scenario Outline: API orchestration for fetching account details
    Given User access the endpoint "<endpoint>"
    Then get the account  number from API
    Then Input the account number to fetch the detailed account information
    And Verify the accountType is "<AccountType>"
    And Verify the PendingTransaction amount is <PendingTransaction>
    And the available balance is <AvailableBalance>
    Examples:
      |endpoint|PendingTransaction|AvailableBalance|AccountType|
      |/api/accountDetails|50     |950             |Checking   |


