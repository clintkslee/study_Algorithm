package SWEA;

import java.io.*;
import java.util.*;

/*
 * 제목
 * <장훈이의 높은 선반> D4
 * 링크
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV2b7Yf6ABcBBASw
 * 요약
 * 
 * 풀이
 * dfs, 부분집합
 */
public class swea_1486 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int peopleCnt, shelfHeight;
    static int[] peopleHeight;
    static int minHeight;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; ++tc) {
            // 입력
            st = new StringTokenizer(br.readLine().trim());
            peopleCnt = Integer.parseInt(st.nextToken());
            shelfHeight = Integer.parseInt(st.nextToken());
            peopleHeight = new int[peopleCnt];
            st = new StringTokenizer(br.readLine().trim());
            for (int i = 0; i < peopleCnt; ++i)
                peopleHeight[i] = Integer.parseInt(st.nextToken());
            // 풀이
            minHeight = Integer.MAX_VALUE;
            dfs(0, 0); // depth, sum of heights
            // 출력
            sb.append("#").append(tc).append(" ").append(minHeight - shelfHeight).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private static void dfs(int depth, int sum) {
        if (sum > minHeight)
            return;
        if (depth == peopleCnt) {
            if (sum < shelfHeight)
                return;
            minHeight = Math.min(minHeight, sum);
            return;
        }
        dfs(depth + 1, sum + peopleHeight[depth]);
        dfs(depth + 1, sum);
    }
}
