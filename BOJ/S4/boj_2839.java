package BOJ.S4;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <설탕배달>
 * 링크
 * https://www.acmicpc.net/problem/2839
 * 요약
 * 설탕 N만큼 배달, 설탕 봉지의 크기는 3, 5 일때 최소 봉지 수
 * 풀이
 * 5짜리 설탕 최대한 많이 들어야 봉지 최소, greedy
 */
public class boj_2839 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N; 
    public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine());
		// 풀이
		solution();
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	private static void solution() {
		int cnt = 0; // 동전 개수
		while(N>0) { // 설탕 무게 빼가면서 반복, 3씩 빼면서 0이하 되면 종료
			if(N%5==0) break; // 남은 설탕이 5의 배수이면 종료
			N -= 3;
			cnt++;
		}
		if(N<0) cnt = -1; // 3씩 빼서 음수까지 가면 조건 불만족
		else cnt += N/5;
		sb.append(cnt+"\n");
	}
}
