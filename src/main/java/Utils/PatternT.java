package Utils;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zsy
 * @description TestPattern
 * @date 2021/4/10 21:41
 */
public class PatternT {
    @Test
    public void testRegx(){
        String regx = "\\$\\$\\{.*?}";
        System.out.println(regx);
        String ss = "$${aks}和$${akb}还有$${akc}";
        Pattern pattern  = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(ss);
        matcher.find();
        System.out.println(matcher.group());


    }
}
