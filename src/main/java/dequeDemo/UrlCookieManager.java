package dequeDemo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：李晓楠 时间：2022/8/1 18:44
 */
public class UrlCookieManager {
    private final Map<String, String> apiHeaders = new HashMap<>(5);
    private static UrlCookieManager instance;
    public static UrlCookieManager getInstance() {
        if (instance == null) {
            synchronized (UrlCookieManager.class) {
                if (instance == null) {
                    instance = new UrlCookieManager();
                }
            }
        }
        return instance;
    }

    private UrlCookieManager() {

    }

    public Map<String, String> getApiHeader() {
        apiHeaders.put("1","1+");
        apiHeaders.put("2","2+");
        apiHeaders.put("3","3+");
        return apiHeaders;
    }
}
