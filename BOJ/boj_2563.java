package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <색종이>
 * 링크
 * https://www.acmicpc.net/problem/2563
 * 요약
 * 색종이 넓이합 구하기
 * 풀이
 * 색종이가 덮힌 곳은 true 로 생각하여 겹친 부분 계산
 */
public class boj_2563 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static boolean[][] paper = new boolean[100][100];
	static int N;		// 색종이 수
	static int dy, dx;	// 색종이 시작 위치
	static int cnt;
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			dy = Integer.parseInt(st.nextToken());
			dx = Integer.parseInt(st.nextToken());
			for(int j=dx; j<dx+10; j++) {
				for(int k=dy; k<dy+10; k++) {
					paper[j][k] = true;
				}
			}
		}
		for(int i=0; i<100; ++i) {
			for(int j=0; j<100; ++j)
				if(paper[i][j]) cnt++;
		}
		bw.write(cnt+"");
		bw.flush();
	
	}
}
