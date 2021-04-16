package JavaSteps;
import cucumber.api.java.en.*;

import static com.thoughtworks.selenium.SeleneseTestNgHelper.assertEquals;

public class NewOne {
    /*@Given("开始")
    public void ab(){
        System.out.println("224");
    }

    @Then("结束")
    public void a(){
        System.out.println("224");
    }*/

    @Given("开始")
    public void 开始() {

        System.out.println(1234);
        assertEquals("2","2");
        //throw new cucumber.api.PendingException();

    }

    @Then("结束")
    public void 结束() {
        System.out.println(4321);
        assertEquals("1","2");
        //throw new cucumber.api.PendingException();
    }
}
