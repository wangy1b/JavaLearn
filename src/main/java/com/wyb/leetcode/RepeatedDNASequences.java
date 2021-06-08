package com.wyb.leetcode;

import java.util.*;


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

        System.out.println(re.myFindRepeatedDnaSequences(s));
    }

    // Rabin-Karp
    public List<String> myFindRepeatedDnaSequences(String s) {
        int L = 10, n = s.length();
        HashSet<Long> seen = new HashSet();
        HashSet<String> output = new HashSet();
        if (n <= L) return new ArrayList<>();
        HashMap<Character,Integer> map = new HashMap<>();
        map.put('A',0);
        map.put('C',1);
        map.put('G',2);
        map.put('T',3);
        long sum = 0;
        long windMax = 1;
        long overFlow = 1L<<32;
        int rk = 4;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int a = map.get(c);
            nums[i] = a;
            if (i < L) {
                sum = (sum * rk)%overFlow + a;
                if (i > 0)
                    windMax = windMax * rk;
            }

        }
        seen.add(sum);


        for (int start = 1; start < n - L + 1; ++start) {
            int lastFirstC = nums[start-1];
            int currentLastC = nums[start + L - 1];
            sum = ((sum - (lastFirstC * windMax)%overFlow ) * rk)%overFlow + currentLastC;
            if (seen.contains(sum)) {
                output.add(s.substring(start, start + L));
            }
            seen.add(sum);
        }
        return new ArrayList<>(output);
    }

    // Rabin-Karp
    public List<String> findRepeatedDnaSequencesOfficial(String s) {
        int L = 10, n = s.length();
        if (n <= L) return new ArrayList();

        // rolling hash parameters: base a
        int a = 4, aL = (int)Math.pow(a, L);

        // convert string to array of integers
        Map<Character, Integer> toInt = new
                HashMap() {{put('A', 0); put('C', 1); put('G', 2); put('T', 3); }};
        int[] nums = new int[n];
        for(int i = 0; i < n; ++i) nums[i] = toInt.get(s.charAt(i));

        int h = 0;
        Set<Integer> seen = new HashSet();
        Set<String> output = new HashSet();
        // iterate over all sequences of length L
        for (int start = 0; start < n - L + 1; ++start) {
            // compute hash of the current sequence in O(1) time
            if (start != 0)
                h = h * a - nums[start - 1] * aL + nums[start + L - 1];
                // compute hash of the first sequence in O(L) time
            else
                for(int i = 0; i < L; ++i) h = h * a + nums[i];
            // update output and hashset of seen sequences
            if (seen.contains(h)) output.add(s.substring(start, start + L));
            seen.add(h);
        }
        return new ArrayList<String>(output);
    }

    // 位运算
    public List<String> findRepeatedDnaSequencesOfficial1(String s) {
        int L = 10, n = s.length();
        if (n <= L) return new ArrayList();

        // rolling hash parameters: base a
        int a = 4, aL = (int)Math.pow(a, L);

        // convert string to array of integers
        Map<Character, Integer> toInt = new
                HashMap() {{put('A', 0); put('C', 1); put('G', 2); put('T', 3); }};
        int[] nums = new int[n];
        for(int i = 0; i < n; ++i) nums[i] = toInt.get(s.charAt(i));

        int bitmask = 0;
        Set<Integer> seen = new HashSet();
        Set<String> output = new HashSet();
        // iterate over all sequences of length L
        for (int start = 0; start < n - L + 1; ++start) {
            // compute bitmask of the current sequence in O(1) time
            if (start != 0) {
                // left shift to free the last 2 bit
                bitmask <<= 2;
                // add a new 2-bits number in the last two bits
                bitmask |= nums[start + L - 1];
                // unset first two bits: 2L-bit and (2L + 1)-bit
                bitmask &= ~(3 << 2 * L);
            }
            // compute hash of the first sequence in O(L) time
            else {
                for(int i = 0; i < L; ++i) {
                    bitmask <<= 2;
                    bitmask |= nums[i];
                }
            }
            // update output and hashset of seen sequences
            if (seen.contains(bitmask)) output.add(s.substring(start, start + L));
            seen.add(bitmask);
        }
        return new ArrayList<String>(output);
    }

}
