package com.example.demo.test;

import java.util.Arrays;

public class PairsOfCircle {
    public static void main(String[] args) {
        PairsOfCircle pairsOfCircle = new PairsOfCircle();

        System.out.println(pairsOfCircle.solution(new int[]{4, 2, 5, 8, 7, 3, 7}));
        System.out.println(2);
        System.out.println(pairsOfCircle.solution(new int[]{14, 21, 16, 35, 22}));
        System.out.println(1);
        System.out.println(pairsOfCircle.solution(new int[]{5,5,5,5,5,5}));
        System.out.println(3);
    }
    public int solution(int[] A) {
        int temp = A[0];
        int length = A.length;
        A = Arrays.copyOf(A, length + 1);
        A[length] = temp;
        int firstSum = utilFunc(0, A.length-2, A);
        int secondSum = utilFunc(1, A.length-1, A);

        return Math.max(firstSum, secondSum);
    }

    private int utilFunc(int start, int end, int[] A) {
        int sum =0;
        for (int i = start; i+1<= end; i++) {
            if ((A[i] + A[i+1]) % 2 == 0) {
                sum++;
                i++;
            }
        }
        return sum;
    }
}