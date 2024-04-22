package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <가장 긴 증가하는 부분 수열> S1
 * 링크
 * https://www.acmicpc.net/problem/11053
 * 요약
 * 
 * 풀이
 * LIS
 */

public class boj_11053 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N; // 수열 A의 크기
    static int[] A; // 수열 A
    static int[] LIS; // dp 배열

    public static void main(String[] args) throws IOException {
        // 입력
        N = Integer.parseInt(br.readLine().trim());
        A = new int[N];
        LIS = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 풀이
        for (int i = 0; i < N; i++) {
            LIS[i] = 1;
            for (int j = 0; j < i; j++) {
                if (A[i] > A[j] && LIS[i] < LIS[j] + 1) {
                    LIS[i] = LIS[j] + 1;
                }
            }
        }

        // 출력
        Arrays.sort(LIS);
        bw.write(LIS[N - 1] + "");
        bw.flush();
    }
}
