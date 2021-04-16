package entity;

import HttpService.HttpExcute;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import lombok.Data;

import java.util.Map;

@Data
public class HttpEntity {
    //请求
    String url;
    String method;
    String headers;
    Map params;

    //响应
    int code;
    String response;
    String cookies;//token

    public static void main(String[] args) {
        HttpEntity httpEntity = new HttpEntity();
//        String json = "{\"url\":\"baidu1.com\",\"method\":\"POST\",\"params\":[{\"a1\":\"b1\"}]}";
//        httpEntity  = JSONObject.parseObject(json,HttpEntity.class);
        //System.out.println(httpEntity.getParams());

        String code = null;
        String ss = "$.code==200";
        ss.trim().replace(" ","");

        code = ss.substring(6, ss.length());
        System.out.println(code);
    }
}
