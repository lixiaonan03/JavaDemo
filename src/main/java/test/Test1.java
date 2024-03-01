package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author：李晓楠 时间：2022/8/17 15:54
 */
public class Test1 {


    public static void main(String[] args) throws Exception {
        String aa = "test01\uD83D\uDE02❤️问题[旺柴]⊙ω⊙ xncnnc\uD83D\uDE02\uD83D\uDE0A\uD83D\uDE43\uD83D\uDE17dffm\uD83D\uDC6Cteshu01";
        //去掉表情字符的
        String bb = aa.replaceAll("[\\uD83C-\\uDBFF\\uDC00-\\uDFFF]+", "");

        System.out.println(bb);


        String ss= "assembleofficialWebsitedebug";
        Pattern pattern = Pattern.compile("(assemble|bundle)(\\w+)(Release|debug)");
        Matcher matcher = pattern.matcher(ss);
        if (matcher.find()) {
            String taskName = matcher.group(2);
            System.out.println("taskName==="+taskName);
        }else{
            System.out.println("taskName==nofind=");
        }
    }

}

