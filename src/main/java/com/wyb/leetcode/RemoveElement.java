package com.wyb.leetcode;

public class RemoveElement {
    public static void main(String[] args) {
        // int[] nums = new int[] {0,0,1,1,1,2,2,3,3,4};
        int[] nums = new int[] {0,1,2,2,3,0,4,2};
        System.out.println(removeElement(nums,2));
        printArr(nums);
    }


    private static int removeElement(int[] nums, int val){
        int j = 0;
        for (int i = nums.length -1 ; i >= 0 ; i--) {
            if (nums[i] != val) {
                j++;
            } else {
                moveForward(nums,i);
            }
        }
        return j ;
    }


    private static void moveForward(int[] arr, int idx) {
        int temp = arr[idx];
        while (idx < arr.length -1) {
            arr[idx] = arr[idx+1];
            idx++;
        }
        arr[idx] = temp;
    }

    private static void printArr(int[] arr) {
        System.out.print("结果为:");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
