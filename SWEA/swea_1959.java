package SWEA.D2;

import java.util.*;

public class swea_1959 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        int N, M;
        int sum;
        Vector<Integer> A = new Vector<>();
        Vector<Integer> B = new Vector<>();

        for(int i=0; i<T; i++) {
            sum=0;
            N = scanner.nextInt();
            M = scanner.nextInt();
            for(int j=0; j<N; j++) {
                A.add(scanner.nextInt());
            }
            for(int j=0; j<M; j++) {
                B.add(scanner.nextInt());
            }

            // 계산
            if(N == M) {
                for(int j=0; j<N; j++) {
                    sum += A.get(j)*B.get(j);
                }
            } else if(N > M) { // A 배열 > B 배열
                int shift = N - M;
                int temp;
                for(int j=0; j<=shift; j++) {
                    temp = 0;
                    for(int k=0; k<M; k++) {
                        temp += A.get(k+j)*B.get(k);
                    }
                    if(temp > sum) {
                        sum = temp;
                    }
                }

            } else { // A 배열 < B 배열
                int shift = M - N;
                int temp;
                for(int j=0; j<=shift; j++) {
                    temp = 0;
                    for(int k=0; k<N; k++) {
                        temp += A.get(k)*B.get(k+j);
                    }
                    if(temp > sum) {
                        sum = temp;
                    }
                }
            }

            // 출력
            System.out.println("#"+(i+1)+" "+sum);
            A.clear();
            B.clear();
        }
        scanner.close();
    }
}
