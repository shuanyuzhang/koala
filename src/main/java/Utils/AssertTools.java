package Utils;

import constant.Constants;
import org.junit.Assert;

public class AssertTools {

    public static String[] match(String assertText) {

        if ((assertText.contains("==") && !(assertText.contains("&&")))) {
            String arg[] = assertText.split("==");
            String code = AutoReplace.replaceFromJsonPath(Constants.response, arg[0]);
            String[] args = {code.trim(), arg[1].trim()};
            return args;

        } else if (assertText.contains("&&")) {
            return null;
        }
        return null;
    }


    public static boolean assertEquals(Object expect,Object actual) throws Exception {
        try {
            Assert.assertEquals(expect,actual);
            return true;
        } catch (Error e) {
            e.printStackTrace();
            throw new Exception("case执行失败");
            //return false;
        }
    }

}
