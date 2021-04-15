package Utils;

import HttpApi.HttpMethods;
import constant.Constants;
import entity.HttpEntity;

import java.util.Map;

/**
 * @author zsy
 * @description 处理请求前的参数
 * @date 2021/4/5 19:11
 */
public class AutoTools {
    public static void setApiEntity(String apiPath,Map paramsMap){
        try {

            String ss[] = apiPath.split("\\.");//后续优化
            //if(ss.length == 2){ //后续优化
            Map map = (Map)YamlReader.instance.getValueByKey(apiPath,ss[1]+".params");
            String url = YamlReader.instance.getValueByKey(apiPath,ss[1]+".url").toString();
            String header = YamlReader.instance.getValueByKey(apiPath,"demand_create.headers.authorization").toString();
            String method = YamlReader.instance.getValueByKey(apiPath,ss[1]+".method").toString();

            /*System.out.println("url: "+url+"\n"+"header: "+header);
            System.out.println("map: "+map);*/

            HttpEntity httpEntity = new HttpEntity();
            httpEntity.setParams(map);
            httpEntity.setUrl(url);
            httpEntity.setHeaders(header);
            httpEntity.setMethod(method);
            Constants.httpEntity = httpEntity;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
