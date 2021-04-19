package JavaSteps;

import HttpService.HttpExcute;
import Utils.AssertTools;
import Utils.AutoTools;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.Map;

public class ManagerSteps {


    @When("case: (.*) api: (.*) assert: (.*)")
    public void Excute(String stepName, String apiPath, String assertText, Map paramsMap) throws Exception {
        AutoTools.setApiEntity(apiPath,paramsMap);
        HttpExcute.excute();
        Assert.assertEquals(AssertTools.match(assertText)[0],AssertTools.match(assertText)[1]);
    }
}
