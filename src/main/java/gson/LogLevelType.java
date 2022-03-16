package gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import util.StringUtil;

import java.io.IOException;


/**
 * @author lixiaonan
 * 功能描述: 针对日志实体和网络上报中字段的值不同处理的
 * 时 间： 2022/2/23 8:22 PM
 */
public class LogLevelType extends TypeAdapter<Integer> {
    @Override
    public void write(JsonWriter out, Integer value) throws IOException {
        if (value != null) {
            switch (value) {
//                debug: 0,
//                        notice: 1,
//                        warning: 2,
//                        fatal: 3
                case LogLevel.DEBUG:
                    out.value("debug");
                    break;
                case LogLevel.NOTICE:
                    out.value("notice");
                    break;
                case LogLevel.WARNIN:
                    out.value("warning");
                    break;
                case LogLevel.FATAL:
                    out.value("fatal");
                    break;
                default:
                    out.value("debug");
                    break;
            }
        } else {
            out.value("debug");
        }

    }

    @Override
    public Integer read(JsonReader in) throws IOException {
        if (in.peek() != JsonToken.NULL) {
            String string = in.nextString();
            if (StringUtil.equals("debug", string)) {
                return LogLevel.DEBUG;
            } else if (StringUtil.equals("notice", string)) {
                return LogLevel.NOTICE;
            } else if (StringUtil.equals("warning", string)) {
                return LogLevel.WARNIN;
            } else if (StringUtil.equals("fatal", string)) {
                return LogLevel.FATAL;
            }
        }
        return LogLevel.DEBUG;
    }
}
