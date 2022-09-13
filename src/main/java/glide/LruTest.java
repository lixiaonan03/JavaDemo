package glide;


/**
 * @author：李晓楠 时间：2022/8/3 15:55
 */
public class LruTest {
    public static void main(String[] args) {
        LruCache cache = new LruCache<String,Integer>(2);
        cache.put("1",1);
        cache.put("2",2);
        cache.put("1",1);
        cache.put("3",3);
        System.out.println("1=="+cache.get("1"));
        System.out.println("2=="+cache.get("2"));
        System.out.println("3=="+cache.get("3"));
    }
}
