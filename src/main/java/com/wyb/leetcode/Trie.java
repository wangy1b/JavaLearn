package com.wyb.leetcode;

/*

208. 实现 Trie (前缀树)
Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
这一数据结构有相当多的应用情景，例如自动补完和拼写检查。

请你实现 Trie 类：

Trie() 初始化前缀树对象。
void insert(String word) 向前缀树中插入字符串 word 。
boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。


示例：

输入
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
输出
[null, null, true, false, true, null, true]

解释
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // 返回 True
trie.search("app");     // 返回 False
trie.startsWith("app"); // 返回 True
trie.insert("app");
trie.search("app");     // 返回 True

https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 */
public class Trie {
    /***
     * 方法一：字典树
     Trie，又称前缀树或字典树，是一棵有根树，其每个节点包含以下字段：

     指向子节点的指针数组 children。对于本题而言，数组长度为 26，即小写英文字母的数量。
     此时 children[0] 对应小写字母 a，children[1] 对应小写字母 b，…，children[25] 对应小写字母 z。
     布尔字段 isEnd，表示该节点是否为字符串的结尾。
     插入字符串

     我们从字典树的根开始，插入字符串。对于当前字符对应的子节点，有两种情况：

     子节点存在。沿着指针移动到子节点，继续处理下一个字符。
     子节点不存在。创建一个新的子节点，记录在 children 数组的对应位置上，然后沿着指针移动到子节点，继续搜索下一个字符。
     重复以上步骤，直到处理字符串的最后一个字符，然后将当前节点标记为字符串的结尾。

     查找前缀

     我们从字典树的根开始，查找前缀。对于当前字符对应的子节点，有两种情况：

     子节点存在。沿着指针移动到子节点，继续搜索下一个字符。
     子节点不存在。说明字典树中不包含该前缀，返回空指针。
     重复以上步骤，直到返回空指针或搜索完前缀的最后一个字符。

     若搜索到了前缀的末尾，就说明字典树中存在该前缀。此外，若前缀末尾对应节点的 isEnd 为真，则说明字典树中存在该字符串。

     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/shi-xian-trie-qian-zhui-shu-by-leetcode-ti500/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     */

    private Trie[] children;
    private boolean isEnd;

    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }

    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private Trie searchPrefix(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        // trie.insert("apple");
        // trie.search("apple");   // 返回 True
        // System.out.println(trie.startsWith("apple"));   // 返回 True
        // System.out.println(trie.search("applea"));   // 返回 True
        // System.out.println(trie.startsWith("appled"));   // 返回 True
        // trie.search("app");     // 返回 False
        // trie.startsWith("app"); // 返回 True
        // trie.insert("app");
        // trie.search("app");     // 返回 True
        // =====

        // trie.insert("hello");
        // trie.search("hell");
        // trie.search("helloa");
        // trie.search("hello");
        // trie.startsWith("hell");
        // trie.startsWith("helloa");
        // trie.startsWith("hello");


        trie.insert("app");
        trie.insert("apple");
        trie.insert("beer");
        trie.insert("add");
        trie.insert("jam");
        trie.insert("rental");
        trie.search("apps");
        trie.search("app");
        trie.search("ad");
        trie.search("applepie");
        trie.search("rest");
        trie.search("jan");
        trie.search("rent");
        trie.search("beer");
        trie.search("jam");
        trie.search("apple");
        trie.startsWith("apple");
        trie.startsWith("apps");
        trie.startsWith("app");
        trie.startsWith("ad");
        trie.startsWith("applepie");
        trie.startsWith("rest");
        trie.startsWith("jan");
        trie.startsWith("rent");
        trie.startsWith("beer");
        trie.startsWith("jam");

    }
}
