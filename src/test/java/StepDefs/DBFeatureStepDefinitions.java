package StepDefs;

import CommonUtils.DBUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.sql.SQLException;

public class DBFeatureStepDefinitions {
    DBUtils utils =new DBUtils();
    @Given("User connects to {string}")
    public void userConnectsToDatabase(String DB) throws ClassNotFoundException {
        utils.connection(DB);
    }

    @Then("User inserts value into Table {string}")
    public void userInsertsValueIntoTable(String arg0) throws SQLException {
        utils.insertRecords("CustomerDetails");
    }

    @Then("user validates the inserted records {string}")
    public void userValidatesTheInsertedRecords(String tableName) throws SQLException {
        utils.validateRecords(tableName);
    }

    @Then("User close the connection")
    public void userCloseTheConnection() throws SQLException {
        utils.close();
    }
}
