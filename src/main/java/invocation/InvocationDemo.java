package invocation;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
  *  @author lixiaonan
  *  功能描述: 代理demo学习的
  *  时 间： 2020/12/13 12:07 PM
  */
public class InvocationDemo {
    public static void main(String[] args){
         //静态代理的
        Hello helloImpl = new HelloImpl();
        helloImpl.morning("静态代理");

        //动态代理的
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("需要执行的方法===="+method);
                if (method.getName().equals("morning")) {
                    System.out.println("Good morning, " + args[0]);
                }
                return null;
            }
        };
        //直接代理接口
        Hello hello = (Hello) Proxy.newProxyInstance(
                // 传入ClassLoader
                Hello.class.getClassLoader(),
                // 传入要实现的接口
                new Class[] { Hello.class },
                // 传入处理调用方法的InvocationHandler
                handler);
       hello.morning("动态代理");



        //代理接口实现的
        HelloImpl imp=new HelloImpl();
        Hello login=(Hello)Proxy.newProxyInstance(
                imp.getClass().getClassLoader(),
                imp.getClass().getInterfaces(),
                new ProxyHandler(imp));
        login.morning("动态代理接口实现的");
    }
}
