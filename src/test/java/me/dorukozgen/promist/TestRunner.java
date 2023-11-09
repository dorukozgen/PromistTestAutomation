package me.dorukozgen.promist;

import io.cucumber.java.*;
import io.cucumber.junit.platform.engine.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import me.dorukozgen.promist.managers.DriverManagement;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.ConfigurationParameters;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.Suite;

import java.nio.file.Paths;

@Suite
@IncludeEngines("cucumber")
@ConfigurationParameters({
        @ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty,html:target/cucumber-reports/DesktopTestReport.html"),
        @ConfigurationParameter(
                key = Constants.GLUE_PROPERTY_NAME,
                value = "me.dorukozgen.promist.stepDefinitions,me.dorukozgen.promist"

        ),
        @ConfigurationParameter(key = Constants.FEATURES_PROPERTY_NAME, value = "classpath:features")
})
public class TestRunner {

        @Before
        public void before(Scenario scenario) {
                if (DriverManagement.getInstance().getDriver() == null) {
                        // System.setProperty(
                        //        "webdriver.chrome.driver",
                        //         Paths.get("").resolve("src/test/resources/chromedriver.exe").toAbsolutePath().toString()
                        // );

                        WebDriverManager.chromedriver().setup();

                        DriverManagement.getInstance().setDriver();
                        DriverManagement.getInstance().setWait();
                }
        }

        @After
        public void after(Scenario scenario) {
                DriverManagement.getInstance().destroy();
        }

}
