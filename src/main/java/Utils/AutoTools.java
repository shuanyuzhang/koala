package Utils;

import HttpApi.HttpMethods;
import java.util.Map;

/**
 * @author zsy
 * @description 处理请求前的参数
 * @date 2021/4/5 19:11
 */
public class AutoTools {
    public static String setApiEntity(String apiPath){
        try {
            String ss[] = apiPath.split("\\.");//后续优化
            //if(ss.length == 2){ //后续优化
                Map map = (Map)YamlReader.instance.getValueByKey(apiPath,ss[1]+".params");
                String url = YamlReader.instance.getValueByKey(apiPath,ss[1]+".url").toString();
                String header = null;
                if(!(ss[0].equals("login"))){
                    header = YamlReader.instance.getValueByKey(apiPath,"demand_create.headers.authorization").toString();
                }

            /*System.out.println("url: "+url+"\n"+"header: "+header);
            System.out.println("map: "+map);*/
            return HttpMethods.doPost(url,map,header);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
