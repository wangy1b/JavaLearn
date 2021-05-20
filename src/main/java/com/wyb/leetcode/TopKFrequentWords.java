package com.wyb.leetcode;

import java.util.*;

/**
 * 692. 前K个高频单词
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 * <p>
 * 示例 1：
 * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * 输出: ["i", "love"]
 * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
 * 注意，按字母顺序 "i" 在 "love" 之前。
 * <p>
 * <p>
 * 示例 2：
 * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * 输出: ["the", "is", "sunny", "day"]
 * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
 * 出现次数依次为 4, 3, 2 和 1 次。
 * <p>
 * <p>
 * 注意：
 * 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
 * 输入的单词均由小写字母组成。
 * <p>
 * <p>
 * 扩展练习：
 * 尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。
 * https://leetcode-cn.com/problems/top-k-frequent-words/
 */
public class TopKFrequentWords {

    public static void main(String[] args) {

        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 3;

        // String[] words = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        // int k = 4;
        TopKFrequentWords topKF = new TopKFrequentWords();
        List<String> res = topKF.topKFrequent(words, k);

        System.out.println(res);
    }

    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> occurrences = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            occurrences.put(words[i], occurrences.getOrDefault(words[i], 0) + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<Map.Entry<String, Integer>>(k,
                new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                        int cmp = o1.getValue().compareTo(o2.getValue());
                        if (cmp == 0) {
                            return - o1.getKey().compareTo(o2.getKey());
                        }
                        return cmp;
                    }
                });

        // todo coding leetcode
        for (Map.Entry<String, Integer> entry : occurrences.entrySet()) {
            if (heap.size() >= k) {
                if (entry.getValue() >= heap.element().getValue()) {
                    //  (entry.getKey().compareTo( heap.element().getKey())) < 0
                    heap.poll();
                    heap.offer(entry);
                }
            } else {
                heap.offer(entry);
            }
        }

        String[] res = new String[k];
        for (int i = 0; i < k; i++) {
            res[k-1-i] = heap.poll().getKey();
        }
        return Arrays.asList(res);
    }
}
