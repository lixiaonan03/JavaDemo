package suan;

/**
  *  @author lixiaonan
  *  功能描述: 排序算法联系
  *  时 间： 2022/8/22 10:44
  */
public class Paixu {
    public static void sort(int array[]){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length-1; j++) {
                int temp =0;
                if(array[j]>array[j+1]){
                   temp =array[j];
                   array[j] = array[j+1];
                   array[j+1] =temp;
                }
            }
        }
    }
}
