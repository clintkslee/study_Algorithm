package SWEA;

import java.io.*;
import java.util.*;

/*
* 제목
* <수영대회 결승전> D4
* 링크
* https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AWKaG6_6AGQDFARV
* 요약
* 
* 풀이
* bfs
*/
public class swea_4193 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static class Pos {
		int i, j, time;

		public Pos(int i, int j, int time) {
			this.i = i;
			this.j = j;
			this.time = time;
		}
	}

	static int N; // 수영장 크기 N*N
	static int[][] arr;
	static int[][] vis;
	static int A, B; // 시작 위치 [A][B]
	static int C, D; // 종료 위치 [C][D]
	static int[] di = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dj = { 0, 0, -1, 1 };

	static int minTime;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; ++tc) {
			// 입력
			N = Integer.parseInt(br.readLine().trim());
			arr = new int[N][N]; // 수영장
			vis = new int[N][N]; // 방문 배열
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; ++j) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 시작 지점
			st = new StringTokenizer(br.readLine().trim());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			// 종료 지점
			st = new StringTokenizer(br.readLine().trim());
			C = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());

			// 풀이
			minTime = Integer.MAX_VALUE;
			bfs();

			// 출력
			sb.append("#").append(tc).append(" ").append(minTime).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static void bfs() {
		Queue<Pos> q = new ArrayDeque<>();
		for (int[] row : vis) { // 방문관리 배열 최대값으로 초기화
			Arrays.fill(row, 123456789);
		}
		vis[A][B] = 0;
		q.offer(new Pos(A, B, 0)); // 시작위치 [A][B], 경과 시간
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			if (cur.i == C && cur.j == D) {
				minTime = Math.min(cur.time, minTime);
			}
			int curTime = cur.time;

			// 사방탐색
			for (int i = 0; i < 4; ++i) {
				int ni = cur.i + di[i];
				int nj = cur.j + dj[i];
				if (!isIn(ni, nj)) // 범위 외
					continue;
				if (vis[ni][nj] < cur.time + 1)
					continue;
				if (arr[ni][nj] == 1) // 장애물이면 못감
					continue;
				// 이하는 지나갈 수 있는 상황
				if (arr[ni][nj] == 2) { // 소용돌이 살아있는 경우
					if (cur.time % 3 == 2) {
						vis[ni][nj] = curTime + 1;
						q.offer(new Pos(ni, nj, curTime + 1));
					} else {
						q.offer(new Pos(cur.i, cur.j, cur.time + 1));
					}
				}
				if(arr[ni][nj] == 0) {
					vis[ni][nj] = curTime + 1;
					q.offer(new Pos(ni, nj, curTime + 1));
				}
			}
		}
	}

	private static boolean isIn(int ni, int nj) {
		if (0 <= ni && ni < N && 0 <= nj && nj < N)
			return true;
		return false;
	}
}
