package BOJ;

import java.io.*;
import java.util.*;

/*
 * 제목
 * <토마토> G5
 * 링크
 * https://www.acmicpc.net/problem/7569
 * 요약
 * 토마토 익히기
 * 풀이
 * bfs
 */
public class boj_1107 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static class Pos {
		int z, y, x;

		public Pos(int z, int y, int x) {
			this.z = z;
			this.y = y;
			this.x = x;
		}
	}

	static int M, N, H; // 가로칸 수 M, 세로 칸 수 N, 층 H
	static int[][][] arr;
	static Queue<Pos> q = new ArrayDeque<>();
	static int[] dz = { -1, 1, 0, 0, 0, 0 }; // 상 하 전 후 좌 우
	static int[] dy = { 0, 0, -1, 1, 0, 0 };
	static int[] dx = { 0, 0, 0, 0, -1, 1 };
	static int day = -1;
	static int ripeTomatoCnt = 0;

	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new int[H][N][M]; // 층-세로-가로
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				for (int m = 0; m < M; m++) {
					arr[h][n][m] = Integer.parseInt(st.nextToken());
					if (arr[h][n][m] == 1) {
						q.offer(new Pos(h, n, m));
						ripeTomatoCnt++;
					} else if (arr[h][n][m] == -1) {
						ripeTomatoCnt++;
					}
				}
			}
		}
		// // 입력 검증
		// for(int h=0; h<H; h++) {
		// System.out.println(h+"층");
		// for(int n=0; n<N; n++) {
		// System.out.println(Arrays.toString(arr[h][n]));
		// }
		// System.out.println("===============");
		// }
		// 풀이
		bfs();
		// 출력
		if (ripeTomatoCnt == H * N * M)
			sb.append(day);
		else
			sb.append("-1");
		bw.write(sb.toString());
		bw.flush();
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			int tomatoCnt = q.size();
			day++;
			for (int i = 0; i < tomatoCnt; i++) {
				Pos t = q.poll();
				for (int d = 0; d < 6; d++) {
					int nz = t.z + dz[d];
					int ny = t.y + dy[d];
					int nx = t.x + dx[d];
					if (!isIn(nz, ny, nx))
						continue;
					if (arr[nz][ny][nx] == 0) {
						arr[nz][ny][nx] = 1;
						ripeTomatoCnt++;
						q.offer(new Pos(nz, ny, nx));
					}
				}
			}
		}
	}

	private static boolean isIn(int z, int y, int x) {
		return 0 <= z && z < H && 0 <= y && y < N && 0 <= x && x < M;
	}
}
