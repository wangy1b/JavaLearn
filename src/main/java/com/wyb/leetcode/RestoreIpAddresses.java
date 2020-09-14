package com.wyb.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*

93. 复原IP地址
给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。

有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。

例如："0.1.2.201" 和 "192.168.1.1" 是 有效的 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效的 IP 地址。



示例 1：

输入：s = "25525511135"
输出：["255.255.11.135","255.255.111.35"]
示例 2：

输入：s = "0000"
输出：["0.0.0.0"]
示例 3：

输入：s = "1111"
输出：["1.1.1.1"]
示例 4：

输入：s = "010010"
输出：["0.10.0.10","0.100.1.0"]
示例 5：

输入：s = "101023"
输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]


提示：

0 <= s.length <= 3000
s 仅由数字组成

https://leetcode-cn.com/problems/restore-ip-addresses/

 */
public class RestoreIpAddresses {
    public static void main(String[] args) {
        String s = "25525511135";
        System.out.println(restoreIpAddresses(s));
    }
    private static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        StringBuffer tmp = new StringBuffer();

        for (int a = 1; a < 4; a++) {
            for (int b = 1; b < 4; b++) {
                for (int c = 1; c < 4; c++) {
                    for (int d = 1; d < 4; d++) {

                        if ( a + b + c +d == s.length() ) {
                            int fst = Integer.parseInt(s.substring(0,a));
                            int sec = Integer.parseInt(s.substring(a,a+b));
                            int thd = Integer.parseInt(s.substring(a+b,a+b+c));
                            int fth = Integer.parseInt(s.substring(a+b+c,a+b+c+d));

                            if (fst < 256 && sec < 256 && thd < 256 && fth < 256) {
                                tmp.append(fst).append(".").append(sec).append(".")
                                        .append(thd).append(".").append(fth);

                                if (tmp.length() == s.length() + 3) {
                                    res.add(tmp.toString());
                                }
                                tmp.delete(0,tmp.length());

                            }



                        }


                    }
                }
            }
        }
        return res;
    }

    // 剪枝
    public List<String> restoreIpAddresses2(String s) {
        int len = s.length();
        List<String> res = new ArrayList<>();
        if (len > 12 || len < 4) {
            return res;
        }

        Deque<String> path = new ArrayDeque<>(4);
        dfs(s, len, 0, 4, path, res);
        return res;
    }

    // 需要一个变量记录剩余多少段还没被分割
    private void dfs(String s, int len, int begin, int residue, Deque<String> path, List<String> res) {
        if (begin == len) {
            if (residue == 0) {
                res.add(String.join(".", path));
            }
            return;
        }

        for (int i = begin; i < begin + 3; i++) {
            if (i >= len) {
                break;
            }

            if (residue * 3 < len - i) {
                continue;
            }

            if (judgeIpSegment(s, begin, i)) {
                String currentIpSegment = s.substring(begin, i + 1);
                path.addLast(currentIpSegment);

                dfs(s, len, i + 1, residue - 1, path, res);
                path.removeLast();
            }
        }
    }

    private boolean judgeIpSegment(String s, int left, int right) {
        int len = right - left + 1;
        if (len > 1 && s.charAt(left) == '0') {
            return false;
        }

        int res = 0;
        while (left <= right) {
            res = res * 10 + s.charAt(left) - '0';
            left++;
        }

        return res >= 0 && res <= 255;
    }


    // 四层循环
    public List<String> restoreIpAddresses3(String s) {
        List<String> result = new ArrayList<>();
        StringBuilder ip = new StringBuilder();

        for (int a = 1; a < 4; a++) {
            for (int b = 1; b < 4; b++) {
                for (int c = 1; c < 4; c++) {
                    for (int d = 1; d < 4; d++) {
                        /*
                         * 1、保障下面subString不会越界
                         * 2、保障截取的字符串与输入字符串长度相同
                         * //1、2比较好理解，3比较有意思
                         * 3、不能保障截取的字符串转成int后与输入字符串长度相同
                         * 如：字符串010010，a=1，b=1，c=1，d=3，对应字符串0，1，0，010
                         * 转成int后seg1=0，seg2=1，seg3=0，seg4=10
                         * //所以需要下面这处判断if (ip.length() == s.length() + 3)
                         */
                        if (a + b + c + d == s.length()) {
                            int seg1 = Integer.parseInt(s.substring(0, a));
                            int seg2 = Integer.parseInt(s.substring(a, a + b));
                            int seg3 = Integer.parseInt(s.substring(a + b, a + b + c));
                            int seg4 = Integer.parseInt(s.substring(a + b + c, a + b + c + d));
                            // 四个段数值满足0~255
                            if (seg1 <= 255 && seg2 <= 255 && seg3 <= 255 && seg4 <= 255) {
                                ip.append(seg1).append(".").append(seg2).append(".").
                                        append(seg3).append(".").append(seg4);
                                // 保障截取的字符串转成int后与输入字符串长度相同
                                if (ip.length() == s.length() + 3) {
                                    result.add(ip.toString());
                                }
                                ip.delete(0, ip.length());
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
