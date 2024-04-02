package SWEA;

import java.io.*;
import java.util.*;

/*
 * 제목
 * <숫자 만들기> D?
 * 링크
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeRZV6kBUDFAVH
 * 요약
 * 
 * 풀이
 * dfs
 */
public class swea_4008 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int minResult;
    static int maxResult;

    static int numCount; // 숫자 개수
    static int[] operator = new int[4]; // 연산자 +, -, *, / 개수
    static final char[] op = { '+', '-', '*', '/' };
    static int[] num;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; ++tc) {
            // 입력
            numCount = Integer.parseInt(br.readLine().trim()); // 숫자 개수
            st = new StringTokenizer(br.readLine().trim());
            for (int i = 0; i < 4; ++i) {
                operator[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine().trim());
            num = new int[numCount];
            for (int i = 0; i < numCount; ++i) {
                num[i] = Integer.parseInt(st.nextToken()); // 계산될 숫자들
            }

            // 풀이
            minResult = Integer.MAX_VALUE;
            maxResult = Integer.MIN_VALUE;
            dfs(0, num[0]);

            // 출력
            sb.append("#").append(tc).append(" ").append(maxResult - minResult).append("\n");

        }

        bw.write(sb.toString());
        bw.flush();
    }

    private static void dfs(int depth, int currentResult) {
        if (depth == numCount - 1) { // 숫자 개수-1 개만큼 연산자 선택 시 비교 후 종료
            minResult = Math.min(minResult, currentResult);
            maxResult = Math.max(maxResult, currentResult);
            return;
        }

        // 아직 연산자 더 선택해야 함
        for (int i = 0; i < 4; ++i) {
            if (operator[i] == 0)
                continue;
            if (i == 0) { // +
                operator[i]--;
                dfs(depth + 1, currentResult + num[depth + 1]);
                operator[i]++;
            } else if (i == 1) { // -
                operator[i]--;
                dfs(depth + 1, currentResult - num[depth + 1]);
                operator[i]++;
            } else if (i == 2) { // *
                operator[i]--;
                dfs(depth + 1, currentResult * num[depth + 1]);
                operator[i]++;
            } else { // /
                operator[i]--;
                dfs(depth + 1, currentResult / num[depth + 1]);
                operator[i]++;
            }
        }
    }
}