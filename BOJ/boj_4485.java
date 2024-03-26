package BOJ;

import java.io.*;
import java.util.*;

/*
 * 제목
 * <녹색 옷 입은 애가 젤다지?> G4
 * 링크
 * https://www.acmicpc.net/problem/4485
 * 요약
 * 초록색 옷 입은 애는 링크입니다.
 * 풀이
 * bfs + 해당 위치까지의 최소비용 저장
 */
public class boj_4485 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static class Pos {
		int i;
		int j;

		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	static int N; // 동굴 크기 N*N

	static int[] dy = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int[] dx = { 0, 1, 0, -1 };

	static int[][] cave;
	static int[][] vis;

	public static void main(String[] args) throws IOException {
		// 입력
		int tc = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			cave = new int[N][N];
			vis = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 풀이
			int minCoin = bfs();
			// 출력
			sb.append("Problem ").append(tc).append(": ").append(minCoin).append("\n");
			tc++;
		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static int bfs() {
		for (int i = 0; i < N; i++) {
			Arrays.fill(vis[i], -1);
		}
		vis[0][0] = cave[0][0];
		Pos start = new Pos(0, 0); // 시작 위치
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(start);
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			for (int k = 0; k < 4; k++) {
				int ni = cur.i + dy[k];
				int nj = cur.j + dx[k];
				if (noPath(ni, nj))
					continue;
				if (vis[ni][nj] == -1) { // 한 번도 방문된 적 없는 곳인 경우 그냥 큐에 삽입
					vis[ni][nj] = vis[cur.i][cur.j] + cave[ni][nj];
					q.offer(new Pos(ni, nj));
				} else { // 방문한 적 있는 곳이면 비용 비교해서 더 작은 경우에만 큐에 삽입
					if (vis[ni][nj] <= vis[cur.i][cur.j] + cave[ni][nj])
						continue;
					vis[ni][nj] = vis[cur.i][cur.j] + cave[ni][nj];
					q.offer(new Pos(ni, nj));
				}
			}
		}
		return vis[N - 1][N - 1];
	}

	private static boolean noPath(int ni, int nj) {
		// 범위 판정
		if (ni < 0 || ni >= N || nj < 0 || nj >= N)
			return true;
		return false;
	}
}
