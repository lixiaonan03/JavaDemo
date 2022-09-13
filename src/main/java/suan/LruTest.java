package suan;

/**
 * @author lixiaonan
 * 功能描述: lru算法的实现的
 * 时 间： 2022/9/11 16:19
 */
public class LruTest {


    public static void main(String[] args) {
        LruCache<Integer, Integer> map = new LruCache<>(4);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.get(2);
        System.out.println(map);
    }

}

