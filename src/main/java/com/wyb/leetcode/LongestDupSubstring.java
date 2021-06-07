package com.wyb.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * 1044. 最长重复子串
 给出一个字符串 S，考虑其所有重复子串（S 的连续子串，出现两次或多次，可能会有重叠）。

 返回任何具有最长可能长度的重复子串。（如果 S 不含重复子串，那么答案为 ""。）

 示例 1：
 输入："banana"
 输出："ana"

 示例 2：
 输入："abcd"
 输出：""

 提示：
 2 <= S.length <= 10^5
 S 由小写英文字母组成。
 https://leetcode-cn.com/problems/longest-duplicate-substring/
 */
public class LongestDupSubstring {
    public String longestDupSubstring(String s) {
        int len = s.length();
        if (len == 0) return "";
        HashSet<String> travel = new HashSet<>();
        PriorityQueue<String[]> SubStrCnt = new PriorityQueue<>(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return -Integer.valueOf(o1[1]).compareTo(Integer.valueOf(o2[1]));
            }
        });

        for (int window = 1; window < len; window++) {
            // travel
            for (int i = 0; i < len - window + 1; i++) {
                String tmp = s.substring(i,i+window);
                if (travel.contains(tmp))
                    SubStrCnt.add(new String[]{tmp,String.valueOf(tmp.length())});
                travel.add(tmp);
            }
        }
        if (SubStrCnt.isEmpty()) return "";
        return SubStrCnt.peek()[0];
    }

    public static void main(String[] args) {
        LongestDupSubstring l = new LongestDupSubstring();
        // String s = "banana";
        String s = "abcd";
        String res = l.longestDupSubstring(s);
        System.out.println(res);
    }
}
