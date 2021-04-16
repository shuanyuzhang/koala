package constant;

import HttpService.HttpExcute;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import entity.HttpEntity;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    //请求实体相关
    public static HttpEntity httpEntity;

    //响应
    public static String respone;
    public static List<String> responseJsons = new ArrayList<>();

    //环境
    public static final String testManager = "https://test-manager.zhongfuyun.com";
    public static final String preManager = "https://pre-manager.zhongfuyun.com";
    public static final String Manager = "https://manager.zhongfuyun.com";
}
