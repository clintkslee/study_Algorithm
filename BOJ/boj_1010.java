package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <다리 놓기> S5
 * 링크
 * https://www.acmicpc.net/problem/1010
 * 요약
 * 강 양 쪽을 잇는 다리를 N개 지을 때 가능한 경우의 수 구하기
 * 풀이
 * M_C_N
 */
public class boj_1010 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine()); // tc 개수
		for(int tc=0; tc<T; tc++) {
			// 입력
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 강 서쪽 사이트 개수
			int M = Integer.parseInt(st.nextToken()); // 강 동쪽 사이트 개수
			// 풀이
			int res = nCk(M, N);
			// 출력
			sb.append(res+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static int nCk(int n, int k) {
		int[][] nCk = new int[n+1][k+1]; // 필요한 부분까지만 배열 생성
		nCk[0][0] = 1;
		for(int i=1; i<=n; i++) {
			for(int j=0, end=Math.min(i,  k); j<=end; j++) { // 필요한 부분까지만 nCk 계산
				if(j==0 || j==i) nCk[i][j]=1;
				else nCk[i][j] = nCk[i-1][j-1]+nCk[i-1][j];
			}
		}
		return nCk[n][k];
	}
}
