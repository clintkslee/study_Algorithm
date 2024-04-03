package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
* <미세먼지 안녕!> G4
* 링크
* https://www.acmicpc.net/problem/17144
* 요약
* 1) 미세먼지 확산
* 2) 공기청정기 작동
* T초 후 방에 남아있는 미세먼지 총량 구하기
* 풀이
* BFS
*/
public class boj_17144 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static class Pos {
		int i, j;

		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "Pos [i=" + i + ", j=" + j + "]";
		}

	}

	static int R, C; // 방 크기 R*C
	static int T; // T초

	static int[][] room; // 방
	static int[][] temp; // 확산 값 저장

	static ArrayList<Pos> dust = new ArrayList<>(); // 먼지들 좌표
	static ArrayList<Pos> air = new ArrayList<>(); // 최대 크기 2

	static final int[] di = { -1, 1, 0, 0 }; // 상 하 좌 우
	static final int[] dj = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine().trim());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		room = new int[R + 1][C + 1];
		temp = new int[R + 1][C + 1];
		for (int i = 1; i <= R; ++i) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 1; j <= C; ++j) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if (room[i][j] == -1) {
					air.add(new Pos(i, j));
				}
				if (room[i][j] > 0) {
					if (room[i][j] / 5 > 0) // 4 이하의 먼지는 확산이 불가능
						dust.add(new Pos(i, j));
				}
			}
		}

		// 풀이
		int dustCount = bfs();
		// 출력
		bw.write(dustCount + "");
		bw.flush();
	}

	private static int bfs() {
		// 초기값 설정
		Queue<Pos> q = new ArrayDeque<>();
		for (Pos p : dust) {
			q.offer(p);
		}
		int time = 0;
		while (time < T) { // 시간 경과하거나 q 비면 끝
			// 1. 미세먼지 확산
			spread(q);
			// 2. 상부 공기청정기 가동
			blow(0);
			// 3. 하부 공기청정기 가동
			blow(1);
			// 확산 시킬 먼지들 offer
			for (int i = 1; i <= R; ++i) {
				for (int j = 1; j <= C; ++j) {
					if (room[i][j] > 4)
						q.offer(new Pos(i, j));

				}
			}
			time++;
		}
		return dustCount();
	}

	private static int dustCount() {
		int cnt = 0;
		for (int i = 1; i <= R; ++i) {
			for (int j = 1; j <= C; ++j) {
				if (room[i][j] > 0)
					cnt += room[i][j];
			}
		}
		return cnt;
	}

	private static void spread(Queue<Pos> q) {
		int pollCnt = q.size();
		for (int cnt = 0; cnt < pollCnt; ++cnt) {
			Pos curDust = q.poll();
			int spreadValue = room[curDust.i][curDust.j] / 5;
			int spreadCnt = 0; // 4방향 중 확산 가능 횟수
			for (int k = 0; k < 4; ++k) {
				int ni = curDust.i + di[k];
				int nj = curDust.j + dj[k];
				if (!isIn(ni, nj) || room[ni][nj] == -1)
					continue;
				spreadCnt++;
			}

			room[curDust.i][curDust.j] -= spreadValue * spreadCnt;
			for (int k = 0; k < 4; ++k) {
				int ni = curDust.i + di[k];
				int nj = curDust.j + dj[k];
				if (!isIn(ni, nj) || room[ni][nj] == -1)
					continue;
				temp[ni][nj] += spreadValue;
			}
		}
		for (int i = 1; i <= R; ++i) {
			for (int j = 1; j <= C; ++j) {
				if (temp[i][j] > 0) {
					room[i][j] += temp[i][j];
					temp[i][j] = 0;
				}
			}
		}
	}

	private static boolean isIn(int i, int j) {
		if (1 <= i && i <= R && 1 <= j && j <= C)
			return true;
		return false;
	}

	private static void blow(int k) {
		// 공기청정기 좌표 : k=0 상부공기청정기, k=1 하부공기청정기
		int iAir = air.get(k).i;
		int jAir = air.get(k).j; // 항상 1번 열에 설치되므로 jAir=1

		if (k == 0) {
			// 상부 반시계방향
			// 행 : 1~iAir, 열 : 1~C
			for (int i = iAir - 2; i >= 1; --i)
				room[i + 1][1] = room[i][1];
			for (int j = 2; j <= C; ++j)
				room[1][j - 1] = room[1][j];
			for (int i = 2; i <= iAir; ++i)
				room[i - 1][C] = room[i][C];
			for (int j = C; j > 2; --j)
				room[iAir][j] = room[iAir][j - 1];
			room[iAir][2] = 0;

		} else { // k==1
			// 하부 시계방향
			// 행 : iAir~R, 열 : 1~C
			for (int i = iAir + 2; i <= R; ++i)
				room[i - 1][1] = room[i][1];
			for (int j = 2; j <= C; ++j)
				room[R][j - 1] = room[R][j];
			for (int i = R; i >= iAir + 1; --i)
				room[i][C] = room[i - 1][C];
			for (int j = C; j > 2; --j)
				room[iAir][j] = room[iAir][j - 1];
			room[iAir][2] = 0;
		}
	}
}