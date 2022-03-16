package gson;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author songyuqiang
 * 功能描述: $
 * 时 间： 2022/2/9
 */

@Retention(RetentionPolicy.SOURCE)
public @interface LogType {
    String NET_WORK_MONITOR = "network_monitor";
    String SHARE_PRODUCT = "share_product";
    String JSON_FORMAT = "json_format";
}
