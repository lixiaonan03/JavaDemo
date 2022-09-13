package suan;


import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        float[] p1 = new float[]{1,1};
        float[] p2 = new float[]{2,3};
        List<float[]> list =new ArrayList<>();
        list.add(p1);
        list.add(p2);
        System.out.println("结果==="+linear(list,2,2f));
    }
    /**
     * 线性函数问题:
     * 线性函数(y = kx + b)是应用非常广的函数。在平面上给出 n 个点，
     * 可组成 (n - 1) 个区间的线性函数。
     * 例如:
     * 1. 给出点 p1 = (1, 1) 和点 p2 = (2, 2)，连结 p1 和 p2，可确定一条唯一的函数 y = x;
     * <p>
     * 2. 给出点 p1 = (-1, 0), p2 = (0, 1), p3 = (1, 0)，依次连结 p1p2, p2p3，可确定两条直线: y = x + 1 和 y = -x + 1
     * <p>
     * 3. 给出点 p1 = (1, 1), p2 = (2, 2), p3 = (3, 1), p4 = (4, 2)，依次连结 p1p2，p2p3，p3p4，可确定三条直
     * 试求:
     * 给定 n 个点坐标和一个位于 (-∞, +∞) 的 x 坐标，确定一组线性函数，并返回其 y 值
     *
     * @param points 点坐标，float[0] 为 x 坐标，float[1] 为 y 坐标;
     * @param n      点坐标的个数，等于 points.size()
     * @param x      随机给出的 x 值
     */
    public static  float linear(List<float[]> points, int n, float x) {
        //先判断是否位于边界
        if(x<points.get(0)[0] || x>points.get(n-1)[0]){
            return 0;
        }
        //判断在那一段
        int num = 0;
        for (int i = 0; i < n; i++) {
            if(x <= points.get(i)[0]){
                //当前的点在 i-1 和 i之间的
                num =i;
                break;
            }
        }
        //再算比例
        float nFloat = (points.get(num)[1]-points.get(num-1)[1])/(points.get(num)[0]-points.get(num-1)[0]);
        //先算偏移值
        float p = points.get(num)[1] - nFloat* points.get(num)[0];
        return (nFloat * x + p);
    }
}