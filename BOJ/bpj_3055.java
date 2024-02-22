package BOJ;

import java.awt.Point;
import java.io.*;
import java.util.*;
/*
 * 제목
 * <탈출> G4
 * 링크
 * https://www.acmicpc.net/problem/3055
 * 요약
 * 고슴도치를 목표 지점으로 이동시킬 수 있는 지 여부 + 시간 구하기
 * 풀이
 * 물 먼저 넘치게 한 후 고슴도치 BFS 
 */
public class bpj_3055 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int R, C; // 티떱숲! R*C
	static char[][] map;
	static int[][] visit;
	static final char BLANK = '.', WATER = '*', ROCK = 'X', HOME = 'D', SONIC = 'S';
	static Queue<Point> waterQ = new ArrayDeque<>(); // 물 bfs
	static ArrayList<int[][]> maps = new ArrayList<>();
	static Queue<Point> sonicQ = new ArrayDeque<>(); 
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static Point home;
    public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visit = new int[R][C];
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j]==WATER) waterQ.offer(new Point(j, i));
				else if(map[i][j]==SONIC) {
					sonicQ.offer(new Point(j, i));
					map[i][j] = BLANK;
				}
				else if(map[i][j]==HOME) home=new Point(j, i);
			}
		}
		// 풀이
		bfs();
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	private static void bfs() {
		boolean survived = false;
		while(true) {
			int waterCnt = waterQ.size();
			while(waterCnt-- > 0) { // 먼저 물을 넘치게 해서 고슴도치가 이동할 수 있는 곳만 남기기
				Point w = waterQ.poll();
				for(int i=0; i<4; i++) {
					int nx = w.x+dx[i];
					int ny = w.y+dy[i];
					if(isIn(nx, ny) && map[ny][nx]==BLANK) {
						map[ny][nx] = WATER;
						waterQ.offer(new Point(nx, ny)); // 다음에 넘치게 할 위치
					}	
				}
			}
			int sonicCnt = sonicQ.size();
			while(sonicCnt-- > 0) {			
				Point p = sonicQ.poll(); // 싸-닉의 현재 위치
				if(p.x==home.x && p.y==home.y) {
					survived = true;
					break;
				}
				for(int i=0; i<4; i++) {
					int nx = p.x+dx[i];
					int ny = p.y+dy[i];
					if(!isIn(nx, ny) || map[ny][nx]==ROCK || map[ny][nx]==WATER || visit[ny][nx]>0) continue;
					visit[ny][nx] = visit[p.y][p.x]+1;
					sonicQ.offer(new Point(nx, ny));	
				}
			}
			// 현재 위치가 집이거나 큐가 비었다면(더 이상 갈 수 있는 곳이 없으면) break
			if(survived == true || sonicQ.isEmpty()) break;
		}
		if(survived) sb.append(visit[home.y][home.x]);
		else sb.append("KAKTUS");
	}

	private static boolean isIn(int x, int y) {
		if(0 <= x && x < C && 0 <= y && y < R) return true;
		return false;
	}
	
	
}
