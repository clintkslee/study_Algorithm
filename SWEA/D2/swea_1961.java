package SWEA.D2;

import java.util.Scanner;
import java.util.Vector;

public class swea_1961 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        int N;
        int[][] array;
        Vector<Integer> N90 = new Vector<>();
        Vector<Integer> N180 = new Vector<>();
        Vector<Integer> N270 = new Vector<>();
        for(int i=0; i<T; i++) {
            // N*N 배열 입력
            N = scanner.nextInt();
            array = new int[N][N];
            for(int j=0; j<N; j++) {
                for(int k=0; k<N; k++) {
                    array[j][k] = scanner.nextInt();
                }
            }
            // 90 회전
            for(int j=0; j<N; j++) {
                for(int k=N-1; k>=0; k--) {
                    N90.add(array[k][j]); 
                }
            }      
            // 180 회전
            for(int j=N-1; j>=0; j--) {
                for(int k=N-1; k>=0; k--) {
                    N180.add(array[j][k]); 
                }
            }    
            // 270 회전
            for(int j=N-1; j>=0; j--) {
                for(int k=0; k<N; k++) {
                    N270.add(array[k][j]); 
                }
            }             
            // 출력
            System.out.println("#"+(i+1));
            for(int j=0; j<N; j++) {
                for(int k=0; k<N; k++) {
                    System.out.print(N90.get(k+j*N));
                }
                System.out.print(" ");
                for(int k=0; k<N; k++) {
                    System.out.print(N180.get(k+j*N));
                }
                System.out.print(" ");
                for(int k=0; k<N; k++) {
                    System.out.print(N270.get(k+j*N));
                }
                System.out.println();                
            }
            N90.clear();
            N180.clear();
            N270.clear();
        }
        scanner.close();
    }
}
