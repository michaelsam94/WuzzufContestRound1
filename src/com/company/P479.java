package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class P479 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t, n, m;
        int numOfQualified;
        int[] contestants;

        t = in.nextInt();

        for (int i = 0; i < t; i++) {
            n = in.nextInt();
            m = in.nextInt();

            contestants = new int[n];
            for (int j = 0; j < n; j++) {
                contestants[j] = in.nextInt();
            }
            Arrays.sort(contestants);
            numOfQualified = 0;
            if(m > 0){
                int lastQualifiedContestant = -1;
                for (int j = contestants.length - 1; j >= 0; j--) {
                    if(numOfQualified < m && contestants[j] !=0){
                        lastQualifiedContestant = j;
                        numOfQualified++;
                    } else if(numOfQualified >= m && contestants[lastQualifiedContestant] == contestants[j]){
                        numOfQualified++;
                    }
                }
            }
            System.out.println(numOfQualified);
        }
    }
}
