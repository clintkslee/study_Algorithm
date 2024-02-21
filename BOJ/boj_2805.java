package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <나무 자르기> S2
 * 링크
 * https://www.acmicpc.net/problem/2805
 * 요약
 * 주어진 모든 수에 대해서 H만큼 뺀 값들의 합이 M 이상이 되게 하는 정수 H의 최댓값 구하기
 * 풀이
 * 정렬 후 H=(제일 긴 나무 길이-1)로 시작하여 H-- 하면서 나무 자르기
 */
public class boj_2805 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, M; // 나무의 수  N, 필요한 나무의 길이 M
	static int[] tree;
	static int H;
    public static void main(String[] args) throws IOException {
		// 입력
    	st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		tree = new int[N];
		for(int i=0; i<N; i++) 
			tree[i] = Integer.parseInt(st.nextToken());
		// 풀이
		solution();
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	private static void solution() {
		Arrays.sort(tree);
		int longestTreeLength = tree[tree.length-1];
		for(H=longestTreeLength-1; H>=0; H--) { // 길이 H로 자르기
			int sum = 0;
			for(int i=tree.length-1; i>=0; i--) {
				if(H>tree[i]) break;
				sum += tree[i]-H;
			}
			if(sum>=M) {
				break;
			}
		}
		sb.append(H+"");
	}
}
