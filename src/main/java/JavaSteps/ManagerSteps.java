package JavaSteps;

import HttpApi.HttpMethods;
import HttpService.HttpExcute;
import Utils.AssertTools;
import Utils.AutoTools;
import constant.Constants;
import cucumber.api.java.en.When;
import entity.HttpEntity;
import org.junit.Assert;

import java.util.Map;

public class ManagerSteps {


    @When("case: (.*) api: (.*) assert: (.*)")
    public void Excute(String stepName, String apiPath, String assertText, Map paramsMap) {

        AutoTools.setApiEntity(apiPath,paramsMap);
        HttpExcute.excute();

        AssertTools.match(assertText);
        //Assert.assertEquals();

        /*for(Object s : paramsMap.entrySet()){
            System.out.println(s);
        }
        System.out.println("params = "+params);*/
    }
}
