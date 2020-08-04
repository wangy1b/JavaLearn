package com.wyb.leetcode;

public class MySqrt {


    public static void main(String[] args) {
        int x = 2147395599;
        // System.out.println(mySqrt(x));
        System.out.println(mySqrt2(x));
        // System.out.println(mySqrtOfficial(x));

    }


    private static int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        double C = x, x0 = x;
        int i = 0;
        while (true) {
            double xi = 0.5 * (x0 + C / x0);
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
            i++;
        }
        System.out.println("times : " + i);

        return (int)x0;
    }

    private static int mySqrt2(int x) {
        int left = 0;
        int right = x;
        int res = 0;
        int i = 0;
        while ( left <= right ) {
            int mid = (left + right ) /2 ;
            // int mid = left + (right -left ) /2 ;
            if ( (long)mid * mid <= x) {
                res = mid;
                left = mid + 1 ;
            } else {
                right = mid - 1 ;
            }
            i++;
        }

        System.out.println("times : " + i);

        return res;
    }


    private static int mySqrtOfficial(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long)mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }
        }
        return ans;
    }
}
