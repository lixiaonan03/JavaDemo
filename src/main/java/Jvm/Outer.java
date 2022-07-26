package Jvm;

/**
  *  @author lixiaonan
  *  功能描述: 匿名内部类持有外部类的this 引用
  *  时 间： 2022/7/26 15:32
  */
public class Outer {
    private String name;
    private LxnCallBack lxnCallBack;

    public void setLxnCallBack(LxnCallBack lxnCallBack) {
        this.lxnCallBack = lxnCallBack;
    }

    public void click(int num){
        if(lxnCallBack!=null){
            lxnCallBack.out("click点击事件+"+num);
        }
    }

    class Inner{
        private String test;
    }

    public interface  LxnCallBack{
        void out(String aa);
    }
}
