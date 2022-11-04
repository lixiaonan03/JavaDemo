package dequeDemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author：李晓楠 时间：2022/3/20 11:25
 */
public class HashMapTest {
    public static void main(String[] args) {
        Map<String, String> map = UrlCookieManager.getInstance().getApiHeader();
        map.put("4", "4+");
//        System.out.println("===" + map.size() + "===" + UrlCookieManager.getInstance().getApiHeader().size());
        hashCodeTest();

        Map<String, String> map1 = new HashMap<>();
        map1.put(null,"1");
        Map<String, String> map2 = new ConcurrentHashMap<>();
        map1.put(null,"1");

    }


    /**
     * hashCode计算实列的
     */
    private static void hashCodeTest() {
        List<String> list = new ArrayList<>();
        list.add("jlkk");
        list.add("lopi");
        list.add("jmdw");
        list.add("e4we");
        list.add("io98");
        list.add("nmhg");
        list.add("vfg6");
        list.add("gfrt");
        list.add("alpo");
        list.add("vfbh");
        list.add("bnhj");
        list.add("zuio");
        list.add("iu8e");
        list.add("yhjk");
        list.add("plop");
        list.add("dd0p");
        for (String key : list) {
            int hash = key.hashCode() ^ (key.hashCode() >>> 16);
            System.out.println("字符串：" + key + " \tIdx(16)：" + ((16 - 1) & hash) + " \tBit值：" + Integer.toBinaryString(hash)
                    + " - " + Integer.toBinaryString(hash & 16) + " \t\tIdx(32)：" + (Integer.toBinaryString(key.hashCode())
                    + " " + Integer.toBinaryString(hash) + " " + Integer.toBinaryString((32 - 1) & hash)));
        }
    }
}
