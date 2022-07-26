package test;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static final class A {
        public void run(String aa) {
//            System.out.println("A");
        }
    }

    public static final class B {
        public void run(String aa) {
//            System.out.println("B");
        }
    }

    private static final Map<String, Method> sm = new HashMap<>(4, 1F);

    public static void invokeRun(Object obj) throws Throwable {
        final String key = obj.getClass().getName();

        Method method = sm.get(key);
        if (method == null) {
            method = obj.getClass().getMethod("run",String.class);
            sm.put(key, method);
        }
        method.invoke(obj,"aa");
    }

    private static final Map<String, MethodHandle> smh = new HashMap<>(4, 1F);

    public static void invokeRun2(Object obj) throws Throwable {
        final String key = obj.getClass().getName();
        MethodHandle mh = smh.get(key);
        if (mh == null) {
            MethodType mt = MethodType.methodType(void.class,String.class);
            mh = MethodHandles.lookup()
                    .findVirtual(obj.getClass(), "run", mt);
            smh.put(key, mh);
        }
        mh.invoke(obj,"aa");
    }

    public static void main(String[] args) throws Throwable {
        final Object a = new A();
        final Object b = new B();

        final int N = 10000000;

        final long t0 = System.nanoTime();
        for (int i = 0; i < N; i++) {
            invokeRun2(a);
            invokeRun2(b);
        }
        final long t2 = System.nanoTime();
        System.out.println("MethodHandle===cost " + ((t2 - t0) / 1000_000) + " ms.");


        final long t3 = System.nanoTime();
        for (int i = 0; i < N; i++) {
            invokeRun(a);
            invokeRun(b);
        }
        final long t1 = System.nanoTime();
        System.out.println("反射==cost " + ((t1 - t3) / 1000_000) + " ms.");


    }

    private static void http() throws Exception {
        InetAddress addr = InetAddress.getByName("www.baidu.com");
        Socket socket = new Socket(addr, 80);

        PrintStream ps = new PrintStream(socket.getOutputStream());
        ps.println("GET http://www.baidu.com/ HTTP/1.1");
        ps.println("Host: www.baidu.com");
        ps.println("User-Agent: okhttp3");
        ps.println();
        ps.flush();

        InputStreamReader reader = new InputStreamReader(socket.getInputStream());
        BufferedReader br = new BufferedReader(reader);
        String s;
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }

        ps.close();
        reader.close();
        socket.close();
    }

    private static void https() throws IOException {
        InetAddress addr = InetAddress.getByName("www.baidu.com");

        SocketFactory socketFactory = SSLSocketFactory.getDefault();
        Socket socket = socketFactory.createSocket(addr, 443);

        PrintStream ps = new PrintStream(socket.getOutputStream());
        ps.println("GET http://www.baidu.com/ HTTP/1.1");
        ps.println("Host: www.baidu.com");
        ps.println("User-Agent: okhttp3");
        ps.println();
        ps.flush();

        InputStreamReader reader = new InputStreamReader(socket.getInputStream());
        BufferedReader br = new BufferedReader(reader);
        String s;
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }

        ps.close();
        reader.close();
        socket.close();
    }
}
