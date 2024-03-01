package dequeDemo;

import java.util.*;

/**
  *  @author lixiaonan
  *  功能描述: list判断的
  *  时 间： 2020/11/10 12:38 PM
  */
public class ListJavaTest {


    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        List<String> listnew = list.subList(0, 9);
        listnew.add(0,"第0个");

        //addAll 会改变原数组，但是返回值是true or false

        System.out.println("size====" + list.get(0));
        System.out.println("listnew====" + listnew);


        String[] strArr = {"张三","李四","王二麻"};
        List list1 = Arrays.asList(strArr);
        System.out.println("strArr====" + strArr);
        strArr[1] = "lxn";
        System.out.println("size====" + list1.get(1));

    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
