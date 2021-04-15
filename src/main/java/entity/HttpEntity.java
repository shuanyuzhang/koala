package entity;

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
        String json = "{\"url\":\"baidu1.com\",\"method\":\"POST\",\"params\":[{\"a1\":\"b1\"}]}";
        httpEntity  = JSONObject.parseObject(json,HttpEntity.class);
        System.out.println(httpEntity.getParams());
    }
}
