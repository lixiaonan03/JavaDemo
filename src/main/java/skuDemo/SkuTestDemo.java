package skuDemo;

import java.util.Arrays;

/**
  *  @author lixiaonan
  *  功能描述: sku选择的list
  *  时 间： 2021/8/16 6:31 PM 
  */
public class SkuTestDemo {

    public static void main(String[] args) {
        Object sku [] = new Object[3];
        String s1[]={"黑","白"};
        String s2[]={"x","xl","xxxl"};
        String s3[]={"长","短"};
        sku[0]=s1;
        sku[1]=s2;
        sku[2]=s3;
        String[] skuArray=process(sku);
        System.out.println(Arrays.toString(skuArray));
    }

    public static String[] process(Object sku[]){
        int len = sku.length;
        if(len>=2){
            String  s1[]= (String[]) sku[0];
            int len1=s1.length;
            String  s2[]= (String[]) sku[1];
            int len2=s2.length;

            int len3=len1*len2;
            int index=0;
            String items[]=new String[len3];
            for(int i = 0; i < len1; i++) {
                for(int j = 0; j < len2; j++) {
                    items[index] = ((String[]) sku[0])[i] + ";" + ((String[]) sku[1])[j];
                    index++;
                }
            }
            // 将新组合的数组并到原数组中
            Object newArr[]=new Object[len-1];
            for(int i = 2; i < sku.length; i++) {
                newArr[i - 1] = sku[i];
            }
            newArr[0] = items;
            return process(newArr);

        }else {
            return (String[]) sku[0];
        }
    }

}
