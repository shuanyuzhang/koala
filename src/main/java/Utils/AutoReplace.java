package Utils;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import constant.Constants;
import constant.ManagerContants;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zsy
 * @description
 * @date 2021/4/16 15:07
 */
public class AutoReplace {

    public static String replaceFromJsonPath(String json, String jsonPath) {
        DocumentContext dc = JsonPath.parse(json);
        JsonPath jsp = JsonPath.compile(jsonPath);
        return dc.read(jsonPath).toString();
    }

    public static String replaceFromEvn(String context) {
        /**
         * 替换$${key}从evn配置文件或者环境变量中，优先从环境变量中获取
         * content：需要替换的内容
         */
        String regex = "\\$\\$\\{.*?}";
        List<String> list = getJsonPattern(context, regex);
        for (String s : list) {
            String key = getSubstring(s, regex);
            String value = getEnvValue(key);
            if (key != null && value != null) {
                context = context.replace(s, value);
            }
        }
        return context;
    }

    public static String getEnvValue(String key) {
        /**
         * 传入key
         * */
        try {
            Properties properties = new Properties();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.env));
            properties.load(bufferedReader);
            return properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getValue(String key) {
        if (key == null || key.equals(" ")) {
            return null;
        }
        //${}的格式优先从map取
        Map map = ManagerContants.createDemandEntityMap;
        for (Object o : map.keySet()) {
            if (o.equals(key)) {
                return map.get(key).toString();
            }
        }

        //从map未取到值，再去Response取值
        try {
            ObjectMapper jackson = new ObjectMapper();
            JsonNode jsonNode = jackson.readTree(Constants.response);
            if (jsonNode.findValue(key) != null) {
                return jsonNode.findValue(key).toString().replace("\"","");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static String replaceValueFromContants(String context) {
        /**
         * 替换$${key}从evn配置文件或者环境变量中，优先从环境变量中获取
         * content：需要替换的内容
         */
        System.out.println("context = " + context);
        String regex = "\\$\\{.*?}";
        List<String> list = getJsonPattern(context, regex);
        for (String s : list) {
            String key = getSubstring(s, regex);
            String value = getValue(key);
            if (key != null && value != null) {
                context = context.replace(s, value);
            }
        }
        return context;
    }

    public static List<String> getJsonPattern(String json, String regex) {
        List<String> list = new LinkedList<>();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(json);
        while (m.find()) {
            list.add(m.group());
        }
        return list;
    }


    public static String getSubstring(String input, String regx) {
        if (regx.equals("\\$\\{.*?}")) {
            return input.trim().substring(2, input.length() - 1);
        } else if (regx.equals("\\$\\$\\{.*?}")) {
            return input.trim().substring(3, input.length() - 1);
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        String ss = "{\n" +
                "    \"code\":200,\n" +
                "    \"msg\":\"查询成功！\",\n" +
                "    \"data\":{\n" +
                "        \"count\":7,\n" +
                "        \"list\":[\n" +
                "            {\n" +
                "                \"id\":80100,\n" +
                "                \"task_sn\":\"TB041800080100\",\n" +
                "                \"shop_sn\":\"E0010000143306\",\n" +
                "                \"shop_id\":306,\n" +
                "                \"shop_name\":\"西贝新店\",\n" +
                "                \"demand_sn\":\"DB0418005151\",\n" +
                "                \"demand_id\":5151,\n" +
                "                \"area_id\":139,\n" +
                "                \"city_id\":1,\n" +
                "                \"city_name\":\"北京\",\n" +
                "                \"area_name\":\"西二旗\",\n" +
                "                \"job_name\":\"餐厅保洁\",\n" +
                "                \"hour_expect\":3,\n" +
                "                \"task_description\":\"By Cucumber Random 西贝新店\",\n" +
                "                \"hour_actual\":0,\n" +
                "                \"hour_delay\":0,\n" +
                "                \"status\":10,\n" +
                "                \"status_name\":\"待分配\",\n" +
                "                \"staff_id\":0,\n" +
                "                \"staff_sn\":0,\n" +
                "                \"staff_name\":\"\",\n" +
                "                \"mobile\":\"\",\n" +
                "                \"start_date\":\"2021-04-29\",\n" +
                "                \"start_time\":\"14:00\",\n" +
                "                \"end_time\":\"16:59\",\n" +
                "                \"delay_add_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"accept_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"checkin_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"finish_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"cancel_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"amout_shop\":2500,\n" +
                "                \"actual_start_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"actual_end_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"add_time\":\"2021-04-18 18:54:58\",\n" +
                "                \"abnormal_types\":[\n" +
                "\n" +
                "                ],\n" +
                "                \"abnormal\":[\n" +
                "\n" +
                "                ],\n" +
                "                \"location\":{\n" +
                "                    \"type\":\"Point\",\n" +
                "                    \"coordinates\":[\n" +
                "                        116.301392,\n" +
                "                        40.050897\n" +
                "                    ]\n" +
                "                },\n" +
                "                \"is_bind\":2,\n" +
                "                \"work_type\":0,\n" +
                "                \"bill_staff_status\":0,\n" +
                "                \"bill_shop_status\":0,\n" +
                "                \"shop_settlement_amount\":0,\n" +
                "                \"staff_settlement_amount\":0,\n" +
                "                \"shop_change_hour\":0,\n" +
                "                \"staff_change_hour\":0,\n" +
                "                \"hour_diff\":0,\n" +
                "                \"period\":1,\n" +
                "                \"shop_remark\":\"\",\n" +
                "                \"shop_is_halal\":2,\n" +
                "                \"shop_contact\":\"12345\",\n" +
                "                \"shop_contact_phone\":\"13691541102\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\":80099,\n" +
                "                \"task_sn\":\"TB041800080099\",\n" +
                "                \"shop_sn\":\"E0010000143306\",\n" +
                "                \"shop_id\":306,\n" +
                "                \"shop_name\":\"西贝新店\",\n" +
                "                \"demand_sn\":\"DB0418005150\",\n" +
                "                \"demand_id\":5150,\n" +
                "                \"area_id\":139,\n" +
                "                \"city_id\":1,\n" +
                "                \"city_name\":\"北京\",\n" +
                "                \"area_name\":\"西二旗\",\n" +
                "                \"job_name\":\"餐厅保洁\",\n" +
                "                \"hour_expect\":3,\n" +
                "                \"task_description\":\"By Cucumber Random 西贝新店\",\n" +
                "                \"hour_actual\":0,\n" +
                "                \"hour_delay\":0,\n" +
                "                \"status\":10,\n" +
                "                \"status_name\":\"待分配\",\n" +
                "                \"staff_id\":0,\n" +
                "                \"staff_sn\":0,\n" +
                "                \"staff_name\":\"\",\n" +
                "                \"mobile\":\"\",\n" +
                "                \"start_date\":\"2021-04-29\",\n" +
                "                \"start_time\":\"14:00\",\n" +
                "                \"end_time\":\"16:59\",\n" +
                "                \"delay_add_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"accept_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"checkin_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"finish_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"cancel_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"amout_shop\":2500,\n" +
                "                \"actual_start_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"actual_end_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"add_time\":\"2021-04-18 18:54:31\",\n" +
                "                \"abnormal_types\":[\n" +
                "\n" +
                "                ],\n" +
                "                \"abnormal\":[\n" +
                "\n" +
                "                ],\n" +
                "                \"location\":{\n" +
                "                    \"type\":\"Point\",\n" +
                "                    \"coordinates\":[\n" +
                "                        116.301392,\n" +
                "                        40.050897\n" +
                "                    ]\n" +
                "                },\n" +
                "                \"is_bind\":2,\n" +
                "                \"work_type\":0,\n" +
                "                \"bill_staff_status\":0,\n" +
                "                \"bill_shop_status\":0,\n" +
                "                \"shop_settlement_amount\":0,\n" +
                "                \"staff_settlement_amount\":0,\n" +
                "                \"shop_change_hour\":0,\n" +
                "                \"staff_change_hour\":0,\n" +
                "                \"hour_diff\":0,\n" +
                "                \"period\":1,\n" +
                "                \"shop_remark\":\"\",\n" +
                "                \"shop_is_halal\":2,\n" +
                "                \"shop_contact\":\"12345\",\n" +
                "                \"shop_contact_phone\":\"13691541102\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\":80094,\n" +
                "                \"task_sn\":\"TB041800080094\",\n" +
                "                \"shop_sn\":\"E0010000143306\",\n" +
                "                \"shop_id\":306,\n" +
                "                \"shop_name\":\"西贝新店\",\n" +
                "                \"demand_sn\":\"DB0418005145\",\n" +
                "                \"demand_id\":5145,\n" +
                "                \"area_id\":139,\n" +
                "                \"city_id\":1,\n" +
                "                \"city_name\":\"北京\",\n" +
                "                \"area_name\":\"西二旗\",\n" +
                "                \"job_name\":\"餐厅保洁\",\n" +
                "                \"hour_expect\":3,\n" +
                "                \"task_description\":\"By Cucumber Random 西贝新店\",\n" +
                "                \"hour_actual\":0,\n" +
                "                \"hour_delay\":0,\n" +
                "                \"status\":10,\n" +
                "                \"status_name\":\"待分配\",\n" +
                "                \"staff_id\":0,\n" +
                "                \"staff_sn\":0,\n" +
                "                \"staff_name\":\"\",\n" +
                "                \"mobile\":\"\",\n" +
                "                \"start_date\":\"2021-04-29\",\n" +
                "                \"start_time\":\"14:00\",\n" +
                "                \"end_time\":\"16:59\",\n" +
                "                \"delay_add_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"accept_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"checkin_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"finish_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"cancel_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"amout_shop\":2500,\n" +
                "                \"actual_start_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"actual_end_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"add_time\":\"2021-04-18 18:18:18\",\n" +
                "                \"abnormal_types\":[\n" +
                "\n" +
                "                ],\n" +
                "                \"abnormal\":[\n" +
                "\n" +
                "                ],\n" +
                "                \"location\":{\n" +
                "                    \"type\":\"Point\",\n" +
                "                    \"coordinates\":[\n" +
                "                        116.301392,\n" +
                "                        40.050897\n" +
                "                    ]\n" +
                "                },\n" +
                "                \"is_bind\":2,\n" +
                "                \"work_type\":0,\n" +
                "                \"bill_staff_status\":0,\n" +
                "                \"bill_shop_status\":0,\n" +
                "                \"shop_settlement_amount\":0,\n" +
                "                \"staff_settlement_amount\":0,\n" +
                "                \"shop_change_hour\":0,\n" +
                "                \"staff_change_hour\":0,\n" +
                "                \"hour_diff\":0,\n" +
                "                \"period\":1,\n" +
                "                \"shop_remark\":\"\",\n" +
                "                \"shop_is_halal\":2,\n" +
                "                \"shop_contact\":\"12345\",\n" +
                "                \"shop_contact_phone\":\"13691541102\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\":80092,\n" +
                "                \"task_sn\":\"TB041800080092\",\n" +
                "                \"shop_sn\":\"E0010000143306\",\n" +
                "                \"shop_id\":306,\n" +
                "                \"shop_name\":\"西贝新店\",\n" +
                "                \"demand_sn\":\"DB0418005143\",\n" +
                "                \"demand_id\":5143,\n" +
                "                \"area_id\":139,\n" +
                "                \"city_id\":1,\n" +
                "                \"city_name\":\"北京\",\n" +
                "                \"area_name\":\"西二旗\",\n" +
                "                \"job_name\":\"餐厅保洁\",\n" +
                "                \"hour_expect\":3,\n" +
                "                \"task_description\":\"By Cucumber Random 西贝新店\",\n" +
                "                \"hour_actual\":0,\n" +
                "                \"hour_delay\":0,\n" +
                "                \"status\":10,\n" +
                "                \"status_name\":\"待分配\",\n" +
                "                \"staff_id\":0,\n" +
                "                \"staff_sn\":0,\n" +
                "                \"staff_name\":\"\",\n" +
                "                \"mobile\":\"\",\n" +
                "                \"start_date\":\"2021-04-29\",\n" +
                "                \"start_time\":\"14:00\",\n" +
                "                \"end_time\":\"16:59\",\n" +
                "                \"delay_add_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"accept_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"checkin_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"finish_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"cancel_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"amout_shop\":2500,\n" +
                "                \"actual_start_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"actual_end_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"add_time\":\"2021-04-18 18:03:16\",\n" +
                "                \"abnormal_types\":[\n" +
                "\n" +
                "                ],\n" +
                "                \"abnormal\":[\n" +
                "\n" +
                "                ],\n" +
                "                \"location\":{\n" +
                "                    \"type\":\"Point\",\n" +
                "                    \"coordinates\":[\n" +
                "                        116.301392,\n" +
                "                        40.050897\n" +
                "                    ]\n" +
                "                },\n" +
                "                \"is_bind\":2,\n" +
                "                \"work_type\":0,\n" +
                "                \"bill_staff_status\":0,\n" +
                "                \"bill_shop_status\":0,\n" +
                "                \"shop_settlement_amount\":0,\n" +
                "                \"staff_settlement_amount\":0,\n" +
                "                \"shop_change_hour\":0,\n" +
                "                \"staff_change_hour\":0,\n" +
                "                \"hour_diff\":0,\n" +
                "                \"period\":1,\n" +
                "                \"shop_remark\":\"\",\n" +
                "                \"shop_is_halal\":2,\n" +
                "                \"shop_contact\":\"12345\",\n" +
                "                \"shop_contact_phone\":\"13691541102\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\":80084,\n" +
                "                \"task_sn\":\"TB041800080084\",\n" +
                "                \"shop_sn\":\"E0010000143306\",\n" +
                "                \"shop_id\":306,\n" +
                "                \"shop_name\":\"西贝新店\",\n" +
                "                \"demand_sn\":\"DB0418005135\",\n" +
                "                \"demand_id\":5135,\n" +
                "                \"area_id\":139,\n" +
                "                \"city_id\":1,\n" +
                "                \"city_name\":\"北京\",\n" +
                "                \"area_name\":\"西二旗\",\n" +
                "                \"job_name\":\"餐厅保洁\",\n" +
                "                \"hour_expect\":3,\n" +
                "                \"task_description\":\"By Cucumber Random 西贝新店\",\n" +
                "                \"hour_actual\":0,\n" +
                "                \"hour_delay\":0,\n" +
                "                \"status\":10,\n" +
                "                \"status_name\":\"待分配\",\n" +
                "                \"staff_id\":0,\n" +
                "                \"staff_sn\":0,\n" +
                "                \"staff_name\":\"\",\n" +
                "                \"mobile\":\"\",\n" +
                "                \"start_date\":\"2021-04-29\",\n" +
                "                \"start_time\":\"14:00\",\n" +
                "                \"end_time\":\"16:59\",\n" +
                "                \"delay_add_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"accept_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"checkin_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"finish_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"cancel_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"amout_shop\":2500,\n" +
                "                \"actual_start_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"actual_end_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"add_time\":\"2021-04-18 14:37:19\",\n" +
                "                \"abnormal_types\":[\n" +
                "\n" +
                "                ],\n" +
                "                \"abnormal\":[\n" +
                "\n" +
                "                ],\n" +
                "                \"location\":{\n" +
                "                    \"type\":\"Point\",\n" +
                "                    \"coordinates\":[\n" +
                "                        116.301392,\n" +
                "                        40.050897\n" +
                "                    ]\n" +
                "                },\n" +
                "                \"is_bind\":2,\n" +
                "                \"work_type\":0,\n" +
                "                \"bill_staff_status\":0,\n" +
                "                \"bill_shop_status\":0,\n" +
                "                \"shop_settlement_amount\":0,\n" +
                "                \"staff_settlement_amount\":0,\n" +
                "                \"shop_change_hour\":0,\n" +
                "                \"staff_change_hour\":0,\n" +
                "                \"hour_diff\":0,\n" +
                "                \"period\":1,\n" +
                "                \"shop_remark\":\"\",\n" +
                "                \"shop_is_halal\":2,\n" +
                "                \"shop_contact\":\"12345\",\n" +
                "                \"shop_contact_phone\":\"13691541102\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\":80083,\n" +
                "                \"task_sn\":\"TB041800080083\",\n" +
                "                \"shop_sn\":\"E0010000143306\",\n" +
                "                \"shop_id\":306,\n" +
                "                \"shop_name\":\"西贝新店\",\n" +
                "                \"demand_sn\":\"DB0418005134\",\n" +
                "                \"demand_id\":5134,\n" +
                "                \"area_id\":139,\n" +
                "                \"city_id\":1,\n" +
                "                \"city_name\":\"北京\",\n" +
                "                \"area_name\":\"西二旗\",\n" +
                "                \"job_name\":\"餐厅保洁\",\n" +
                "                \"hour_expect\":3,\n" +
                "                \"task_description\":\"By Cucumber Random 西贝新店\",\n" +
                "                \"hour_actual\":0,\n" +
                "                \"hour_delay\":0,\n" +
                "                \"status\":10,\n" +
                "                \"status_name\":\"待分配\",\n" +
                "                \"staff_id\":0,\n" +
                "                \"staff_sn\":0,\n" +
                "                \"staff_name\":\"\",\n" +
                "                \"mobile\":\"\",\n" +
                "                \"start_date\":\"2021-04-29\",\n" +
                "                \"start_time\":\"14:00\",\n" +
                "                \"end_time\":\"16:59\",\n" +
                "                \"delay_add_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"accept_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"checkin_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"finish_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"cancel_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"amout_shop\":2500,\n" +
                "                \"actual_start_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"actual_end_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"add_time\":\"2021-04-18 14:12:06\",\n" +
                "                \"abnormal_types\":[\n" +
                "\n" +
                "                ],\n" +
                "                \"abnormal\":[\n" +
                "\n" +
                "                ],\n" +
                "                \"location\":{\n" +
                "                    \"type\":\"Point\",\n" +
                "                    \"coordinates\":[\n" +
                "                        116.301392,\n" +
                "                        40.050897\n" +
                "                    ]\n" +
                "                },\n" +
                "                \"is_bind\":2,\n" +
                "                \"work_type\":0,\n" +
                "                \"bill_staff_status\":0,\n" +
                "                \"bill_shop_status\":0,\n" +
                "                \"shop_settlement_amount\":0,\n" +
                "                \"staff_settlement_amount\":0,\n" +
                "                \"shop_change_hour\":0,\n" +
                "                \"staff_change_hour\":0,\n" +
                "                \"hour_diff\":0,\n" +
                "                \"period\":1,\n" +
                "                \"shop_remark\":\"\",\n" +
                "                \"shop_is_halal\":2,\n" +
                "                \"shop_contact\":\"12345\",\n" +
                "                \"shop_contact_phone\":\"13691541102\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\":80080,\n" +
                "                \"task_sn\":\"TB041800080080\",\n" +
                "                \"shop_sn\":\"E0010000143306\",\n" +
                "                \"shop_id\":306,\n" +
                "                \"shop_name\":\"西贝新店\",\n" +
                "                \"demand_sn\":\"DB0418005131\",\n" +
                "                \"demand_id\":5131,\n" +
                "                \"area_id\":139,\n" +
                "                \"city_id\":1,\n" +
                "                \"city_name\":\"北京\",\n" +
                "                \"area_name\":\"西二旗\",\n" +
                "                \"job_name\":\"餐厅保洁\",\n" +
                "                \"hour_expect\":3,\n" +
                "                \"task_description\":\"By Cucumber Random 西贝新店\",\n" +
                "                \"hour_actual\":0,\n" +
                "                \"hour_delay\":0,\n" +
                "                \"status\":10,\n" +
                "                \"status_name\":\"待分配\",\n" +
                "                \"staff_id\":0,\n" +
                "                \"staff_sn\":0,\n" +
                "                \"staff_name\":\"\",\n" +
                "                \"mobile\":\"\",\n" +
                "                \"start_date\":\"2021-04-29\",\n" +
                "                \"start_time\":\"14:00\",\n" +
                "                \"end_time\":\"16:59\",\n" +
                "                \"delay_add_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"accept_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"checkin_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"finish_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"cancel_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"amout_shop\":2500,\n" +
                "                \"actual_start_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"actual_end_time\":\"0000-00-00 00:00:00\",\n" +
                "                \"add_time\":\"2021-04-18 14:11:33\",\n" +
                "                \"abnormal_types\":[\n" +
                "\n" +
                "                ],\n" +
                "                \"abnormal\":[\n" +
                "\n" +
                "                ],\n" +
                "                \"location\":{\n" +
                "                    \"type\":\"Point\",\n" +
                "                    \"coordinates\":[\n" +
                "                        116.301392,\n" +
                "                        40.050897\n" +
                "                    ]\n" +
                "                },\n" +
                "                \"is_bind\":2,\n" +
                "                \"work_type\":0,\n" +
                "                \"bill_staff_status\":0,\n" +
                "                \"bill_shop_status\":0,\n" +
                "                \"shop_settlement_amount\":0,\n" +
                "                \"staff_settlement_amount\":0,\n" +
                "                \"shop_change_hour\":0,\n" +
                "                \"staff_change_hour\":0,\n" +
                "                \"hour_diff\":0,\n" +
                "                \"period\":1,\n" +
                "                \"shop_remark\":\"\",\n" +
                "                \"shop_is_halal\":2,\n" +
                "                \"shop_contact\":\"12345\",\n" +
                "                \"shop_contact_phone\":\"13691541102\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";

        //System.out.println(replaceFromJsonPath(ss,"$.msg"));
        //replaceFromEntityMap("${shop_id}");

        ObjectMapper jackson = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = jackson.readTree(ss);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (jsonNode.findValue("task_sn") != null) {
            System.out.println(jsonNode.findValue("task_sn").toString().replace("\"",""));
        }

        //System.out.println(replaceFromJsonPath(ss,"$.data.list[1].task_sn"));

    }
}
