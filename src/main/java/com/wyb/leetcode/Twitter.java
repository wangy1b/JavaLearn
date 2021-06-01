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


/***
 *  利用双hashmap + PriorityQueue 来解决：
 *  ·用hashmap保存每个用户的tweet及其发推时间（这里取得是nanotime，为了后续排序），userTweetsWithTime，
 *  ·用hashmap保存每个用户的关注人，userFollower
 *  ·每次follow和posttweet之前，检查userId是否存在，不存在就创建
 *  ·取最新tweet的方法getNewsFeed：
 *      1.先把userId对应的tweet及其发推时间的数组，加入PriorityQueue；
 *      2.再遍历他关注的每个人，找到关注人的tweet，同样将tweet及其发推时间的数组一次性加入PriorityQueue；
 *      3.利用PriorityQueue的小顶堆排序属性保留最大的10个tweet；
 *      4.再倒序插入结果的list中
 */
public class Twitter {

    // userId -> <[tweetId,timestamp],[]>
    private HashMap<Integer, Queue<Long[]>> userTweetsWithTime;
    // userId -> <userId,userId,>
    private HashMap<Integer, ArrayList<Integer>> userFollower;

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        userTweetsWithTime = new HashMap<>();
        userFollower = new HashMap<>();
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        // 用户userId不存在就创建
        addUser(userId);
        // 系统时间
        Long timeStamp = System.nanoTime();
        // 获取userId的tweet队列
        Queue<Long[]> tweetPQ = userTweetsWithTime.get(userId);
        if (!tweetPQ.contains(tweetId)) {
            tweetPQ.offer(new Long[]{Long.valueOf(tweetId), timeStamp});
        }
        userTweetsWithTime.put(userId, tweetPQ);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed.
     * Each item in the news feed must be posted by users who the user followed or by the user herself.
     * Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new LinkedList<>();

        if (userFollower.containsKey(userId)) {
            // userId关注的人
            ArrayList<Integer> followerList = userFollower.get(userId);
            // userId自己的tweet
            Queue<Long[]> PQ = userTweetsWithTime.get(userId);
            // 为了不消费
            // 用于排序，按timestamp顺序，最老（最小的）的时间在堆顶
            PriorityQueue<Long[]> tweetPQ = new PriorityQueue<>(new Comparator<Long[]>() {
                @Override
                // TweetsWithTime o1{tweet,timestamp}
                public int compare(Long[] o1, Long[] o2) {
                    return o1[1].compareTo(o2[1]);
                }
            });
            tweetPQ.addAll(PQ);
            // 获取关注人的tweet，并加入tweetPQ
            if (followerList.size() > 0) {
                Iterator<Integer> iterator = followerList.iterator();
                while (iterator.hasNext()) {
                    int followerUserId = iterator.next();
                    Queue<Long[]> tmp = userTweetsWithTime.get(followerUserId);
                    tweetPQ.addAll(tmp);
                }
            }
            // tweetPQ 大于10个，则把时间小的去掉
            while (tweetPQ.size() > 10) {
                tweetPQ.poll();
            }
            // 将最后10个加入res,
            // 注意出去的顺序，是最新的在前面，最老的在后面。所以linkedList用得是在头部追加
            while (!tweetPQ.isEmpty()) {
                Long[] t = tweetPQ.poll();
                // System.out.println(t[1] + " > " + t[0]);
                res.add(0, Long.valueOf(t[0]).intValue());
            }
        }
        return res;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        // followerId 和 followeeId 不存在就创建，并添加其tweet空表
        addUser(followerId);
        addUser(followeeId);
        // 当前followerId已经存在,就获取他的关注人list
        ArrayList<Integer> followerList = userFollower.get(followerId);
        if (!followerList.contains(followeeId)) {
            followerList.add(followeeId);
        }
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (userFollower.containsKey(followerId)) {
            ArrayList<Integer> followerList = userFollower.get(followerId);
            if (followerList.contains(followeeId)) {
                followerList.remove(followerList.indexOf(followeeId));
            }
        }
    }


    private void addUser(int userId) {
        if (!userFollower.containsKey(userId)) {
            userFollower.put(userId, new ArrayList<Integer>());
        }
        if (!userTweetsWithTime.containsKey(userId)) {
            userTweetsWithTime.put(userId, new ArrayDeque<>());
        }
    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();

        // // 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
        // twitter.postTweet(1, 5);
        //
        // // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
        // System.out.println(twitter.getNewsFeed(1));
        //
        // // 用户1关注了用户2.
        // twitter.follow(1, 2);
        //
        // // 用户2发送了一个新推文 (推文id = 6).
        // twitter.postTweet(2, 6);
        //
        // // 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
        // // 推文id6应当在推文id5之前，因为它是在5之后发送的.
        // System.out.println(twitter.getNewsFeed(1));
        //
        // // 用户1取消关注了用户2.
        // twitter.unfollow(1, 2);
        //
        // // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
        // // 因为用户1已经不再关注用户2.
        // System.out.println(twitter.getNewsFeed(1));

        // ================

        // twitter.postTweet(1,1);
        // System.out.println(twitter.getNewsFeed(1));
        // twitter.follow(2,1);
        // System.out.println(twitter.getNewsFeed(2));
        // twitter.unfollow(2,1);
        // System.out.println(twitter.getNewsFeed(2));

        // ==================


        // twitter.follow(1, 5);
        // System.out.println(twitter.getNewsFeed(1));

        // ========================

        System.out.println(twitter.getNewsFeed(1));


    }
}
