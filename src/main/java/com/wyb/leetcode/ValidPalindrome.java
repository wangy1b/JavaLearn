package com.wyb.leetcode;
/*

给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

说明：本题中，我们将空字符串定义为有效的回文串。

示例 1:

输入: "A man, a plan, a canal: Panama"
输出: true
示例 2:

输入: "race a car"
输出: false

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/valid-palindrome
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


 */
public class ValidPalindrome {

    public static void main(String[] args) {
        // String s = "A man, a plan, a canal: Panama";
        String s = "a12, b   B , 21 a + - / | ";
        System.out.println(s.replaceAll("\\P{LD}+", "").toLowerCase());
        System.out.println(isPalindrome(s));
    }


    private static boolean isPalindrome(String s) {
        boolean res = true;
        if (s.isEmpty()) return res;
        StringBuffer ns = new StringBuffer();
        ns.append(s.replaceAll("\\P{LD}+", "").toLowerCase());
        int start = 0, end = ns.length() -1;
        while (start < end){
            if (ns.charAt(start++) != ns.charAt(end--)) res = false;
        }
        return res;
    }



}
