package gson;


import com.google.gson.annotations.JsonAdapter;


import java.util.HashMap;

/**
  *  @author 宋宇强
  *  功能描述: 整个日志系统的日志工具类的
  *  时 间： 2022/2/22 6:04 PM
  */
public class AnalyticLog{
    /**
     * 当前日志的 日志等级
     */
    @JsonAdapter(LogLevelType.class)
    public @LogLevel int logLevel;
    /**
     * 当前日志的 模块
     */
    public @LogType String logType;
    /**
     * 前端日志logid
     */
    public String feLogId;
    /**
     * 当前的时间戳 毫米级
     */
    public long timestamp;
    /**
     * sessionId  访问的sessionId
     */
    public String sessionId;
    /**
     * 网络当前的类型的
     */
    public String networkType;
    /**
     * 当期的界面
     */
    public String page;
    /**
     * 当期的userId
     */
    public String userId;

    /**
     * 当前额外补充的数据
     */
    public HashMap<String, String> statTrace;
}
