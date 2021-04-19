package Utils;

import constant.Constants;
import constant.ManagerContants;
import entity.HttpEntity;
import org.junit.Test;

import java.util.*;

import static Utils.AutoReplace.*;

/**
 * @author zsy
 * @description 自动处理请求相关数据的类
 * @date 2021/4/5 19:11
 */
public class AutoTools {
    /**
     * @param apiPath:文件存储位置拼接上接口路径
     * @param paramsMap:用例自定义输入的参数
     * 处理请求的实体
     * */
    public static void setApiEntity(String apiPath, Map paramsMap) {
        try {
            String arg[] = apiPath.split("\\.");
            Map map = (Map) YamlReader.instance.getValueByKey(apiPath, arg[1] + ".params");

            //random机制的随机提取map值
            if (arg[1].contains("random")) {
                map = (Map) map.get(random(map));
            }

            //根据需要，赋值公共参数map
            setManagerContantsMap(map,arg[1]);

            //处理用例自定义输入的参数
            for (Object k : paramsMap.keySet()) {
                if (!(k.toString().equals("null"))) {
                    if(paramsMap.get(k.toString()).toString().startsWith("$")){//输入的参数值包含$的正则提取
                        String mapValue = replaceValueFromContants(paramsMap.get(k).toString());
                        //System.out.println("mapValue2222 = " + mapValue);
                        map.put(k, mapValue);
                    }else{ //输入的参数值为直接赋值的
                        map.put(k.toString(), paramsMap.get(k).toString());
                    }
                }
            }

            for(Object o : map.entrySet()){ //每次map的实体
                System.out.println("########### = "+o);
            }

            String url = YamlReader.instance.getValueByKey(apiPath, arg[1] + ".url").toString();
            url = AutoReplace.replaceFromEvn(url);

            //String header = YamlReader.instance.getValueByKey(apiPath, "demand_create.headers.authorization").toString();
            String method = YamlReader.instance.getValueByKey(apiPath, arg[1] + ".method").toString();

            HttpEntity httpEntity = new HttpEntity();
            httpEntity.setParams(map);
            httpEntity.setUrl(url);
            httpEntity.setMethod(method);
            Constants.httpEntity = httpEntity;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param map:随机机制的，存在多个params值，共同存放
     * 根据map的长度得出随机函数，将key装成list，通过随机数取出
     * */
    public static String random(Map map) {
        List<String> list = new ArrayList<String>();
        int i = new Random().nextInt(map.size());
        for (Object k : map.keySet()) {
            list.add(k.toString());//a = a++;//a++、先执行代码，下次再加1
        }
        return list.get(i);
    }

    /**
     * @param map:接口请求的参数集合
     * @param str：当前请求的接口地址
     * 将接口创建的参数，赋值给公共参数，供其他地方使用
     * */
    public static void setManagerContantsMap(Map<String,String> map,String str){
        if(str.contains("demand_create")){
            //后续再考虑任务过长导致跨天的情况，再来修改start、end的time
            map.put("start_time",map.get("start_date")+" 00:00:00");
            map.put("end_time",map.get("start_date")+" 23:59:59");
            ManagerContants.createDemandEntityMap = map;
            System.out.println("赋值公共参数成功");
        }
    }

    public String timeStamp(Date startDate, Date endDate) {
        long startTime = startDate.getTime();//获取毫秒数
        long endTime = endDate.getTime();     //获取毫秒数
        long timeDifference = endTime - startTime;
        long second = timeDifference / 1000;    //计算秒

        if (second < 60) {
            return second + "秒前";
        } else {
            long minute = second / 60;
            if (minute < 60) {
                return minute + "分钟前";
            } else {
                long hour = minute / 60;
                if (hour < 24) {
                    return hour + "时前";
                } else {
                    long day = hour / 24;
                    if (day < 30) {
                        return day + "天前";
                    } else {
                        long month = day / 30;
                        if (month < 12) {
                            return day + "月前";
                        } else {
                            long year = month / 12;
                            return year + "年前";
                        }
                    }

                }
            }
        }

    }

    @Test
    public void main() {
        double d = Math.random();
        Random random = new Random();

        while (true) {
            System.out.println();
        }

    }
}
