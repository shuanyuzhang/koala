package HttpService;

import HttpApi.HttpMethods;
import constant.Constants;

/**
 * @author zsy
 * @description
 * @date 2021/4/15 18:24
 */
public class HttpExcute {
    public static void excute(){
        if(Constants.httpEntity.getMethod().equals("POST")){
            String ss = HttpMethods.doManagerPost(Constants.httpEntity.getUrl(),Constants.httpEntity.getParams(),Constants.httpEntity.getHeaders());
            System.out.println(ss);
        }
    }
}
