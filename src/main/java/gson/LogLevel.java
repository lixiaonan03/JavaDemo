package gson;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * https://wanwuxinxuan.feishu.cn/wiki/wikcnUoNf331BiPjV43iJl0hKOd
 * logLevel: NOTICE|WARNIN|FATAL|DEBUG
 */
@Retention(RetentionPolicy.SOURCE)
public @interface LogLevel {
    int NOTICE = 1;
    int WARNIN = 2;
    int FATAL = 3;
    int DEBUG = 0;
}
