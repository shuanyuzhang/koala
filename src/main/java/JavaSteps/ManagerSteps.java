package JavaSteps;

import HttpApi.HttpMethods;
import HttpService.HttpExcute;
import Utils.AutoTools;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.Map;

public class ManagerSteps {


    @When("case: (.*) api: (.*) assert: (.*)")
    public void Excute(String stepName, String apiPath, String params, Map paramsMap) {
        //System.out.println(stepName+" he "+YamlReader.instance.getValueByKey(apiPath)+" he "+params);
        //Assert.assertEquals("","params");//
        AutoTools.setApiEntity(apiPath,paramsMap);
        HttpExcute.excute();
        for(Object s : paramsMap.entrySet()){
            System.out.println(s);
        }

        System.out.println("params = "+params);
    }
}
