package Jvm.fanxing;

import java.util.List;

/**
 * 泛型擦除是指Java中的泛型只在编译期有效，在运行期间会被删除。也就是说所有泛型参数在编译后都会被清除掉。
 * @author：李晓楠
 * 时间：2023/10/16 14:56
 */
public class Foo {
    public void listMethod(List<String> stringList){
    }

    //上面这段代码编译时会报方法重载错误，原因是上面两个方法的参数是泛型参数，在编译后会被泛型擦除，最后两个方法都会是 public void listMethod(List intList)，所以会报重载错误的。
//    public void listMethod(List<Integer> intList) {
//    }
}
