package Jvm.fanxing;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型相关的测试
 * @author：李晓楠
 * 时间：2023/10/13 10:56
 */
public class FanxingTest {

    public static void main(String[] args) {
        List<String> ls = new ArrayList<String>();
        List<Integer> li = new ArrayList<Integer>();
        //返回的是true 说明泛型擦除了
        System.out.println(ls.getClass() == li.getClass());
    }
}
