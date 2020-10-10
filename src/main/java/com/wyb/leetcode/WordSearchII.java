package com.wyb.leetcode;

import com.sun.org.apache.xml.internal.utils.Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*

212. 单词搜索 II
给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。

示例:

输入:
words = ["oath","pea","eat","rain"] and board =
[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]

输出: ["eat","oath"]
说明:
你可以假设所有输入都由小写字母 a-z 组成。

提示:

你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。
什么样的数据结构可以有效地执行这样的操作？
散列表是否可行？为什么？
前缀树如何？如果你想学习如何实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。

https://leetcode-cn.com/problems/word-search-ii/solution/

 */


class TrieNode {
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    String word = null;
    public TrieNode() {}
}

/*

方法一：使用前缀树的回溯
这个问题实际上是一个简化的纵横填字游戏，在这个游戏中，单词的解已经被嵌入了一些无关字母。我们要做的就是把它们划掉。

直观地说，为了划掉所有潜在的单词，总体策略是一个接一个地迭代单元格，然后从每个单元格沿着它的四个潜在方向的走，找到匹配的单词。

当我们在黑板上徘徊时，若我们知道这不会发现新单词时，我们会停止探索。

有人可能已经猜到了我们用来解决这个问题的方法。是的，它是回溯，这将是解决方案的主干。构造一个回溯的解决方案是相当简单的。

解决这个问题的关键在于我们如何从字典中找到单词的匹配项。直观地说，可以使用 hashset 数据结构（例如Python 中的 set()）。

然而，在回溯过程中，人们会更经常地遇到这样的问题：是否存在任何包含特定前缀的单词，而不是是否有一个字符串作为单词存在于字典中。因为如果我们知道给定前缀的字典中不存在任何单词匹配，那么我们就不需要进一步探索某个方向。而这，将大大减少探测空间，从而提高回溯算法的性能。

能够查找前缀的数据结构叫 Trie，于 hashset 比较。Trie 不仅可以检查一个单词，还可以立即找到共享给定前缀的单词。事实证明，数据结构的选择（Trie 与 hashset）可能以排名前 5% 或后 5% 的解决方案结束。

这里我们展示了一个由单词列表构建的 Trie 示例。如下图所示，在所表示的节点处，我们将知道字典中至少有两个前缀为 d 的单词。

作者：LeetCode
链接：https://leetcode-cn.com/problems/word-search-ii/solution/dan-ci-sou-suo-ii-by-leetcode/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

 */
public class WordSearchII {
    public static void main(String[] args) {
        // String[] words = {"oath","pea","eat","rain"};
        String[] words = {"oath","oathf"};
        char[][] board = {
                        {'o', 'a', 'a', 'n'},
                        {'e', 't', 'a', 'e'},
                        {'i', 'h', 'k', 'r'},
                        {'i', 'f', 'l', 'v'},
                };

        List<String> list =  findWords(board,words);
        System.out.println(list);

    }

    private static char[][] _board = null;
    private static ArrayList<String> _result = new ArrayList<String>();

    private static List<String> findWords(char[][] board, String[] words) {

        // Step 1). Construct the Trie
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;

            for (Character letter : word.toCharArray()) {
                if (node.children.containsKey(letter)) {
                    node = node.children.get(letter);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(letter, newNode);
                    node = newNode;
                }
            }
            node.word = word;  // store words in Trie
        }

        _board = board;
        // Step 2). Backtracking starting for each cell in the board
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                if (root.children.containsKey(board[row][col])) {
                    backtracking(row, col, root);
                }
            }
        }

        return _result;
    }

    private static void backtracking(int row, int col, TrieNode parent) {
        Character letter = _board[row][col];
        TrieNode currNode = parent.children.get(letter);

        // check if there is any match
        if (currNode.word != null) {
            _result.add(currNode.word);
            currNode.word = null;
        }

        // mark the current letter before the EXPLORATION
        _board[row][col] = '#';

        // explore neighbor cells in around-clock directions: up, right, down, left
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};
        for (int i = 0; i < 4; ++i) {
            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];
            if (newRow < 0 || newRow >= _board.length || newCol < 0
                    || newCol >= _board[0].length) {
                continue;
            }
            if (currNode.children.containsKey(_board[newRow][newCol])) {
                backtracking(newRow, newCol, currNode);
            }
        }

        // End of EXPLORATION, restore the original letter in the board.
        _board[row][col] = letter;

        // Optimization: incrementally remove the leaf nodes
        if (currNode.children.isEmpty()) {
            parent.children.remove(letter);
        }
    }


}
