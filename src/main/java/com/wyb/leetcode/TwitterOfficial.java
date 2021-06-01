package com.wyb.leetcode;

import java.util.*;



/***
 * 355. 设计推特
 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近十条推文。
 你的设计需要支持以下的几个功能：
 postTweet(userId, tweetId): 创建一条新的推文
 getNewsFeed(userId): 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
 follow(followerId, followeeId): 关注一个用户
 unfollow(followerId, followeeId): 取消关注一个用户

 示例:
 Twitter twitter = new Twitter();

 // 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
 twitter.postTweet(1, 5);

 // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
 twitter.getNewsFeed(1);

 // 用户1关注了用户2.
 twitter.follow(1, 2);

 // 用户2发送了一个新推文 (推文id = 6).
 twitter.postTweet(2, 6);

 // 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
 // 推文id6应当在推文id5之前，因为它是在5之后发送的.
 twitter.getNewsFeed(1);

 // 用户1取消关注了用户2.
 twitter.unfollow(1, 2);

 // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
 // 因为用户1已经不再关注用户2.
 twitter.getNewsFeed(1);

 *
 * https://leetcode-cn.com/problems/design-twitter/
 */

/**
 *
 * 方法一：哈希表 + 链表
 思路和算法

 根据题意我们知道，对于每个推特用户，我们需要存储他关注的用户 Id，以及自己发的推文 Id 的集合，
 为了使每个操作的复杂度尽可能的低，我们需要根据操作来决定存储这些信息的数据结构。
 注意，由于题目中没有说明用户的 Id 是否连续，所以我们需要用一个以用户 Id 为索引的哈希表来存储用户的信息。

 对于操作 3 和操作 4，我们只需要用一个哈希表存储，即可实现插入和删除的时间复杂度都为 O(1)。

 对于操作 1 和操作 2，由于操作 2 要知道此用户关注的人和用户自己发出的最近十条推文，因此我们可以考虑对每个用户用链表存储发送的推文。
 每次创建推文的时候我们在链表头插入，这样能保证链表里存储的推文的时间是从最近到最久的。
 那么对于操作 2，问题其实就等价于有若干个有序的链表，我们需要找到它们合起来最近的十条推文。
 由于链表里存储的数据都是有序的，所以我们将这些链表进行线性归并即可得到最近的十条推文。
 这个操作与 23.合并K个排序链表 基本等同。

 如果我们直接照搬「合并K个排序链表」的解法来进行合并，那么无疑会造成空间的部分浪费，
 因为这个题目不要求你展示用户的所有推文，所以我们只要动态维护用户的链表，
 存储最近的 recentMax 个推文 Id 即可（题目中的 recentMax 为 10）。
 那么对于操作 1，当发现链表的节点数等于 recentMax 时，我们按题意删除链表末尾的元素，
 再插入最新的推文 Id。对于操作 2，在两个链表进行线性归并的时候，
 只要已合并的数量等于 recentMax，代表已经找到这两个链表合起来后最近的 recentMax 条推文，直接结束合并即可。

 作者：LeetCode-Solution
 链接：https://leetcode-cn.com/problems/design-twitter/solution/she-ji-tui-te-by-leetcode-solution/
 来源：力扣（LeetCode）
 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 */
public class TwitterOfficial {
    private class Node {
        // 哈希表存储关注人的 Id
        Set<Integer> followee;
        // 用链表存储 tweetId
        LinkedList<Integer> tweet;

        Node() {
            followee = new HashSet<Integer>();
            tweet = new LinkedList<Integer>();
        }
    }

    // getNewsFeed 检索的推文的上限以及 tweetId 的时间戳
    private int recentMax, time;
    // tweetId 对应发送的时间
    private Map<Integer, Integer> tweetTime;
    // 每个用户存储的信息
    private Map<Integer, Node> user;

    public TwitterOfficial() {
        time = 0;
        recentMax = 10;
        tweetTime = new HashMap<Integer, Integer>();
        user = new HashMap<Integer, Node>();
    }

    // 初始化
    public void init(int userId) {
        user.put(userId, new Node());
    }

    public void postTweet(int userId, int tweetId) {
        if (!user.containsKey(userId)) {
            init(userId);
        }
        // 达到限制，剔除链表末尾元素
        if (user.get(userId).tweet.size() == recentMax) {
            user.get(userId).tweet.remove(recentMax - 1);
        }
        user.get(userId).tweet.addFirst(tweetId);
        tweetTime.put(tweetId, ++time);
    }

    public List<Integer> getNewsFeed(int userId) {
        LinkedList<Integer> ans = new LinkedList<Integer>();
        for (int it : user.getOrDefault(userId, new Node()).tweet) {
            ans.addLast(it);
        }
        for (int followeeId : user.getOrDefault(userId, new Node()).followee) {
            if (followeeId == userId) { // 可能出现自己关注自己的情况
                continue;
            }
            LinkedList<Integer> res = new LinkedList<Integer>();
            int tweetSize = user.get(followeeId).tweet.size();
            Iterator<Integer> it = user.get(followeeId).tweet.iterator();
            int i = 0;
            int j = 0;
            int curr = -1;
            // 线性归并
            if (j < tweetSize) {
                curr = it.next();
                while (i < ans.size() && j < tweetSize) {
                    if (tweetTime.get(curr) > tweetTime.get(ans.get(i))) {
                        res.addLast(curr);
                        ++j;
                        if (it.hasNext()) {
                            curr = it.next();
                        }
                    } else {
                        res.addLast(ans.get(i));
                        ++i;
                    }
                    // 已经找到这两个链表合起来后最近的 recentMax 条推文
                    if (res.size() == recentMax) {
                        break;
                    }
                }
            }
            for (; i < ans.size() && res.size() < recentMax; ++i) {
                res.addLast(ans.get(i));
            }
            if (j < tweetSize && res.size() < recentMax) {
                res.addLast(curr);
                for (; it.hasNext() && res.size() < recentMax;) {
                    res.addLast(it.next());
                }
            }
            ans = new LinkedList<Integer>(res);
        }
        return ans;
    }

    public void follow(int followerId, int followeeId) {
        if (!user.containsKey(followerId)) {
            init(followerId);
        }
        if (!user.containsKey(followeeId)) {
            init(followeeId);
        }
        user.get(followerId).followee.add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        user.getOrDefault(followerId, new Node()).followee.remove(followeeId);
    }

}
