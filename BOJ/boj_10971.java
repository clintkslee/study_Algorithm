package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <외판원 순회 2> S2
 * 링크
 * https://www.acmicpc.net/problem/10971
 * 요약
 * TSP 문제
 * 풀이
 * DFS
 */
public class boj_10971 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N; // 도시의 수
	static int[][] W; // 인접행렬
	static boolean[] visit; // 거리 정보
	static long minWeight = Integer.MAX_VALUE;
	
    public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine());
		W = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 풀이
		for(int i=0; i<N; i++) { // 모든 도시에서 출발시켜 보기
			visit = new boolean[N];
			visit[i] = true;
			dfs(i, 0, i, 0);
		}
		// 출력
		sb.append(minWeight);
		bw.write(sb.toString());
		bw.flush();
	}

	private static void dfs(int startIdx, int depth, int curIdx, int weightSum) {
		if(weightSum > minWeight) return;
		if(depth == N-1) { // N개의 도시 방문 완료 (시작 도시 미리 체크했으므로 -1)
			if(W[curIdx][startIdx] > 0) // 마지막 도시에서 맨 처음 도시로 돌아가는 길 있는 지 확인
				minWeight = Math.min(minWeight, weightSum+W[curIdx][startIdx]);
			return;
		}
		for(int i=0; i<N; i++) {
			if(W[curIdx][i]>0 && !visit[i]) {
				visit[i] = true;
				dfs(startIdx, depth+1, i, weightSum+W[curIdx][i]);
				visit[i] = false;
			}
		}
	}
}
