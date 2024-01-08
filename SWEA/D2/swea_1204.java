package SWEA.D2;

import java.util.Arrays;
import java.util.Scanner;

public class swea_1204 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        int[] arr = new int[101];
        int inputTemp;
        int scoreTemp, indexTemp;
        for(int i=0; i<T; i++) {
            for(int j=0; j<101; j++) {
                arr[j]=0;
            } 
            for(int j=0; j<1000; j++) {
                inputTemp = scanner.nextInt();
                arr[inputTemp]++;
            }
            scoreTemp=arr[100];
            indexTemp=100;
            for(int j=99; j>=0; j--) {
                if(scoreTemp < arr[j])
                    indexTemp=j;
            }
            System.out.println("#"+(i+1)+" "+indexTemp);
        }
    }
}
