package com.wyb.leetcode;
/*

编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。

示例 1：

输入：00000000000000000000000000001011
输出：3
解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
示例 2：

输入：00000000000000000000000010000000
输出：1
解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
示例 3：

输入：11111111111111111111111111111101
输出：31
解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
 

提示：

请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/number-of-1-bits
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
public class HammingWeight {
    public static void main(String[] args) {
        System.out.println(hammingWeight(11));
        // System.out.println(hammingWeight(4294967293));
    }

    // you need to treat n as an unsigned value

    /*

    <<:左移  左边最高位丢弃，右边补齐0
    >>:右移  最高位是0，左边补齐0;最高为是1，左边补齐1
    >>>:无符号右移  无论最高位是0还是1，左边补齐0

     */
    public static int hammingWeight(int n) {
        int cnt = 0;
        for (int i = 31; i > 0 && n != 0; i--) {
            System.out.print("Binary Radix:" + Integer.toBinaryString(n));
            System.out.println("  last Data:" + (((n & 1) << i) >>> i) );
            if ( ((n & 1) << i) >>> i == 1 ) cnt ++;
            n >>= 1;
        }
       return cnt;
    }

    /*
    这个方法比较直接。我们遍历数字的 32 位。如果某一位是 1，将计数器加一。

    我们使用 位掩码 来检查数字的第ith位。一开始，掩码 m=1 因为 1 的二进制表示是

    0000 0000 0000 0000 0000 0000 0000 0001

    显然，任何数字跟掩码 11 进行逻辑与运算，都可以让我们获得这个数字的最低位。检查下一位时，我们将掩码左移一位。

    0000 0000 0000 0000 0000 0000 0000 0010

    并重复此过程。

    作者：LeetCode
    链接：https://leetcode-cn.com/problems/number-of-1-bits/solution/wei-1de-ge-shu-by-leetcode/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */
    public static int hammingWeightOfficial1(int n) {
        int bits = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                bits++;
            }
            mask <<= 1;
        }
        return bits;
    }
    /*

    我们可以把前面的算法进行优化。我们不再检查数字的每一个位，而是不断把数字最后一个 1 反转，并把答案加一。
    当数字变成 0 的时候偶，我们就知道它没有 1 的位了，此时返回答案。

    这里关键的想法是对于任意数字 n ，将 n 和 n−1 做与运算，会把最后一个 1 的位变成 0 。为什么？考虑 n 和 n−1 的二进制表示。

    作者：LeetCode
    链接：https://leetcode-cn.com/problems/number-of-1-bits/solution/wei-1de-ge-shu-by-leetcode/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public static int hammingWeightOfficial2(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n &= (n - 1);
        }
        return sum;
    }
}
