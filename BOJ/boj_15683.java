package BOJ;

import java.awt.Point;
import java.io.*;
import java.util.*;
/*
 * 제목
 * <감시> G4
 * 링크
 * https://www.acmicpc.net/problem/15683
 * 요약
 * 주어진 카메라의 방향을 조절하면서 사각지대가 최소가 될 때의 사각지대 칸 수 구하기
 * 풀이
 * 모든 카메라 회전해가며 모든 경우 탐색
 */
public class boj_15683 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static class Camera {
		int type, x, y;
		public Camera(int type, int x, int y) {
			this.type = type;
			this.x = x;
			this.y = y;
		}
	}

	static int N, M; // 사무실 세로 크기 N, 가로크기 M
    static int[][] office;
    static ArrayList<Point> wallPos = new ArrayList<>();
    static ArrayList<Camera> camPos = new ArrayList<>();
    static int camCnt;
    static final int UP=0, RIGHT=1, DOWN=2, LEFT=3, WALL=6;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int min = Integer.MAX_VALUE;
    
	public static void main(String[] args) throws IOException {
		// 입력
    	st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		office = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				office[i][j] = Integer.parseInt(st.nextToken());
				if(office[i][j] == 6) wallPos.add(new Point(j, i)); // x, y
				else if(office[i][j] != 0) camPos.add(new Camera(office[i][j], j, i));
			}
		}
		camCnt = camPos.size();
		// 풀이
		dfs(0, office);
		// 출력
		sb.append(min);
		bw.write(sb.toString());
		bw.flush();
	}

	private static void dfs(int depth, int[][] arr) { // depth 가 카메라 개수 만큼 되면 끝
		if(depth==camCnt) {
			min = Math.min(min, getBlindSpot(arr));
			return;
		}
		int camType = camPos.get(depth).type;
		int camX = camPos.get(depth).x;
		int camY = camPos.get(depth).y;
		switch(camType) { // CCTV 타입에 따라 회전시키면서 모든 경우 탐색
			case 1: // 상, 우, 하, 좌
				for(int i=0; i<4; i++) { 
					int[][] temp = copy(arr);
					watch(temp, camY, camX, i);
					dfs(depth+1, temp);
				}
				break;
			case 2: // 상하, 좌우
				for(int i=0; i<2; i++) { 
					int[][] temp = copy(arr);
					watch(temp, camY, camX, i); 
					watch(temp, camY, camX, i+2);
					dfs(depth+1, temp);
				}
				break;
			case 3: // └, ┌, ┐, ┘
				for(int i=0; i<4; i++) {
					int[][] temp = copy(arr);
					watch(temp, camY, camX, i%4);
					watch(temp, camY, camX, (i+1)%4);
					dfs(depth+1, temp);
				}
				break;
			case 4: // ├, ┬, ┤, ┴ 
				for(int i=0; i<4; i++) {
					int[][] temp = copy(arr);
					watch(temp, camY, camX, i);
					watch(temp, camY, camX, (i+1)%4);
					watch(temp, camY, camX, (i+2)%4);
					dfs(depth+1, temp);
				}
				break;
			case 5: // ┼
				int[][] temp = copy(arr);
				watch(temp, camY, camX, UP);
				watch(temp, camY, camX, DOWN);
				watch(temp, camY, camX, LEFT);
				watch(temp, camY, camX, RIGHT);
				dfs(depth+1, temp);
				break;
		}
	}

	// 2차원 배열 복사
	private static int[][] copy(int[][] arr) {
		int[][] temp = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				temp[i][j] = arr[i][j];
			}
		}
		return temp;
	}
	
	// 현재 카메라 위치에서 주어진 방향 1자로 감시
	private static void watch(int[][] temp, int y, int x, int dir) { // 카메라 위치[y][x], 방향
		while(isIn(y+dy[dir], x+dx[dir])) {
			if(temp[y+dy[dir]][x+dx[dir]]==WALL) break; // 벽이면 수정 종료
			y += dy[dir];
			x += dx[dir];
			if(isCamera(y, x)) continue; // 카메라면 수정x
			temp[y][x] = '#';
		}		
	}

	// [y][x] 가 카메라인 지 확인
	private static boolean isCamera(int y, int x) {
		if(1 <= office[y][x] && office[y][x] <= 5) return true;
		return false;
	}

	// [y][x] 가 유효한 위치인 지 확인
	private static boolean isIn(int y, int x) {
		if(0 <= y && y < N && 0 <= x && x < M) return true;
		return false;
	}
	
	// 사각지대 칸 수 세기
	private static int getBlindSpot(int[][] arr) {
		int sum = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j] == 0) sum++;
			}
		}
		return sum;
	}
}
