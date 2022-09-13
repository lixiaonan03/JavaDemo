package suan;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lixiaonan
 * 功能描述: 数据结构算法
 * 时 间： 2022/7/31 23:39
 */
public class StructureDemo {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        System.out.println("判断是否存在重复的数字--" + containsDuplicate(nums));
        int[] numsSum = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("最大子序和--" + maxSubArray(numsSum));
        int[] numsAdd = {2,7,11,15};
        System.out.println("2数之和--" + twoSum(numsAdd,9)[0]);
    }

    /**
     * 判断一个数组中是否存在重复的数字
     *
     * @param nums
     * @return
     */
    public static boolean containsDuplicate(int[] nums) {
        Set set = new HashSet();
        for (int i : nums) {
            set.add(i);
        }
        if (set.size() == nums.length) {
            return false;
        } else {
            return true;
        }
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
