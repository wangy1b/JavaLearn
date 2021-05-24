package com.wyb.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

public class RevealCardsInIncreasingOrder {

    /***
     *  按递增顺序显示卡牌
     牌组中的每张卡牌都对应有一个唯一的整数。你可以按你想要的顺序对这套卡片进行排序。
     最初，这些卡牌在牌组里是正面朝下的（即，未显示状态）。
     现在，重复执行以下步骤，直到显示所有卡牌为止：
     从牌组顶部抽一张牌，显示它，然后将其从牌组中移出。
     如果牌组中仍有牌，则将下一张处于牌组顶部的牌放在牌组的底部。
     如果仍有未显示的牌，那么返回步骤 1。否则，停止行动。
     返回能以递增顺序显示卡牌的牌组顺序。
     答案中的第一张牌被认为处于牌堆顶部。

     示例：

     输入：[17,13,11,2,3,5,7]
     输出：[2,13,3,11,5,17,7]
     解释：
     我们得到的牌组顺序为 [17,13,11,2,3,5,7]（这个顺序不重要），然后将其重新排序。
     重新排序后，牌组以 [2,13,3,11,5,17,7] 开始，其中 2 位于牌组的顶部。
     我们显示 2，然后将 13 移到底部。牌组现在是 [3,11,5,17,7,13]。
     我们显示 3，并将 11 移到底部。牌组现在是 [5,17,7,13,11]。
     我们显示 5，然后将 17 移到底部。牌组现在是 [7,13,11,17]。
     我们显示 7，并将 13 移到底部。牌组现在是 [11,17,13]。
     我们显示 11，然后将 17 移到底部。牌组现在是 [13,17]。
     我们展示 13，然后将 17 移到底部。牌组现在是 [17]。
     我们显示 17。
     由于所有卡片都是按递增顺序排列显示的，所以答案是正确的。

     提示：

     1 <= A.length <= 1000
     1 <= A[i] <= 10^6
     对于所有的 i != j，A[i] != A[j]

     https://leetcode-cn.com/problems/reveal-cards-in-increasing-order/
     */

    public static void main(String[] args) {
        int[] deck = {17, 13, 11, 2, 3, 5, 7};
        RevealCardsInIncreasingOrder r = new RevealCardsInIncreasingOrder();
        int[] res = r.deckRevealedIncreasing(deck);
        System.out.println(r.checkOrder(res));
    }

    /**
     * 思路和算法

     直接模拟从牌组中取牌的过程就可以了。
     举个例子，如果从牌组中以 [0, 2, 4, ...] 的顺序取牌，
     我们只需要把最小的牌放在下标为 0 的地方，
     第二小的牌放在下标为 2 的地方，
     第三小的牌放在下标为 4 的地方，
     依次类推即可。

     作者：LeetCode
     链接：https://leetcode-cn.com/problems/reveal-cards-in-increasing-order/solution/an-di-zeng-shun-xu-xian-shi-qia-pai-by-leetcode/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     * @param deck
     * @return
     */
    public int[] deckRevealedIncreasing(int[] deck) {
        int N = deck.length;
        Deque<Integer> index = new LinkedList();
        for (int i = 0; i < N; ++i)
            index.add(i);

        int[] ans = new int[N];
        Arrays.sort(deck);
        for (int card: deck) {
            //第一位
            ans[index.pollFirst()] = card;
            //为了错位，这位丢给最后
            if (!index.isEmpty())
                index.add(index.pollFirst());
        }

        return ans;
    }

    boolean checkOrder(int[] deck) {
        boolean res = true;
        int len = deck.length;
        int[] checkArr = new int[len];
        System.arraycopy(deck, 0, checkArr, 0, len);
        Arrays.sort(checkArr);

        int[] outArr = new int[len];

        for (int i = 0; i < len - 1; i++) {
            outArr[i] = deck[i];
            int next = i + 1;
            int tail = len - 1;
            // 数组当前值放最后一位，其他前移一位
            if (next < tail)
                rotateArr(deck, next);
            // 最后一位赋值
            else
                outArr[tail] = deck[tail];
        }


        for (int i = 0; i < len - 1; i++) {
            if (checkArr[i] != outArr[i])
                return false;
        }

        return res;
    }

    void rotateArr(int[] arr, int idx) {
        int len = arr.length;
        int num = len - 1 - (idx + 1) + 1;
        int temp = arr[idx];
        System.arraycopy(arr, idx + 1, arr, idx, num);
        arr[len - 1] = temp;
    }
}
