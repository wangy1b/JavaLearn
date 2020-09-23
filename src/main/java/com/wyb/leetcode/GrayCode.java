package com.wyb.leetcode;

import java.util.*;

/*

89. 格雷编码
格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。

给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。

格雷编码序列必须以 0 开头。



示例 1:

输入: 2
输出: [0,1,3,2]
解释:
00 - 0
01 - 1
11 - 3
10 - 2

对于给定的 n，其格雷编码序列并不唯一。
例如，[0,2,3,1] 也是一个有效的格雷编码序列。

00 - 0
10 - 2
11 - 3
01 - 1
示例 2:

输入: 0
输出: [0]
解释: 我们定义格雷编码序列必须以 0 开头。
     给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
     因此，当 n = 0 时，其格雷编码序列为 [0]。

https://leetcode-cn.com/problems/gray-code/

 */
public class GrayCode {

    public static void main(String[] args) {
        int n = 4;
        // System.out.println(grayCode(n));
        // List<Integer> list = grayCode(n);
        List<Integer> list = grayCode1(n);
        // System.out.println(list);
        printFmt(list, n);
    }

    // todo 生成的只是子集,需要求最大子集
    private static List<Integer> grayCode(int n) {
        ArrayList<Integer> res = new ArrayList<>();
        if (n == 0) {
            res.add(0);
            return res;
        }
        Integer cnt = (int) (Math.pow(2, n));
        Integer pre = 0, curr = 1;
        res.add(pre);
        while (res.size() < cnt && curr < cnt) {
            if (Integer.bitCount(pre ^ curr) == 1 && !res.contains(curr)) {
                res.add(curr);
                pre = curr;
                curr = 1;
            } else
                curr++;
        }
        return res;
    }

    private static void printFmt(List<Integer> list,int n){
        String[] tmp = new String[n];
        String fmt = new String(new char[n]).replace("\0", "0");
        for (int l : list) {
            String s = Integer.toBinaryString(l);
            int len = s.length();
            String res = "";
            if (len < n)
                res = fmt.substring(len) + s;
            else
                res = s.substring(len - n,len);
            System.out.println(  res  +" : " + l);
        }
    }


    /*

    关键是要找到规律
    n = 0, [0]
    n = 1, [0,1] //新的元素1，为0+2^0
    n = 2, [0,1,3,2] // 新的元素[3,2]为[0,1]->[1,0]后分别加上2^1
    n = 3, [0,1,3,2,6,7,5,4] // 新的元素[6,7,5,4]为[0,1,3,2]->[2,3,1,0]后分别加上2^2->[6,7,5,4]

    作者：chengm15
    链接：https://leetcode-cn.com/problems/gray-code/solution/zhi-xing-yong-shi-nei-cun-xiao-hao-ji-bai-liao-1-5/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */

    private static List<Integer> grayCode1(int n) {
        ArrayList<Integer> res = new ArrayList<>();
        int shift = 1;
        while(n >= 0){
            if(res.size() == 0){
                res.add(0);
            }else{
                for(int i = shift-1; i >= 0; --i){
                    res.add(res.get(i) + shift);
                }
                shift *= 2;
            }
            --n;
        }
        return res;
    }
}
