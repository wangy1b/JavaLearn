package com.wyb.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/***
 * 187. 重复的DNA序列
 所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。
 在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。

 示例 1：

 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 输出：["AAAAACCCCC","CCCCCAAAAA"]

 示例 2：
 输入：s = "AAAAAAAAAAAAA"
 输出：["AAAAAAAAAA"]


 提示：
 0 <= s.length <= 105
 s[i] 为 'A'、'C'、'G' 或 'T'

 https://leetcode-cn.com/problems/repeated-dna-sequences/
 */
public class RepeatedDNASequences {
    public List<String> findRepeatedDnaSequences(String s) {
        int L = 10, n = s.length();
        HashSet<String> seen = new HashSet(), output = new HashSet();

        // iterate over all sequences of length L
        for (int start = 0; start < n - L + 1; ++start) {
            String tmp = s.substring(start, start + L);
            if (seen.contains(tmp)) output.add(tmp);
            seen.add(tmp);
        }
        return new ArrayList<String>(output);
    }


    public static void main(String[] args) {
        RepeatedDNASequences re = new RepeatedDNASequences();
        //输入：
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> res = re.findRepeatedDnaSequences(s);
       // 输出：["AAAAACCCCC","CCCCCAAAAA"]
        System.out.println(res);
    }
}
