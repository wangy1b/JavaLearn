package com.wyb.leetcode;

/*

557. 反转字符串中的单词 III
给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。



示例：

输入："Let's take LeetCode contest"
输出："s'teL ekat edoCteeL tsetnoc"


提示：

在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。

https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/

 */
public class ReverseWordsInAStringIII {
    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        System.out.println(reverseWords1(s));
    }

    private static String reverseWords0(String s) {
        String[] arr = s.split("\\s");
        StringBuffer res = new StringBuffer();
        for (String s1 : arr) {
            char[] chars = s1.toCharArray();
            helper(chars, 0, chars.length - 1);
            res.append(chars);
            res.append(" ");
        }
        return res.toString().substring(0, s.length()).trim();

    }

    private static String reverseWords1(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        int end = 0;
        for (int i = 0; i < len; i = end + 1) {
            int t = s.indexOf(" ", i);
            end = t > 0 ? t : len;
            if (end > 0) {
                helper(chars, i, end - 1);
            }
        }
        return String.valueOf(chars);
    }


    private static void helper(char[] s, int left, int right) {
        if (left >= right) {
            return;
        }
        char tmp = s[left];
        s[left++] = s[right];
        s[right--] = tmp;
        helper(s, left, right);
    }
}
