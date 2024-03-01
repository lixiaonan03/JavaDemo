package json;

import com.alibaba.fastjson.JSON;
import gson.AnalyticLog;
import gson.LogLevel;
import gson.LogType;

import java.util.Map;

/**
 * @author：李晓楠 时间：2023/3/13 18:14
 */
public class JsonUtil {

    public static void main(String[] args) {
        Testjson log = new Testjson();
        log.setAa_bb("11");

        String jsonStr = JSON.toJSONString(log);
        System.out.println("测试的数据==="+jsonStr);
        TestJson1 bean = JSON.parseObject(jsonStr,TestJson1.class);
        System.out.println("测试的数据=fanhui=="+bean.getAaBb());


        Map map = JSON.parseObject("{\n" +
                "\t\t\t\"app_ver\": \"1.0.0\",\n" +
                "\t\t\t\"biz_channel\": \"MX001\",\n" +
                "\t\t\t\"biz_type\": \"\",\n" +
                "\t\t\t\"button_id\": \"\",\n" +
                "\t\t\t\"content\": \"\",\n" +
                "\t\t\t\"crawler_time\": \"2023-03-29 09:05:05\",\n" +
                "\t\t\t\"duration\": null,\n" +
                "\t\t\t\"idCard\": \"\",\n" +
                "\t\t\t\"imei\": \"1639087029154181121\",\n" +
                "\t\t\t\"open_time\": null,\n" +
                "\t\t\t\"path_id\": \"module.auth.ui.LiveCheckActivity\",\n" +
                "\t\t\t\"start_time\": null,\n" +
                "\t\t\t\"time\": \"2023-03-29 09:05:05\",\n" +
                "\t\t\t\"type\": \"APP_BEHAVIOR\",\n" +
                "\t\t\t\"user_id\": \"1639087029154181121\"\n" +
                "\t\t}", Map.class);
        System.out.println("map=="+map.get("crawler_time"));
    }

}
