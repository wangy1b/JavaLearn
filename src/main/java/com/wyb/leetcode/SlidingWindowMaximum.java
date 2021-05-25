package com.wyb.leetcode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 239. 滑动窗口最大值
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回滑动窗口中的最大值。
 * <p>
 * 示例 1：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * 示例 2：
 * 输入：nums = [1], k = 1
 * 输出：[1]
 * <p>
 * 示例 3：
 * 输入：nums = [1,-1], k = 1
 * 输出：[1,-1]
 * <p>
 * 示例 4：
 * 输入：nums = [9,11], k = 2
 * 输出：[11]
 * <p>
 * 示例 5：
 * 输入：nums = [4,-2], k = 2
 * 输出：[4]
 * <p>
 * 提示：
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 * <p>
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 */
public class SlidingWindowMaximum {
    public static void main(String[] args) {
        // int[] nums = {1,3,-1,-3,5,3,6,7};
        // int k = 3;
        // int[] nums = {1,-1};
        // int k = 1;

        // int[] nums = {9,11};
        // int k = 2;
        int[] nums = {2, 3, 2, 1, 2, 3, 2};
        int k = 3;
        SlidingWindowMaximum s = new SlidingWindowMaximum();
        int[] res = s.maxSlidingWindow(nums, k);
        for (int re : res) {
            System.out.print(re + " ");
        }
        System.out.println();

        int[] res2 = s.maxSlidingWindow2(nums, k);
        for (int re2 : res2) {
            System.out.print(re2 + " ");
        }
        System.out.println();
    }

    // 超出时间限制
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k > nums.length) return new int[]{};
        // 最大值
        PriorityQueue<Integer> max_queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -o1.compareTo(o2);
            }
        });
        Queue<Integer> slide_queue = new LinkedList<>();

        int len = nums.length;
        int[] res = new int[len];
        int last_idx = 0;
        for (int i = 0; i < nums.length; i++) {
            // 出 slide_queue & max_queue
            if (slide_queue.size() == k) {
                int ele = slide_queue.poll();
                max_queue.remove(ele);
            }
            // 进 slide_queue & max_queue
            if (slide_queue.size() < k) {
                slide_queue.add(nums[i]);
                max_queue.add(nums[i]);
            }
            // 进结果
            if (i >= k - 1)
                res[last_idx++] = max_queue.peek();
        }
        return Arrays.copyOfRange(res, 0, last_idx);
    }

    // 最初一组一起加入 超出时间限制
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (k > nums.length) return new int[]{};
        int len = nums.length;
        int[] slide_arr = new int[k];

        int res_len = len + 1 - k;
        int[] res = new int[res_len];
        int last_idx = 0;

        // 最大值
        PriorityQueue<Integer> max_queue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return - o1.compareTo(o2);
            }
        });


        // int[] data = {1,2,3,4,5,6,7,8,9,10};
        //
        // // To boxed array
        // Integer[] what = Arrays.stream( data ).boxed().toArray( Integer[]::new );
        // Integer[] ever = IntStream.of( data ).boxed().toArray( Integer[]::new );
        //
        // // To boxed list
        // List<Integer> you  = Arrays.stream( data ).boxed().collect( Collectors.toList() );
        // List<Integer> like = IntStream.of( data ).boxed().collect( Collectors.toList() );

        // 一次性加入
        System.arraycopy(nums,0,slide_arr,0, k);
        List<Integer> list = Arrays.stream(slide_arr).boxed().collect(Collectors.toList());
        //the method adds Array to the List
        max_queue.addAll(list);
        res[last_idx++] = max_queue.peek();

        for (int i = 1; i < res_len; i++) {
            int left_idx = k - 1 + i;
            int ele = slide_arr[0];
            System.arraycopy(slide_arr,1,slide_arr,0, k-1);
            slide_arr[k-1] = nums[left_idx];

            max_queue.remove(ele);
            max_queue.add(nums[left_idx]);

            // 进结果
            res[last_idx++] = max_queue.peek();
        }
        return res;
    }

    //Official 1
    // 每当我们向右移动窗口时，我们就可以把一个新的元素放入优先队列中，此时堆顶的元素就是堆中所有元素的最大值。
    // 然而这个最大值可能并不在滑动窗口中，在这种情况下，这个值在数组 nums 中的位置出现在滑动窗口左边界的左侧。
    // 因此，当我们后续继续向右移动窗口时，这个值就永远不可能出现在滑动窗口中了，我们可以将其永久地从优先队列中移除。
    // 我们不断地移除堆顶的元素，直到其确实出现在滑动窗口中。此时，堆顶元素就是滑动窗口中的最大值。
    // 为了方便判断堆顶元素与滑动窗口的位置关系，我们可以在优先队列中存储二元组(num,index)，
    // 表示元素 num 在数组中的下标为index。
    //
    // 作者：LeetCode-Solution
    // 链接：https://leetcode-cn.com/problems/sliding-window-maximum/solution/hua-dong-chuang-kou-zui-da-zhi-by-leetco-ki6m/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int[] maxSlidingWindowOfficial1(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });

        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            // 最大值左边的数据，因为最大值的存在，所以其他值都不可能成为最大值，所以可以删除
            // 可以消除大根堆的数据，提高效率
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }

    // 方法二：单调队列
    // 我们可以顺着方法一的思路继续进行优化。
    // 当滑动窗口向右移动时，我们需要把一个新的元素放入队列中。
    // 为了保持队列的性质，我们会不断地将新的元素与队尾的元素相比较，如果前者大于等于后者，
    // 那么队尾的元素就可以被永久地移除，我们将其弹出队列。
    // 我们需要不断地进行此项操作，直到队列为空或者新的元素小于队尾的元素。
    //
    // 由于队列中下标对应的元素是严格单调递减的，因此此时队首下标对应的元素就是滑动窗口中的最大值。
    // 但与方法一中相同的是，此时的最大值可能在滑动窗口左边界的左侧，并且随着窗口向右移动，它永远不可能出现在滑动窗口中了。
    // 因此我们还需要不断从队首弹出元素，直到队首元素在窗口中为止。
    //
    // 为了可以同时弹出队首和队尾的元素，我们需要使用双端队列。满足这种单调性的双端队列一般称作「单调队列」。
    //
    // 作者：LeetCode-Solution
    // 链接：https://leetcode-cn.com/problems/sliding-window-maximum/solution/hua-dong-chuang-kou-zui-da-zhi-by-leetco-ki6m/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int[] maxSlidingWindowOfficial2(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }

    // 方法三：分块 + 预处理
    // 这种方法与稀疏表（Sparse Table）非常类似，感兴趣的读者可以自行查阅资料进行学习。
    public int[] maxSlidingWindowOfficial3(int[] nums, int k) {
        int n = nums.length;
        int[] prefixMax = new int[n];
        int[] suffixMax = new int[n];
        for (int i = 0; i < n; ++i) {
            if (i % k == 0) {
                prefixMax[i] = nums[i];
            }
            else {
                prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            if (i == n - 1 || (i + 1) % k == 0) {
                suffixMax[i] = nums[i];
            } else {
                suffixMax[i] = Math.max(suffixMax[i + 1], nums[i]);
            }
        }

        int[] ans = new int[n - k + 1];
        for (int i = 0; i <= n - k; ++i) {
            ans[i] = Math.max(suffixMax[i], prefixMax[i + k - 1]);
        }
        return ans;
    }

}
