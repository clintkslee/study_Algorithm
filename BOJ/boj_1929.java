package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <소수 구하기> S3
 * 링크
 * https://www.acmicpc.net/problem/1929
 * 요약
 * M이상 N이하 소수구하기
 * 풀이
 * 제곱근 이하의 수로 나눠보기
 */
public class boj_1929 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int M, N;
    public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		// 풀이
		boolean flag;
		for(int i=M; i<=N; i++) {
			if(i==1) continue; // 1은 소수가 아니다
			flag = true;
			for(int j=2; j<=(int)Math.sqrt(i); j++) {
				if(i%j==0) {
					flag = false;
					break;
				}
			}
			if(flag) sb.append(i+"\n");
		}
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}
}
