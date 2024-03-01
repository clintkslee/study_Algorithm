package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <말이 되고픈 원숭이> G3
 * 링크
 * https://www.acmicpc.net/problem/1600
 * 요약
 * "특수이동 K번 + 사방향 이동"으로 [][0][0] 에서 [][H-1][W-1] 까지 이동 시 최소 횟수 
 * 풀이
 * 특수 이동 사용 여부를 층으로 저장 : [k][h][w] 는 [h][w] 위치에서 k번 더 특수이동 가능
 * 3차원 bfs
 */
public class boj_1600 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static class Pos {
		int jump, y, x;

		public Pos(int jump, int y, int x) {
			this.jump = jump;
			this.y = y;
			this.x = x;
		}
		
	}

	static int K; // 특수이동  횟수 K
	static int W, H; // 격자 가로 크기 W, 격자 세로 크기 H
	static int[][][] map; // [k][y][x]
	static boolean[][][] visited;
	
	// 상, 우, 하, 좌, 1시, 2시, 4시, 5시, 7시, 8시, 10시, 11시
	static int[] dk = {0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1}; // 특수이동 시 
	static int[] dy = {-1, 0, 1, 0, -2, -1, 1, 2, 2, 1, -1, -2}; 
	static int[] dx = {0, 1, 0, -1, 1, 2, 2, 1, -1, -2, -2, -1};
	
	
    public static void main(String[] args) throws IOException {
    	// 입력
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[K+1][H][W];
		visited = new boolean[K+1][H][W];
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<W; j++) { // 특수 이동 가능 횟수 k
				map[0][i][j] = Integer.parseInt(st.nextToken());
				for(int k=1; k<=K; k++) {
					map[k][i][j] = map[0][i][j];	
				}
			}
		}
		// 풀이
		int distance = bfs();
		// 출력
		sb.append(distance);
		bw.write(sb.toString());
		bw.flush();
	}

	private static int bfs() {
		Queue<Pos> q = new ArrayDeque<>();
		
		visited[K][0][0] = true;
		q.offer(new Pos(K, 0, 0)); // [k=K][y=0][x=0] 시작
		int cnt = 1;
		
		while(!q.isEmpty()) {
			int qSize = q.size();
			while(qSize-- > 0) {
				Pos p = q.poll();
				for(int i=0; i<12; i++) {
					int nk = p.jump + dk[i];
					int ny = p.y + dy[i];
					int nx = p.x + dx[i];
					if(!isIn(nk, ny, nx)) continue; //  범위 내 인지 확인
					if(visited[nk][ny][nx]) continue; // 이미 방문한 곳
					if(map[nk][ny][nx]==1) continue; // 장애물
					map[nk][ny][nx]=cnt;
					visited[nk][ny][nx] = true;
					q.offer(new Pos(nk, ny, nx));
				}
			}
			cnt++;
		}
		
		int minDistance = Integer.MAX_VALUE;
		for(int i=0; i<=K; i++) {
			if(visited[i][H-1][W-1])
				minDistance = Math.min(minDistance, map[i][H-1][W-1]);
		}
		if (minDistance == Integer.MAX_VALUE) return -1;
		return minDistance;
	}

	private static boolean isIn(int nk, int ny, int nx) {
		return 0 <= nk && nk <= K  && 0 <= ny && ny < H && 0 <= nx && nx < W;
	}
}
