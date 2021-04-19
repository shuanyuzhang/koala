package HttpApi;

import Utils.JsonMethods;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class HttpMethods extends JsonMethods {
    static final String encoding = "UTF-8";
    String host = null;
    JsonMethods jsonMethods = new JsonMethods();
    String token = "";


    public String getHost(){
        try {
            Properties prop = new Properties();
            String propName= "src\\main\\resources\\ApiInfo\\test-manager.properties";
            prop.load(new FileInputStream(propName));
            String host = prop.getProperty("host");
            return host;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public void doGet(){
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet get = new HttpGet("http://118.186.227.122:80");
            CloseableHttpResponse response = httpClient.execute(get);
            System.out.println("getmethod:"+response.getStatusLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String doManagerPost(String url,Map map,String header){
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            if(header != null || !url.contains("/api/login")){
                httpPost.addHeader("authorization","6a4deaae9e6ac5e15cfc1a07374d311b");
            }
            List<NameValuePair> params = new ArrayList<>();
           for(Object o : map.keySet()){
               params.add(new BasicNameValuePair(o.toString(),map.get(o).toString()));
           }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params,"UTF-8");
            httpPost.setEntity(formEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity(),encoding);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }



    public String doPostyuanlaide (String url, Map map,String token){
        url = getHost() + url;
        String result = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String,String> elem = (Map.Entry<String,String>)iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(),String.valueOf(elem.getValue())));
            }
            if(list.size()>0){
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,encoding);
                post.setEntity(entity);
            }
            //post.addHeader("Content-Type","multipart/form-data; boundary=----WebKitFormBoundarygRSB6V7rTHflSR9y");
            post.addHeader("Authorization",token);
            HttpResponse response = httpClient.execute(post);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,encoding);
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }



    public static String doPost2(String url, Map map,String token){
        String result = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String,String> elem = (Map.Entry<String,String>)iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(),String.valueOf(elem.getValue())));
            }
            if(list.size()>0){
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,encoding);
                post.setEntity(entity);
            }
            //post.addHeader("Content-Type","multipart/form-data; boundary=----WebKitFormBoundarygRSB6V7rTHflSR9y");
            post.addHeader("Authorization",token);
            HttpResponse response = httpClient.execute(post);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,encoding);
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    public String doPost(Object url, Object param){
        String result = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost post = new HttpPost(url.toString());
            System.out.println("url.toString() = "+ url.toString());
            //post.addHeader("Authorization",token);
            HttpResponse response = httpClient.execute(post);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,encoding);
                }
            }
            return result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
