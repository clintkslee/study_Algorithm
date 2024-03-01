package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <1로 만들기> S3
 * 링크
 * https://www.acmicpc.net/problem/1463
 * 요약
 * 주어진 3개의 연산을 가지고 N을 1로 만들기 위해 필요한 연산 횟수
 * 풀이
 * dp
 */
public class boj_1463 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N;
	static int[] d;
	static int cnt;
    public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine());
		d = new int[N+1];
		// 풀이
		toOne(N);
		// 출력
		sb.append(d[N]);
		bw.write(sb.toString());
		bw.flush();
	}

	private static void toOne(int n) {
		d[1] = 0;
		if(n>=2) d[2] = 1;
		if(n>=3) d[3] = 1; 
		for(int i=4; i<=N; i++) {
			d[i] = d[i-1] +1;
			if(i%2==0) {
				d[i] = Math.min(d[i], d[i/2]+1);
			}
			if(i%3==0) {
				d[i] = Math.min(d[i], d[i/3]+1);
			}
		}
	}
}
