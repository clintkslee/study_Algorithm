package SWEA;

import java.io.*;
import java.util.*;

/*
* 제목
* <보급로> D4
* 링크
* https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15QRX6APsCFAYD
* 요약
* 
* 풀이
* BFS
*/
public class swea_1249 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();

	static class Pos {
		int i, j, dist;

		public Pos(int i, int j, int dist) {
			this.i = i;
			this.j = j;
			this.dist = dist;
		}
	}

	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	static int N;
	static int[][] arr;
	static int[][] vis;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			// 입력
			N = Integer.parseInt(br.readLine().trim());
			arr = new int[N][N];
			for (int i = 0; i < N; ++i) {
				String str = br.readLine().trim();
				for (int j = 0; j < N; ++j) {
					arr[i][j] = Integer.parseInt(str.charAt(j)+"");
				}
			}

			// 풀이
			bfs(tc);
		}
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	private static void bfs(int testCase) {
		Queue<Pos> q = new ArrayDeque<>();
		vis = new int[N][N];
		for (int i = 0; i < N; ++i)
			Arrays.fill(vis[i], -1);
		vis[0][0] = 0;
		Pos start = new Pos(0, 0, vis[0][0]);
		q.offer(start);
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			for (int k = 0; k < 4; ++k) {
				int ni = cur.i + di[k];
				int nj = cur.j + dj[k];
				if (!isIn(ni, nj))
					continue;
				if (vis[ni][nj] == -1) { // 방문한 적 없을 때
					vis[ni][nj] = cur.dist + arr[ni][nj];
					q.offer(new Pos(ni, nj, vis[ni][nj]));
				} else { // 방문한 적 있을 때
					if (vis[ni][nj] <= cur.dist + arr[ni][nj])
						continue;
					vis[ni][nj] = cur.dist + arr[ni][nj];
					q.offer(new Pos(ni, nj, vis[ni][nj]));
				}
			}
		}
		sb.append("#").append(testCase).append(" ").append(vis[N-1][N-1]).append("\n");

	}

	private static boolean isIn(int i, int j) {
		if (0 <= i && i < N && 0 <= j && j < N)
			return true;
		return false;
	}
}
