package com.wyb.leetcode;

import java.util.HashMap;

/*

给定一个正整数，返回它在 Excel 表中相对应的列名称。

例如，

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB
    ...
示例 1:

输入: 1
输出: "A"
示例 2:

输入: 28
输出: "AB"
示例 3:

输入: 701
输出: "ZY"

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/excel-sheet-column-title
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


 */
public class ExcelSheetColumnTitle {
    public static void main(String[] args) {
        // System.out.println(convertToTitle(28));
        System.out.println(convertToTitle(702));
        // System.out.println(convertToTitle(702));
        // System.out.println(convertToTitle(1));
        // System.out.println(convertToTitle(52));
    }

    private static String convertToTitle(int n) {
        if (n <= 0) return null;
        StringBuffer stringBuffer = new StringBuffer();

        int r = n % 26 == 0 ? 26 : n % 26;
        int l = (n - r) / 26;

        if (l > 26) {
            stringBuffer.insert(0, convertToTitle(l));
        } else {
            String ls = l == 0 ? "" : Character.toString((char) (l + 64));
            stringBuffer.append(ls);
        }
        String rs = r == 0 ? "" :Character.toString ((char) (r + 64));
        stringBuffer.append(rs);
        return stringBuffer.toString();

    }
}
