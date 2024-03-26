package BOJ;

import java.io.*;
import java.util.*;

/*
 * 제목
 * <평범한 배낭> G5
 * 링크
 * https://www.acmicpc.net/problem/12865
 * 요약
 * 배낭채우기 dp
 * 풀이
 * dp
 */
public class boj_12865 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, K; // 물품 수 N, 무게 제한 K
	static int[][] item;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		item = new int[N + 1][2]; // N개(1~n), [i][0] : 무게, [i][1] : 가치
		dp = new int[N + 1][K + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			item[i][0] = Integer.parseInt(st.nextToken()); // 무게
			item[i][1] = Integer.parseInt(st.nextToken()); // 가치
		}

		// 풀이
		dp();
		// 출력
		sb.append(dp[N][K]);
		bw.write(sb.toString());
		bw.flush();
	}

	private static void dp() {
		for (int i = 0; i <= N; i++)
			dp[i][0] = 0;
		for (int j = 0; j <= K; j++)
			dp[0][j] = 0;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				if (item[i][0] > j) { // 한도 무게 j일 때 i번째 물건 담을 수 없는 경우
					dp[i][j] = dp[i - 1][j];
				} else { // 한도 무게 j일 때 i번째 물건 담을 수 있는 경우
					dp[i][j] = Math.max(dp[i - 1][j - item[i][0]] + item[i][1], dp[i - 1][j]);
				}
			}
		}
	}
}
