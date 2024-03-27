package BOJ;

import java.io.*;
import java.util.*;

/*
 * 제목
 * <앱> G3
 * 링크
 * https://www.acmicpc.net/problem/7579
 * 요약
 * 최소 비용으로 M이상의 메모리 구하는 경우 찾기
 * 풀이
 * dp
 */
public class boj_7579 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, M; // N개의 앱, 필요한 메모리 M
	static int sum; // 비용의 합
	static int[][] app;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sum = 0;

		app = new int[N + 1][2]; // i번째 앱에 대하여 [i][0]=점유중인 메모리 m, [i][1]=비활성화 시 비용 c

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			app[i][0] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			app[i][1] = Integer.parseInt(st.nextToken());
			sum += app[i][1];
		}
		dp = new int[N + 1][sum + 1];

		// 풀이
		dp();
		for (int i = 0; i <= sum; i++) {
			if (dp[N][i] >= M) {
				sb.append(i + "");
				break;
			}
		}

		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	private static void dp() {
		for (int i = 0; i <= N; i++) {
			dp[i][0] = 0;
		}
		for (int j = 0; j <= sum; j++) {
			dp[0][j] = 0;
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= sum; j++) { // 재활성 비용이 0인 경우도 고려해줘야 한다.
				if (j < app[i][1]) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j - app[i][1]] + app[i][0], dp[i - 1][j]);
				}
			}
		}
	}
}
