@DBTesting
Feature: Database testing

  Scenario: Validation of results in Database
    Given User connects to "Database"
    Then User inserts value into Table "CustomerDetails"
    Then user validates the inserted records "CustomerDetails"
    Then User close the connection

  Scenario Outline: Validation of results in Database
    Given User connects to "<Database>"
    Then User inserts value into Table "<TableName>"
    Then user validates the inserted records "<TableName>"
    Then User close the connection
    Examples:
    |Database|TableName|
    |H2SampleDB|CustomerDetails|

  Scenario Outline: Validation of results in Database
    Given User connects to "<Database>"
    Then User inserts value into Table "<TableName>"
      |AccountNumber|Balance|CustomerName|
      |45673218     |1000.0 |Maarten Knippers|
    Then user validates the inserted records "<TableName>"
      |AccountNumber|Balance|CustomerName|
      |45673218     |1000.0 |Maarten Knippers|
    Then User close the connection
    Examples:
      |Database|TableName|
      |H2SampleDB|CustomerDetails|