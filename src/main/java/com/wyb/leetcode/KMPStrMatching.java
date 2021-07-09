package com.wyb.leetcode;

// https://ghh3809.github.io/2018/08/05/kmp/#top
public class KMPStrMatching {

    public int match(String P, String T) {
        int n = T.length(); // 文本串长度
        int m = P.length(); // 模式串长度
        for (int i = 0; i < n - m + 1; i ++) { // 文本串中的起始查找位置
            if (T.substring(i, i + m).equals(P)) return i; // 匹配子串
        }
        return -1;
    }

    public int match2(String P, String T) {
        int n = T.length(), i = 0; // 文本串长度及当前比对字符
        int m = P.length(), j = 0; // 模式串长度及当前比对字符
        while ((i < n) && (j < m)) {
            if (T.charAt(i) == P.charAt(j)) {
                i ++; j ++; // 匹配，转到下一字符
            } else {
                i -= j - 1; j = 0; // 文本串回退，模式串复位
            }
        }
        return (i - j > n - m) ? -1 : i - j; // 若匹配成功，则i-j表示匹配初始位置
    }

    public int KMP(String P, String T) {
        int n = T.length(), i = 0; // 文本串指针
        int m = P.length(), j = 0; // 模式串指针
        int[] next = buildNext(P); // 构建模式串P的next表
        while ((j < m) && (i < n)) {
            if ((j < 0) || (P.charAt(j) == T.charAt(i))) { // 匹配时，移动到下一字符
                j ++; i ++;
            } else { // 不匹配时，加速移动模式串
                j = next[j];
            }
        }
        return (i - j > n - m) ? -1 : i - j;
    }

    private int[] buildNext(String P) {
        int m = P.length();
        int[] next = new int[m];
        next[0] = -1; // 初始化next表
        int t = -1, j = 0; // j为“主”串指针，t为移动串指针
        while (j < m - 1) {
            if ((t < 0) || (P.charAt(j) == P.charAt(t))) {
                // 匹配的情况下，若后一元素不相等时才能移动到t，否则要直接移动到next[t]
                j ++; t ++;
                next[j] = (P.charAt(j) != P.charAt(t) ? t : next[t]);
            } else { // 不匹配时，加速移动模式串
                t = next[t];
            }
        }
        return next;
    }

    public static void main(String[] args) {
        KMPStrMatching k = new KMPStrMatching();
        String p = "ello", t = "helloworldhello";
        // System.out.println(k.match(p, t));
        // System.out.println(k.match2(p, t));
        System.out.println(k.KMP(p, t));

    }
}
