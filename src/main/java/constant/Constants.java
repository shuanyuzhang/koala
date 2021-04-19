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
    public static String response;
    public static List<String> responseJsons = new ArrayList<>();

    //自定义的数据
    public static String[] args;

    //待测环境
    public static String env = "src\\main\\resources\\env\\test-manager.properties" ;




}
