package JavaSteps;

import HttpApi.HttpMethods;
import HttpApi.MapList;
import HttpApi.ApiPath;
import Utils.AutoTools;
import cucumber.api.java.en.Then;

public class ManagerSteps extends HttpMethods {
    /*String result = null;
    MapList ml = new MapList();

    @Then("需求创建{string}{string}{string}")
    public void demandSave(String shopid, String shopsn, String labelid) {
        String result = doPost(ApiPath.DEMANDSAVE,ml.demandSaveMap(shopid,shopsn,labelid),getToken());
        System.out.println(result);
    }*/


    @Then("case: (.*) api: (.*) assert: (.*)")
    public void HttpExcute(String stepName, String apiPath,String params) {
        //System.out.println(stepName+" he "+YamlReader.instance.getValueByKey(apiPath)+" he "+params);
        System.out.println(AutoTools.setApiEntity(apiPath).toString().toString());
    }
}
