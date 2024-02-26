package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <일우는 야바위꾼> B3
 * 링크
 * https://www.acmicpc.net/problem/20361
 * 요약
 * swap 구현
 * 풀이
 * swap 구현
 */
public class boj_20361 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int T; // 테스트케이스 개수
	static int N, X, K; // 종이컵 개수 N, 시작 간식 위치 X(왼쪽에서 X번째), 컵 교환 횟수 K
	static boolean[] cup;
	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		cup = new boolean[N+1]; // 0번 컵 제외
		cup[X] = true;
		// 풀이
		for(int i=0; i<K; i++) { // 컵 교환 K번
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			swap(a, b, cup);
		}
		// 출력
		for(int i=1; i<=N; i++) {
			if(cup[i]) {
				sb.append(i);
				break;
			}
		}

		bw.write(sb.toString());
		bw.flush();
	}
	
	private static void swap(int a, int b, boolean[] cup) {
		boolean temp = cup[a];
		cup[a] = cup[b];
		cup[b] = temp;		
	}
}
