package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
* <키 순서> G4
* 링크
* https://www.acmicpc.net/problem/2458
* 요약
* 모든 노드에 대해 다른 노드들 까지의 경로 있는 지 확인
* 임의의 노드 k에서 출발하여 경로 없는 노드에 대해 반대의 경우 도달할 수 있는 지 확인
* 풀이
* 플로이드 워셜
*/
public class boj_2458_플로이드워셜 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N; // 학생 수
	static int M; // 비교 횟수
	static int[][] arr; // 인접행렬
	static final int INF = 12345678;

	static int[] cnt;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N + 1][N + 1]; // 0번 인덱스 제외

		// init start
		for (int i = 0; i <= N; i++) {
			Arrays.fill(arr[i], INF);
		}

		for (int i = 0; i <= N; i++) {
			arr[i][i] = 0;
		}
		// init end

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			arr[from][to] = 1; // 경로 있음 표시
		}

		// 풀이 : 플로이드 워셜
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i == j)
						continue;
					arr[i][j] = Math.min(arr[i][k] + arr[k][j], arr[i][j]);
				}
			}
		}

		// 자기 위치를 안다 == 자신이 갈 수 있는 노드 개수 + 자기 자신까지 도착할 수 있는 노드 개수 == (N - 1)
		cnt = new int[N + 1];
		// 자신이 갈 수 있는 노드 개수
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j)
					continue;
				if (arr[i][j] != INF)
					cnt[i]++;
			}
		}

		// 자기 자신까지 도착할 수 있는 노드 개수
		for (int j = 1; j <= N; j++) {
			for (int i = 1; i <= N; i++) {
				if (i == j)
					continue;
				if (arr[i][j] != INF)
					cnt[j]++;
			}
		}

		// 자기 위치를 아는 노드 개수 세기
		for (int i = 1; i <= N; i++) {
			if (cnt[i] == N - 1)
				ans++;
		}

		bw.write(ans + "");
		bw.flush();
	}
}
