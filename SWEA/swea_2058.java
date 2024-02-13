package SWEA;

import java.util.Scanner;

public class swea_2058 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        int length = s.length();
        int sum = 0;
        for(int i=0; i<length; i++) {
            sum += s.charAt(i) - '0';
        }
        System.out.println(sum);
        scanner.close();
    }
}
