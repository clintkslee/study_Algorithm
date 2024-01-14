package SWEA.D2;

import java.util.Scanner;

public class swea_1288 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        int[] arr;
        int N, temp;
        for (int i=0; i<T; i++) {
            N = scanner.nextInt();
            temp = N;
            arr = new int[10]; // 0~9 숫자
            while(checkArr(arr, temp)) {
                temp += N;
            }
            // 출력
            System.out.println("#"+(i+1)+" "+temp);
        }
        scanner.close();
    
    }
    static boolean checkArr(int[] arr, int N) {
        int number = N;
        int length = 0;
        while(number>0) {
            length++;
            number /= 10;
        }      
        for(int i=0; i<length; i++) {
            arr[N%10] = 1;
            N /= 10;
        }
        for(int k: arr) {
            if(k==0)    return true;
        }
        return false; // 모든 칸이 1인 경우 모든 숫자 확보 됨
    }
}
