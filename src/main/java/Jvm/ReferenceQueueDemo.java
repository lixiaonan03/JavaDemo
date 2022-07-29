package Jvm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 因为map的key是WeakReference，所以在内存不足的时候，weakReference所指向的对象就会被GC，
 * 在对象被GC的同时，会把该对象的包装类即weakReference放入到ReferenceQueue里面。但是这个map的大小是10000。
 */
class ReferenceQueueDemo {

    private static ReferenceQueue<byte[]> referenceQueue = new ReferenceQueue<>();
    private static int _1M = 1024 * 1024;

    public static void main(String[] args) {

        Integer object = new Integer(1);
        Map<Object, Integer> map = new HashMap<>(10000);

        //回收了对象之后  弱引用队列中会包含这个被回收对象的
        Thread thread = new Thread(() -> {
            try {
                int cnt = 0;
                WeakReference<byte[]> k;
                while ((k = (WeakReference) referenceQueue.remove()) != null) {
                    System.out.println((cnt++) + "回收了:" + k);
                }
            } catch (InterruptedException e) {
                //结束循环
            }
        });
        thread.setDaemon(true);
        thread.start();

        for (int i = 0; i < 10000; i++) {
            byte[] bytes = new byte[_1M];
            WeakReference<byte[]> weakReference = new WeakReference<>(bytes, referenceQueue);
            //mapkey  存放一个弱引用对象
            map.put(weakReference, object);
        }
        System.out.println("map.size->" + map.size());
    }
}

