package HttpApi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JsonMethods {

    public void simpleJson(String json){//处理简单json
        try{
            ObjectMapper mapper = new ObjectMapper();
            Map map = mapper.readValue(json,Map.class);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONObject complexJson(String json){//多维数组，向下兼容
        JSONObject jsonObject = JSON.parseObject(json);
        return jsonObject;
        /**
         * System.out.println("menu："+jsonObject.getJSONObject("data").getString("menu"));//取多维数组的值
         * System.out.println(jsonObject.getJSONObject("data").getJSONArray("menu").
         *                                    getJSONObject(0).getString("name"));//通过下标取多维json里的值
         */
    }
}



/*
 *
 *         JSONObject jsonobj = JSON.parseObject(myJsonObj); //将json字符串转换成jsonObject对象
 *         //获取JSONObject中每个key对应的value值时，可以根据实际场景中想得到什么类型就分别运用不到的方法
 *         System.out.println(jsonobj.get("name")); //取出name对应的value值，得到的是一个object
 *         System.out.println(jsonobj.getIntValue("alexa")); //取出name对应的value值，得到的是一个int
 *         System.out.println(jsonobj.getJSONObject("sites")); //取出sites对应的value值，得到一个JSONObject子对象
 *         System.out.println(jsonobj.getJSONObject("sites").getString("site2")); //取出嵌套的JSONObject子对象中site2对应的value值，必须用getJSONObject()先获取JSONObject
 */