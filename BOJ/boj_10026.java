package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <적록색약> G5
 * 링크
 * https://www.acmicpc.net/problem/10026
 * 요약
 * 조건에 따라 배열 구역 나누기
 * 풀이
 * flood fill (dfs)
 */
public class boj_10026 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N;
	static char[][] arr;
	static int[] dy = {-1, 1, 0, 0}; // 상, 하, 좌, 우
	static int[] dx = {0, 0, -1, 1}; 
	
	
    public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		// 풀이
		normal();
		colorBlindness();
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	private static void normal() {
		char[][] a = new char[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				a[i][j] = arr[i][j];
			}
		}
		
		int normalCnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(a[i][j] != 'x') { // RGB 중 하나
					normalCnt++;
					dfs_n(i, j, a[i][j], a); // 현재 색으로 구역 확인
				}
			}
		}		
		sb.append(normalCnt+" ");
		
	}

	private static void dfs_n(int i, int j, char color, char[][] a) {
		a[i][j] = 'x';
		for(int d=0; d<4; d++) {
			int ny = i + dy[d];
			int nx = j + dx[d];
			if(!isIn(nx, ny)) continue; // 범위 외 예외처리
			if(color == a[ny][nx]) {	
				dfs_n(ny, nx, a[ny][nx], a);
			}
		}
	}

	private static void colorBlindness() {
		char[][] a = new char[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				a[i][j] = arr[i][j];
			}
		}
		
		int cbCnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(a[i][j] != 'x') {
					cbCnt++;
					dfs_cb(i, j, a[i][j], a);
				}
			}
		}
		sb.append(cbCnt);
	}

	private static void dfs_cb(int i, int j, char color, char[][] a) {
		a[i][j] = 'x';
		for(int d=0; d<4; d++) {
			int ny = i + dy[d];
			int nx = j + dx[d];
			if(!isIn(nx, ny)) continue; // 범위 외 예외처리
			if(color == 'B') { // 청
				if(a[ny][nx] == color) {	
					dfs_cb(ny, nx, color, a);
				}
			} else if(color == 'R' || color == 'G') { // 적록
				if(a[ny][nx] == 'R' || a[ny][nx] == 'G') {	
					dfs_cb(ny, nx, color, a);
				}
			}
		}
		
	}
	
	private static boolean isIn(int nx, int ny) {
		return 0 <= nx && nx < N && 0 <= ny && ny < N;
	}
}
