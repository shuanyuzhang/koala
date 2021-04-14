package JavaSteps;

import HttpApi.HttpMethods;
import Utils.AutoTools;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.Map;

public class ManagerSteps {


    @When("case: (.*) api: (.*) assert: (.*)")
    public void HttpExcute(String stepName, String apiPath, String params, Map map) {
        //System.out.println(stepName+" he "+YamlReader.instance.getValueByKey(apiPath)+" he "+params);
        //Assert.assertEquals("","params");//
        System.out.println(AutoTools.setApiEntity(apiPath).toString().toString());
        /*for(Object s : map.entrySet()){
            System.out.println(s);
        }*/


        System.out.println("params = "+params);
    }
}
