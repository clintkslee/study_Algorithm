package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <치즈> G3
 * 링크
 * https://www.acmicpc.net/problem/2636
 * 요약
 * summary
 * 치즈가 다 녹을 떄 까지 걸리는 시간 구하기
 * 풀이
 * flood fill (dfs)
 */
public class boj_2636 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int R, C; // 사각판 세로, 가로 길이
	static int[][] arr; // 사각판
	
	static int[] dy = {-1, 1, 0, 0}; // 상, 하, 좌, 우
	static int[] dx = {0, 0, -1, 1}; 
	static int time; // 다 없어질 때 까지 걸린 시간
	static int cheeseCnt; // 다 없어지기 한시간 전 치즈칸 개수
	
    public static void main(String[] args) throws IOException {
    	// 입력
    	st = new StringTokenizer(br.readLine());
    	R = Integer.parseInt(st.nextToken());
    	C = Integer.parseInt(st.nextToken());
    	
    	arr = new int[R][C];
    	for(int i=0; i<R; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0; j<C; j++) {
    			arr[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
		// 풀이
    	cheeseCnt = cntCheese();
    	do {
    		time++;
    		floodFill(0, 0, new boolean[R][C]); 
    		int temp = cntCheese();
    		if(temp==0) break;
    		else cheeseCnt = temp;
    	} while(true);
    	
		// 출력
    	sb.append(time+"\n"+cheeseCnt);
		bw.write(sb.toString());
		bw.flush();
	}

	private static int cntCheese() {
		int cnt = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(arr[i][j]==1) cnt++;
			}
		}
		return cnt;
	}

	private static void floodFill(int x, int y, boolean[][] visit) { // 시작 위치 (0, 0)
		visit[y][x] = true;
		for(int d=0; d<4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if(!isIn(nx, ny)) continue; // 범위 외 예외처리
			if(visit[ny][nx]) continue; // 중복 방문 예외처리
			if(arr[ny][nx] == 1) {	
				arr[ny][nx] = 0;
				visit[ny][nx] = true;
			} else if(arr[ny][nx] == 0) {
				floodFill(nx, ny, visit);
			}
		}
	}
	
	private static boolean isIn(int nx, int ny) {
		return 0 <= nx && nx < C && 0 <= ny && ny < R;
	}
}
