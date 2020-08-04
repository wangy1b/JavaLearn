package com.wyb.leetcode;

public class ClimbStairs {
    public static void main(String[] args) {

        int n = 45;
        // System.out.println(climbStairs(n));
        // System.out.println(Memo(n));
        // System.out.println(climbStairsDP1(n));
        System.out.println(climbStairsDP2(n));

    }

    private static int climbStairs(int n) {
        if (n <= 2) return n;
        int step1 = 1;
        int step2 = 2;
        for (int i = 3; i <= n; i++) {
            int temp = step1 + step2;
            step1 = step2;
            step2 = temp;
        }
        return step2;
    }

    private static int climbStairsRecursive(int n) {

        if (n <= 2) return n;
        return climbStairsRecursive(n-1) + climbStairsRecursive(n-2);
    }

    private static int Memo(int n){
        int memo[] = new int[n+1];
       return climbStairsMemo(n, memo);
    }

    private static int climbStairsMemo(int n, int[] memo) {
        if(memo[n] > 0) return memo[n];
        if (n <= 2) memo[n] = n;
        else memo[n] = climbStairsMemo(n-1, memo) + climbStairsMemo(n-2, memo);
        return memo[n];
    }


    private static int climbStairsDP1(int n) {
        if (n == 1) return n;
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i - 2];
        }
        return dp[n];

    }

    private static int climbStairsDP2(int n) {
        if (n <= 1) return n;
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;

    }




}
