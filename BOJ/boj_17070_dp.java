package BOJ;
import java.io.*;
import java.util.*;
/*
 * 제목
 * <파이프 옮기기 1> G5
 * 링크
 * https://www.acmicpc.net/problem/17070
 * 요약
 * [1][2]에 놓인 파이프 머리를 [N][N] 에 보내려고 할 때 가능한 경우의 수 
 * 풀이
 * dp
 * [i][j] 에 파이프 머리가 놓이는 경우를 가로, 세로, 대각으로 나눠서 생각하기
 */
public class boj_17070_dp {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
		
	static int N;
    static int[][] map;
    static int[][][] d;
    
    public static void main(String[] args) throws IOException {
   		// 입력
    	N = Integer.parseInt(br.readLine());
    	map = new int[N+1][N+1];
    	d = new int[N+1][N+1][3]; // [i][j] 위치에 가로, 세로, 대각으로 도착하는 경우 저장 
 	   	for(int i=1; i<=N; i++) {
   	 		st = new StringTokenizer(br.readLine());
   	 		for(int j=1; j<=N; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
  	  	}
		// 풀이
	    dp();
		// 출력
		sb.append(d[N][N][0]+d[N][N][1]+d[N][N][2]);
		bw.write(sb.toString());
		bw.flush();
	}
	private static void dp() {
		d[1][2][0] = 1; 
		d[1][2][1] = 0;
		d[1][2][2] = 0;
		
		for(int i=1; i<=N; i++) {
			for(int j=3; j<=N; j++) { // [i][j] 위치 진입 시 경우의 수 구하기
				if(map[i][j]==1) d[i][j][0] = 0;
				else d[i][j][0] = d[i][j-1][0] + d[i][j-1][2];
				if(map[i][j]==1)d[i][j][1] = 0;
				else d[i][j][1] = d[i-1][j][1] + d[i-1][j][2];
				if(map[i][j]==1 || map[i-1][j]==1 || map[i][j-1]==1) d[i][j][2] = 0;
				else d[i][j][2] = d[i-1][j-1][0] + d[i-1][j-1][1] + d[i-1][j-1][2];
			}
		}
	}
}
