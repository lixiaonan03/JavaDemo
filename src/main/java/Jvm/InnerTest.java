package Jvm;

/**
  *  @author lixiaonan
  *  功能描述: 测试匿名内部类的测试
  *  时 间： 2022/7/26 17:27
  */
public class InnerTest {
    public static void main(String[] args) {
        Outer outer =new Outer();
        int[] num = {args.length};
        outer.setLxnCallBack(new Outer.LxnCallBack() {
            @Override
            public void out(String aa) {
                num[0]++; //报错的
                System.out.println("num==="+ num[0]);
                System.out.println("接口输出的==="+aa);
            }
        });
        outer.click(num[0]);
    }

    /**
     * 不通过静态方法调用 匿名内部类会持有外部类引用
     *  InnerTest$2(InnerTest this$0, int[] var2) {
     *         this.this$0 = this$0;
     *         this.val$num = var2;
     *     }
     */
    public void test(){
        Outer outer =new Outer();
        int a = 0;
        int[] num = {a++};
        outer.setLxnCallBack(aa -> {
            num[0]++; //报错的
            System.out.println("num==="+ num[0]);
            System.out.println("接口输出的==="+aa);
        });
        outer.click(num[0]);
    }
}
