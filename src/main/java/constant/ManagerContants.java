package constant;


import cucumber.api.java.hu.Ha;
import lombok.Data;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zsy
 * @description
 * @date 2021/4/18 11:48
 */
@Data
public class ManagerContants {


    public static String start_date = getCurrentDate();

    public static String getCurrentDate(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }

    //创建需求接口的实体map
    public static Map createDemandEntityMap  = new HashMap();

    //only test
    public static Map getMap(){
        Map<String,String> map = new HashMap();
        map.put("shop_id","188");
        map.put("shop_id2","189");
        map.put("shop_id3","1899");
        map.put("shop_id4","18999");
        for(String s : map.keySet()){
            System.out.println(s);
            System.out.println(map.get(s));
        }
        return null;
    }

    public static void main(String[] args) {
        getMap();
    }
}
