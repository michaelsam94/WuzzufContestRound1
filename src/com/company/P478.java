package com.company;

import java.util.Scanner;

public class P478 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int maxPoint=-1,t,n;


        String name;
        int points;

        t = in.nextInt();

        String[] maxTeam = new String[t];

        for (int i=0; i<t; i++){
            n = in.nextInt();
            for (int j=0; j < n; j++){
                name = in.next();
                points = in.nextInt();

                if(points > maxPoint) {
                    maxPoint = points;
                    maxTeam[i] = name;
                }
            }
            maxPoint = -1;
        }


        for (String team : maxTeam){
            System.out.println(team);
        }



    }
}
