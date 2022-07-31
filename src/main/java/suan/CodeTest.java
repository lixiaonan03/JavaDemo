package suan;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：李晓楠 时间：2022/2/9 10:31
 */
public class CodeTest {

    /**
     * 求一个数组中 那2个数加起来 等于目标值
     * @param nums
     * @param target
     * @return  返回出现的2个数组
     */
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>(16);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }
            //eg:     {2, 1, 7, 9, 10, 3, 2}  目标值是9  相当于第一次放入的的 【7，2】 然后遍历到7的时候就找到了
            map.put(target - nums[i], i);
        }
        return null;
    }


    public static int select(int[] nums, int target) {
        int aa = -1;
        //最简单的遍历查找
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] == target) {
//                aa = i;
//                break;
//            }
//        }

        // 避免当 target 小于nums[0] nums[nums.length - 1]时多次循环运算
        if (target < nums[0] || target > nums[nums.length - 1]) {
            return -1;
        }
        //2分查找法
//        int left = 0;
//        int right = nums.length - 1;
//        //左闭右闭
//        while (left <= right) {
//            // >> 相当于 num*2
//            int mid = left + ((right - left) >> 1);
//            if (nums[mid] == target)
//                return mid;
//            else if (nums[mid] < target)
//                left = mid + 1;
//            else if (nums[mid] > target)
//                right = mid - 1;
//        }
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int num = nums[low];
            if (num == target) {
                return mid;
            } else if (num > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }

        }

        return aa;
    }


    public static void main(String[] args) {
        int[] aa = {2, 1, 7, 9, 10, 3, 2};
        int[] bb = twoSum(aa, 9);
        System.out.println(bb[1]);

        int selectAA = select(aa, 1);
        System.out.println("查找==" + selectAA);
    }


    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = n;
        while (left <= right) {
            // 除2
            int mid = ((right - left) >> 1) + left;
            if (target <= nums[mid]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 数组每个元素平方之后排序
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int negative = -1;
        //找到附属在第几个
        for (int i = 0; i < n; ++i) {
            if (nums[i] < 0) {
                negative = i;
            } else {
                break;
            }
        }

        int[] ans = new int[n];
        int index = 0, i = negative, j = negative + 1;
        while (i >= 0 || j < n) {
            if (i < 0) {
                ans[index] = nums[j] * nums[j];
                ++j;
            } else if (j == n) {
                ans[index] = nums[i] * nums[i];
                --i;
            } else if (nums[i] * nums[i] < nums[j] * nums[j]) {
                ans[index] = nums[i] * nums[i];
                --i;
            } else {
                ans[index] = nums[j] * nums[j];
                ++j;
            }
            ++index;
        }

        return ans;
    }
}

