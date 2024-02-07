package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <배열 돌리기 1>
 * 링크
 * https://www.acmicpc.net/problem/16926
 * 요약
 * 반시계 방향으로 배열 R번 돌리기
 */
public class boj_16926 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N, M, R;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N+1][M+1];	// [0] 사용 x
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		// 풀이
		while(R-->0)
			rotate();
			
		// 출력
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++)
				sb.append(arr[i][j]+" ");
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	private static void rotate() {
		int n = N;
		int m = M;
		int direc, curi, curj;
		int min = (int)Math.min(N, M);	// min/2 : 내부 원 개수
		for(int i=1; i<=min/2; ++i) {	// i=1이면 최외곽 회전, [i+1][i] 시작
			arr[0][0] = arr[i][i];		// swap 용 초기값
			int temp;					// swap 용
			curi=i+1;
			curj=i;
			direc = 0;					// 하, 우, 상, 좌 순서
			for(int j=0; j<m*n-(m-2)*(n-2); j++) {	// 현재 원에 속하는 원소 개수만큼 반복
				if(direc==0 && curi+1>N-i+1) direc=1;
				else if(direc==1 && curj+1>M-i+1) direc=2;
				else if(direc==2 && curi-1<i) direc=3;	
				temp = arr[curi][curj];
				arr[curi][curj]=arr[0][0];
				arr[0][0] = temp;
				if(direc==0) curi++; // 하방향
				else if(direc==1) curj++; // 우방향
				else if(direc==2) curi--; // 상방향
				else curj--; // 좌방향
			}
			m-=2;
			n-=2;
		}
	}
}
