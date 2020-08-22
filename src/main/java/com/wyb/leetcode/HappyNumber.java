package com.wyb.leetcode;

import java.util.Arrays;
import java.util.HashSet;

/*

202. 快乐数

编写一个算法来判断一个数 n 是不是快乐数。

「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，
然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
如果 可以变为1，那么这个数就是快乐数。

如果 n 是快乐数就返回 True ；不是，则返回 False 。

 

示例：

输入：19
输出：true
解释：
1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/happy-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
public class HappyNumber {
    public static void main(String[] args) {
        // System.out.println(getSquarSum(19));
        System.out.println(isHappy(199));

    }

    private static int getSquarSum(int n) {
        int temp = n;
        int res = 0;
        while (temp > 0) {
            int reminder = temp % 10;
            res += reminder * reminder;
            temp = temp / 10;
        }
        // System.out.println("res : " + res);
        return res;
    }

    private static boolean isHappy(int n) {
        int temp = n;
        HashSet<Integer> hashSet = new HashSet<>();
        while (temp != 1) {
            int res = getSquarSum(temp);
            if (hashSet.contains(res)) {
                return false;
            }
            hashSet.add(res);
            temp = res;
        }
        return true;
    }
    private static boolean isHappyOfficial(int n) {
        int temp = n;
        HashSet<Integer> hashSet = new HashSet<>(Arrays.asList(4, 16, 37, 58, 89, 145, 42, 20));
        while (temp != 1 && !hashSet.contains(temp)) {
            temp = getSquarSum(temp);
        }
        return temp == 1;
    }
}
