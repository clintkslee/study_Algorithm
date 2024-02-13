package SWEA;

import java.util.*;

public class swea_12712 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        int N, M;
        int[][] array;
        int sum, temp_plus, temp_x;
        for(int i=0; i<T; i++) {
            sum = 0;
            temp_plus = 0;
            temp_x = 0;
            N = scanner.nextInt();
            M = scanner.nextInt();
            array = new int[N][N];

            // 파리 입력
            for(int j=0; j<N; j++) {
                for(int k=0; k<N; k++) {
                    array[j][k] = scanner.nextInt();
                }
            }

            // 파리 계산
            for(int j=0; j<N; j++) {
                for(int k=0; k<N; k++) {
                    // 전범위 탐색하면서 sum 최대 구하기
                    temp_plus = getTempPlus(array, j, k, N, M);
                    temp_x = getTempX(array, j, k, N, M);
                    sum = Math.max(sum, Math.max(temp_plus, temp_x));
                }
            }

            // 출력
            System.out.println("#"+(i+1)+" "+sum);
        }
        scanner.close();
    }

    static int getTempPlus(int[][] array, int j, int k, int N, int M) {
        int temp_plus = 0;
        temp_plus += array[j][k];

        for(int i=1; i<M; i++) { // 상
            if(j-i >= 0) {
                temp_plus += array[j-i][k];
            }
        }
        for(int i=1; i<M; i++) { // 하
            if(j+i < N) {
                temp_plus += array[j+i][k];
            }
        }
        for(int i=1; i<M; i++) { // 좌
            if(k-i >= 0) {
                temp_plus += array[j][k-i];
            }
        }
        for(int i=1; i<M; i++) { // 우
            if(k+i < N) {
                temp_plus += array[j][k+i];
            }
        }
        return temp_plus;
    }

    static int getTempX(int[][] array, int j, int k, int N, int M) {
        int temp_x = 0;
        temp_x += array[j][k];
        
        for(int i=1; i<M; i++) { // 좌상
            if(j-i >= 0 && k-i >= 0) {
                temp_x += array[j-i][k-i];
            }
        }          
        for(int i=1; i<M; i++) { // 좌하
            if(j+i < N && k-i >= 0) {
                temp_x += array[j+i][k-i];
            }
        }            
        for(int i=1; i<M; i++) { // 우상
            if(j-i >= 0 && k+i < N) {
                temp_x += array[j-i][k+i];
            }
        }  
        for(int i=1; i<M; i++) { // 우하
            if(j+i < N && k+i < N) {
                temp_x += array[j+i][k+i];
            }
        }     
        return temp_x;
    }    
}
