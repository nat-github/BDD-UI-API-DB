
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
        features = {"src/test/resources/FeatureFiles"},
        glue = {"StepDefs"},
        plugin = { "pretty","html:target/MyReports/report.html" },
        monochrome = true,
        tags="@UITesting"
        //tags="@AccountType"
        //tags="@AccountOverview"
        //tags="@DBTesting"
        //tags="@APIOrchestration"
        //tags="@BearerAuth"
)
@Test
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
