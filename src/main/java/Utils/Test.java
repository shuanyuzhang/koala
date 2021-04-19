package Utils;

/**
 * @author zsy
 * @description
 * @date 2021/4/17 13:04
 */
public class Test {

    @org.junit.Test
    public void test(){
        AutoReplace.replaceFromEvn("$${host}/api/login");
        System.out.println(AutoReplace.replaceFromEvn("$${host}/api/login"));

    }
}
