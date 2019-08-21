package demo;

import java.util.*;

//力扣笔试专用类
class Solution {

    public static void main(String[] args) {
    }

    public int[] countBits(int num) {
        int [] result = new int[num+1];
        for (int i = 1; i <= num; i++) {
            result[i] = (i & 1) + result[i >> 1];
        }

        return result;
    }
}

