package suan;

import java.util.*;

/**
 * @author lixiaonan
 * 功能描述: 数据结构算法
 * 时 间： 2022/7/31 23:39
 */
public class StructureDemo {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
//        System.out.println("判断是否存在重复的数字--" + containsDuplicate(nums));
        int[] numsSum = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        System.out.println("最大子序和--" + maxSubArray(numsSum));
        int[] numsAdd = {2,7,11,15};
//        System.out.println("2数之和--" + twoSum(numsAdd,9)[0]);

        //合并2个有序数组的
        mergeList();
        //股票的贪心算法
        maxProfit();
    }

    /**
     * 股票的贪心算法的
     */
    private static int maxProfit() {
        int[] prices = {7,15,5,8,6,3};
        // 7,15,5,2,6,3
        //先将数组的第一个数当作最小值
        int min = prices[0];
        //存放利润
        int res =0;

        for (int i = 1; i < prices.length; i++) {
            if(prices[i] < min){
                min = prices[i];
            }else{
                //不小，先求出当前值与min的差值
                int tmp = prices[i] - min;
                //差值与res比较
                if(res < tmp){
                    //res小就更新
                    res = tmp;
                }
            }
        }
        System.out.println(res);
        return res;
    }

    /**
     * 合并2个有序数组的
     * @return
     */
    private static void mergeList(){
        int[] list1 = {1, 5, 8, 9};
        int[] list2 = {4,6,7,12};
        int[] result = new int[list1.length+list2.length];
        int i = 0,j=0,k=0;
        while (i<list1.length && j< list2.length){
            if(list1[i] < list2[j]){
                result[k++] = list1[i++];
            }else{
                result[k++] = list2[j++];
            }
        }
        //判断超过的部分 如果是1多的情况
        while ( i < list1.length){
            result[k++] = list1[i++];
        }
        while ( j < list2.length){
            result[k++] = list2[j++];
        }
        System.out.println(Arrays.toString(result));
    }
    /**
     * 判断一个数组中是否存在重复的数字
     * @param nums
     * @return
     */
    public static boolean containsDuplicate(int[] nums) {
        Set set = new HashSet();
        for (int i : nums) {
            set.add(i);
        }
        return set.size() != nums.length;
    }

    /**
     * 找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
//        int pre = 0;
//        int maxAns = nums[0];
//        for (int x : nums) {
//            pre = Math.max(pre+x,x);
//            maxAns = Math.max(maxAns,pre);
//        }
//        return maxAns;

        //贪心算法的
        int ans = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    /**
     * 2数之和等于目标值
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] results = new int[2];
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int num : nums) {
            if (map.containsKey(num)) {
                results[0] = map.get(num);
                results[1] = num;
                return results;
            } else {
                map.put(target - num, num);
            }
        }
        return null;
    }
}
