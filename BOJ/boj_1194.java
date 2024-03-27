package BOJ;

import java.io.*;
import java.util.*;

/*
 * 제목
 * <달이 차오른다, 가자.> G1
 * 링크
 * https://www.acmicpc.net/problem/1194
 * 요약
 * 열쇠 보유 상태를 저장
 * 풀이
 * 3차원 bfs
 */
public class boj_1194 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static class Pos {
		int i;
		int j;
		int key; // 키 보유 상태 00000000 -> gefdcba
		int count; // 이동거리

		public Pos(int i, int j, int key, int count) {
			this.i = i;
			this.j = j;
			this.key = key;
			this.count = count;
			// 1 : a
			// 2 : b
			// 3 : ab
			// 4 : c
			// ...
		}

		@Override
		public String toString() {
			return "[" + i + "][" + j + "]" + "key : " + key;
		}

	}

	static int N, M; // 세로크기 N, 가로크기 M
	static int[][] maze;
	static boolean[][][] visit; // key보유 상태, i j

	static int[] di = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dj = { 0, 0, -1, 1 };

	static int minCount = Integer.MAX_VALUE;
	static Pos startPos;

	static HashMap<Integer, Integer> key = new HashMap<>();
	static {
		key.put((int) 'A', 1);
		key.put((int) 'B', 1 << 1);
		key.put((int) 'C', 1 << 2);
		key.put((int) 'D', 1 << 3);
		key.put((int) 'E', 1 << 4);
		key.put((int) 'F', 1 << 5);
		key.put((int) 'a', 1);
		key.put((int) 'b', 1 << 1);
		key.put((int) 'c', 1 << 2);
		key.put((int) 'd', 1 << 3);
		key.put((int) 'e', 1 << 4);
		key.put((int) 'f', 1 << 5);
	}

	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new int[N][M];
		visit = new boolean[64][N][M]; // key 6개까지 가능하므로 key 보유 상태 표현 : 0~63

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				maze[i][j] = str.charAt(j);
				if (maze[i][j] == '0')
					startPos = new Pos(i, j, 0, 0); // 시작 좌표 (i, j), 열쇠 상태, 이동거리
			}
		}
		// 풀이
		bfs();
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	private static void bfs() {
		Queue<Pos> q = new ArrayDeque<>();
		q.add(startPos);
		visit[startPos.key][startPos.i][startPos.j] = true; // 시작 위치 visit 체크

		while (!q.isEmpty()) {
			Pos cur = q.poll();
			for (int k = 0; k < 4; k++) {
				int ni = cur.i + di[k];
				int nj = cur.j + dj[k];
				if (!isIn(ni, nj)) { // 범위 외인 경우 continue
					continue;
				} else if (visit[cur.key][ni][nj]) { // 방문했던 곳인 경우 continue
					continue;
				} else if (maze[ni][nj] == '#') { // 벽('#') 인 경우 continue
					continue;
				} else if (maze[ni][nj] == '.' || maze[ni][nj] == '0') { // 길('.') 인 경우 (시작 위치도 길로 처리)
					visit[cur.key][ni][nj] = true;
					q.add(new Pos(ni, nj, cur.key, cur.count + 1));
				} else if (maze[ni][nj] == '1') { // 도착지('1') 인 경우 minCount 와 비교 후 갱신
					minCount = Math.min(minCount, cur.count + 1);
				} else if ('A' <= maze[ni][nj] && maze[ni][nj] <= 'F') { // 문인 경우 ('A'~'F')
					int curKey = cur.key;
					int door = maze[ni][nj];
					if ((key.get(door) & curKey) > 0) { // 키가 있는 경우
						visit[cur.key][ni][nj] = true;
						q.add(new Pos(ni, nj, cur.key, cur.count + 1));
					} else { // 키가 없는 경우
						;
					}
				} else if ('a' <= maze[ni][nj] && maze[ni][nj] <= 'f') { // 열쇠인 경우 ('a'~'f')
					visit[cur.key][ni][nj] = true;
					int curGetKey = key.get(maze[ni][nj]);
					int nKey = cur.key | curGetKey;
					visit[nKey][ni][nj] = true;
					q.add(new Pos(ni, nj, nKey, cur.count + 1));
				}
			}
		}
		if (minCount == Integer.MAX_VALUE)
			sb.append("-1");
		else
			sb.append(minCount);

	}

	private static boolean isIn(int i, int j) {
		if (0 <= i && i < N && 0 <= j && j < M)
			return true;
		return false;
	}
}