@UITesting
Feature: Parabank testing

  Background: Initiate the browser
    Given Initiate the browser "Chrome"

  @Inputs-Inbuilt
  Scenario:Verification of Opening new account
    Given User successfully login to webpage using username "paratest" and password "Test@123"
    And user navigates to Open new account
    Then user fills out necessary details and open the new account

  @UIParametrization
  Scenario Outline:Verification of Opening new account
    Given User successfully login to webpage using username "paratest" and password "Test@123"
    And user navigates to Open new account
    Then user fills out necessary details and open the new account "<AccountType>" "<Amount>"
    And Verify the new account with creation of new account number
    Examples:
    |AccountType|Amount|
    |SAVINGS    |14565 |

  @AccountOverview
  Scenario Outline:Verification of Opening new account
    Given User successfully login to webpage using username "<userName>" and password "<password>"
    And user navigates to Open new account
    Then user fills out necessary details and open the new account "<AccountType>" "<Amount>"
    And Verify the new account with creation of new account number
    And Navigate to Account overview and click on account number "<AccountNumber>"
    Then Verify the "<Balance>" and "<AvailableBalance>"
    Examples:
      |AccountType|Amount|AccountNumber|Balance|AvailableBalance|userName|password|
      |SAVINGS    |14565 |14565        |315.50 |315.50          |paratest|Test@123|




