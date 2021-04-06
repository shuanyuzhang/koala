package HttpApi;

import cucumber.api.java.it.Ma;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MapList {
    Map<String,String> map = new HashMap<>();


    public Map<String,String> loginMap(String host){
        if(host.startsWith("test_")){
            System.out.println(host);
            map.put("username","admin");
            map.put("password","123456");
            return  map;
        }else{
            map.put("username","zhangshuanyu");
            map.put("password","Admin123");
            return  map;
        }

    }

    public Map<String,String> demandSaveMap(String shopid,String shopsn,String labelid){
        String hour = new SimpleDateFormat("HH").format(new Date()); //只取小时
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        /*SimpleDateFormat fmtY  = new SimpleDateFormat("yyyy-MM-dd");
        String hour = fmtH.format(new Date());*/
        if(shopid != null && shopid.equals("549")){
            map.put("location","{\"type\":\"Point\",\"coordinates\":[117.170768,40.254788]}");
            map.put("address","胡熊路");
            map.put("area_id","228");
            map.put("business_id","153");
        }else if(shopid.equals("248")){
            map.put("location","{\"type\":\"Point\",\"coordinates\":[116.472824,39.909513]}");
            map.put("address","建国路93号院万达广场A座1层酸奶屋");
            map.put("area_id","228");
            map.put("business_id","312");
        }else{
            System.out.println("shopid is null");
        }
        map.put("shop_id",shopid);
        map.put("shop_sn",shopsn);
        map.put("label_id",labelid);
        map.put("label_name","服务员");
        map.put("price_b","33");
        map.put("price_c","30");
        map.put("person_num","1");
        map.put("hours","3");
        map.put("city_id","1");
        map.put("industry","1");
        map.put("info","接口创建");
        map.put("start_date",date);
        map.put("start_time",hour);
        map.put("end_time: 3","11");
        map.put("period","1");
        return map;
    }
    
}
