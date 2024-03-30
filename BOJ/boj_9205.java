package BOJ;

import java.util.*;
import java.io.*;

/*
 * 제목
 * <맥주 마시면서 걸어가기> G5
 * 링크
 * https://www.acmicpc.net/problem/9205
 * 요약
 * 그래프탐색
 * 풀이
 * bfs
 */
public class boj_9205 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n; // 편의점 개수
	static int[][] node; // [i][0] : x좌표, [i][1] : y좌표
	static int[][] arr; // [0] : 집, [n+1] : 도착지
	static boolean[] vis;

	public static void main(String[] args) throws IOException {
		int t = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
		for (int TC = 0; TC < t; ++TC) {
			node = new int[102][2];
			arr = new int[102][102];
			vis = new boolean[102];
			// 입력
			n = Integer.parseInt(br.readLine());
			for (int i = 0; i < n + 2; i++) {
				st = new StringTokenizer(br.readLine());
				node[i][0] = Integer.parseInt(st.nextToken());
				node[i][1] = Integer.parseInt(st.nextToken());
			}

			// 인접행렬 생성 : 최대 5151개 간선
			for (int i = 0; i < n + 2; ++i) {
				for (int j = 0; j < n + 2; ++j) {
					if (i == j)
						continue;
					int dist = Math.abs(node[i][0] - node[j][0]) + Math.abs(node[i][1] - node[j][1]);
					if (dist <= 1000) { // 맥주 20병으로 갈 수 있으면 경로 있음
						arr[i][j] = 1;
						arr[j][i] = 1;
					}
				}
			}
//			// 인접행렬 검증
//			for(int i=0; i<n+2; i++) {
//				for(int j=0; j<n+2; j++) {
//					System.out.print(arr[i][j]+" ");
//				}
//				System.out.println();
//			}
			
			// 풀이
			bfs();
			
		}
		// 출력
		bw.write(sb.toString());
		bw.flush();

	}

	private static void bfs() {
		vis[0] = true; // 시작점
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(0);
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int i=0; i<n+2; ++i) {
				if(!vis[i] && arr[cur][i]==1) {
					vis[i] = true;
					q.offer(i);
				}
			}
		}		
		if(vis[n+1]) sb.append("happy\n");
		else sb.append("sad\n");
	}
}
