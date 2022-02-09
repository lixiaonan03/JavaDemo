package invocation;

/**
  *  @author lixiaonan
  *  功能描述: 静态代理实现的
  *  时 间： 2020/12/13 12:06 PM
  */
public class HelloImpl implements Hello {
    @Override
    public void morning(String name) {
        System.out.println("Good morning, " + name);
    }
}