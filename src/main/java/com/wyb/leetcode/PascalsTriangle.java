package com.wyb.leetcode;

import java.util.LinkedList;
import java.util.List;

public class PascalsTriangle {
    public static void main(String[] args) {
        // System.out.println(generate(0));
        System.out.println(getRow(3));
    }


    private static List<Integer> generateCurrentRow(List<Integer> lastRows) {
        System.out.println("lastRows : " + lastRows);
        List<Integer> list = new LinkedList<>();
        // 第一个值
        list.add(1);
        for (int i = 0; i < lastRows.size() - 1; i++) {
            list.add(lastRows.get(i) + lastRows.get(i+1));
        }
        // 最后一个值
        list.add(1);
        return list;
    }


    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> results = new LinkedList<>();
        // if (numRows == 0) return results;
        if (numRows == 1) {
            List<Integer> currentRow = new LinkedList<>();
            currentRow.add(1);
            results.add(currentRow);
            return results;
        }
        List<List<Integer>> preRows = generate(numRows-1);
        List<Integer> lastRow = preRows.get(numRows-2);
        results.addAll(preRows);
        List<Integer> currentRow = generateCurrentRow(lastRow);
        results.add(currentRow);
        return results;
    }


    public static List<Integer> getRow(int rowIndex) {
        // if (rowIndex == 0) return new LinkedList<>();
        if (rowIndex == 0) {
            List<Integer> currentRow = new LinkedList<>();
            currentRow.add(1);
            return currentRow;
        }
        return generateCurrentRow(getRow(rowIndex-1));
    }

}
