package invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
  *  @author lixiaonan
  *  功能描述: 动态代理代理接口实现类的
  *  时 间： 2020/12/13 7:21 PM 
  */
public class ProxyHandler implements InvocationHandler {
    private Object target;
    public ProxyHandler(Object obj){
        this.target=obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理接口实现需要执行的方法===="+method);
        Object ret=null;
        System.out.println("before->"+method.getName());
        ret=method.invoke(target, args);
        System.out.println("after->"+method.getName());
        return ret;
    }
}