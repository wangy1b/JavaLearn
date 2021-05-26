package com.wyb.leetcode;

import java.awt.*;
import java.util.*;

/**
 * 76. 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
 * 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * <p>
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 * <p>
 * 提示：
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 * <p>
 * <p>
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 * https://leetcode-cn.com/problems/minimum-window-substring/
 */
public class MinimumWindowSubstring {
    public static void main(String[] args) {
        MinimumWindowSubstring m = new MinimumWindowSubstring();
        // String s = "ADOBECODEBANC";
        // String t = "ABC";
        String s = "aa";
        String t = "aa";
        String res = m.minWindow(s, t);
        System.out.println(res);
    }

    /**
     *  超出时间限制
     */
    public String minWindow(String s, String t) {
        String res = "";
        if (s.length() < t.length()) return res;
        if (s.equals(t)) return s;
        // pq中存放一个数组，s字符串包含t字符串开始位置和结束位置[s,e],按e-s即为长度来判断谁最短
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int len1 = o1[1] - o1[0];
                int len2 = o2[1] - o2[0];
                int cmp = len1 - len2;
                return cmp;
            }
        });
        // 目标字符串 转为map 方便判断
        HashMap<Character, Integer> target = new HashMap<>(t.length());
        for (int i = 0; i < t.length(); i++) {
            target.put(t.charAt(i), target.getOrDefault(t.charAt(i), 0) + 1);
        }

        // s字符串
        for (int i = 0; i < s.length(); i++) {
            HashMap<Character, Integer> tmp = (HashMap<Character, Integer>) target.clone();
            if (tmp.containsKey(s.charAt(i))) {
                for (int p = i; p < s.length() && !tmp.isEmpty(); p++) {
                    if (tmp.containsKey(s.charAt(p)) && tmp.get(s.charAt(p)) > 0) {
                        if (tmp.get(s.charAt(p)) == 1)
                            tmp.remove(s.charAt(p));
                        else
                            tmp.put(s.charAt(p), tmp.get(s.charAt(p)) - 1);
                    }
                    if (tmp.isEmpty()) {
                        pq.add(new int[]{i, p});
                        break;
                    }
                }
            }
        }

        if (pq.size() > 0) {
            int[] idxs = pq.peek();
            res = s.substring(idxs[0], idxs[1] + 1);
        }
        return res;
    }


    /*
    https://leetcode-cn.com/problems/minimum-window-substring/solution/zui-xiao-fu-gai-zi-chuan-by-leetcode-solution/
     */
    Map<Character, Integer> ori = new HashMap<Character, Integer>();
    Map<Character, Integer> cnt = new HashMap<Character, Integer>();

    public String minWindowOfficial(String s, String t) {
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            ++r;
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            while (check() && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    public boolean check() {
        Iterator iter = ori.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }

}
