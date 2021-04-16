package Utils;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
 * @author zsy
 * @description 读取yaml
 * @date 2021/4/5 13:12
 */
public class YamlReader {

    private static Map map = new HashMap<>();

    /**
     * 单例
     */
    public static final YamlReader instance = new YamlReader();

    /*static {
        try {
            Yaml yaml = new Yaml();
            File directory = new File("src\\main\\resources\\managerParams\\Manager.yml");
            //directory.getCanonicalPath()是项目路径、File.separator是右斜杠“\”
            //String courseFile = directory.getCanonicalPath() + File.separator + "config" + File.separator + "configType.yaml"; //待压缩文件所在的根目录,暂时不用
            InputStream inputStream = new FileInputStream(directory);
            map = (Map) yaml.load(inputStream); //文件转map  直接toString也可以
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * get yaml's Map
     * @param key
     * @return
     */
    public Object getValueByKey(String apiPath,String key) {

        String[] separatorKeys = new String[0];
        Map<String, Map<String, Object>> finalValue = null;
        try {
            Yaml yaml = new Yaml();
            String api[] = apiPath.split("\\."); //根据 . 去分割
            String path = "src/main/resources/managerParams/" + api[0] + ".yml"; //得到yml文件
            //win  src\\main\\resources\\managerParams\\" + api[0] + ".yml"
            File directory = new File(path);
            InputStream inputStream = new FileInputStream(directory);
            Map map = (Map) yaml.load(inputStream); //文件转map  直接toString也可以


            String separator = ".";
            separatorKeys = null;
            if (key.contains(separator)) {
                separatorKeys = key.split("\\."); //正则"\."代表"."
            } else {
                return map.get(key);
            }
            finalValue = new HashMap<>();
            for (int i = 0; i < separatorKeys.length - 1; i++) {
                if (i == 0) {
                    finalValue = (Map) map.get(separatorKeys[i]);
                    continue;
                }
                if (finalValue == null) {
                    break;
                }
                finalValue = (Map) finalValue.get(separatorKeys[i]);
            }
            return finalValue == null ? null : finalValue.get(separatorKeys[separatorKeys.length - 1]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    public void testYamlReader() {
        Map serverHost = (Map) YamlReader.instance.getValueByKey("Manager.login","login.params");
        for(Object o : serverHost.keySet()){
            System.out.println("k = "+o);
            System.out.println("v = "+serverHost.get(o));
        }

    }
}
