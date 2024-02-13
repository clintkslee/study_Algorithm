package SWEA;

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
            inputTemp = scanner.nextInt();  // TC 번호
            for(int j=0; j<1000; j++) {
                inputTemp = scanner.nextInt();
                arr[inputTemp]++;
            }
            scoreTemp=arr[0];
            indexTemp=0;
            for(int j=0; j<101; j++) {
                if(scoreTemp <= arr[j]) {
                    scoreTemp = arr[j];
                    indexTemp = j;
                }
            }
            System.out.println("#"+(i+1)+" "+indexTemp);
        }
        scanner.close();
    }
}
