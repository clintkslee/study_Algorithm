package SWEA;

import java.io.*;
import java.util.*;

/*
 * 제목
 * <최장 증가 부분 수열> D3
 * 링크
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBOKg-a6l0DFAWr
 * 요약
 * lis
 * 풀이
 * lis
 */
public class swea_3307 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N;
	static int[] arr;
	static int[] C;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 입력
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			arr = new int[N];
			C = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			// 풀이
			int max = lis();
			// 출력
			sb.append('#').append(tc).append(' ').append(max).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static int lis() {
		int max = 0;
		for (int i = 0; i < N; i++) {
			int position = Arrays.binarySearch(C, 0, max, arr[i]);
			position = Math.abs(position) - 1;

			C[position] = arr[i];
			if (max == position) {
				max++;
			}
		}
		return max;
	}
}