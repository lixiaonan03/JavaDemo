package gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author：李晓楠 时间：2022/2/23 20:48
 */
public class GsonMain {
    public static void main(String[] args) {
        AnalyticLog log = new AnalyticLog();
        log.feLogId = "12";
        log.logLevel = LogLevel.FATAL;
        log.logType = LogType.JSON_FORMAT;
        String jsonStr = getGson().toJson(log);
        System.out.println("测试的数据==="+jsonStr);
        AnalyticLog bean = getGson().fromJson(jsonStr,AnalyticLog.class);
        System.out.println("测试的数据=fanhui=="+bean.logLevel);
    }

    private static final String KEY_DEFAULT = "defaultGson";
    private static final String KEY_DELEGATE = "delegateGson";
    private static final String KEY_LOG_UTILS = "logUtilsGson";

    private static final Map<String, Gson> GSONS = new ConcurrentHashMap<>();

    public static Gson getGson() {
        Gson gsonDelegate = GSONS.get(KEY_DELEGATE);
        if (gsonDelegate != null) {
            return gsonDelegate;
        }
        Gson gsonDefault = GSONS.get(KEY_DEFAULT);
        if (gsonDefault == null) {
            gsonDefault = createGson();
            GSONS.put(KEY_DEFAULT, gsonDefault);
        }
        return gsonDefault;
    }
    private static Gson createGson() {
        return new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
    }


}
