package SWEA;

import java.util.Scanner;

public class swea_1936 {
    final static int SCISSORS = 1;
    final static int ROCK = 2;
    final static int PAPER = 3;
    public static void main(String[] args) {

        int A, B;
        Scanner sc = new Scanner(System.in);
        A = sc.nextInt();
        B = sc.nextInt();
    
        if(A==SCISSORS && B==PAPER || A==ROCK && B==SCISSORS || A==PAPER && B==ROCK) {
            System.out.println("A");
        }
        else if(B==SCISSORS && A==PAPER || B==ROCK && A==SCISSORS || B==PAPER && A==ROCK) {
            System.out.println("B");
        } 
        sc.close();
    }
}
