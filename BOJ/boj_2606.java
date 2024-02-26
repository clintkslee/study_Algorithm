package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <바이러스> S3
 * 링크
 * https://www.acmicpc.net/problem/2606
 * 요약
 * 그래프 탐색
 * 풀이
 * BFS
 */
public class boj_2606 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N; // 사람수 N 1~N
	static int E; // 인접정보 개수 E
	static ArrayList<Integer>[] list;
	static boolean visited[];
	static int cnt = 0;
	
	public static void main(String[] args) throws IOException {
		// 사람 수 		
		N = Integer.parseInt(br.readLine());
		// 인접리스트 생성
		list = new ArrayList[N+1];
		visited = new boolean[N+1];
		
		for(int i=1; i<=N; i++) list[i] = new ArrayList<>();
		// 인접 정보 수  E
		E = Integer.parseInt(br.readLine());
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list[from].add(to);
			list[to].add(from);
		}
		// 풀이
		bfs();
		// 출력
		cnt--; // 1번 정점(시작점)은 소문을 알게 된 사람 수에 포함 x 
		sb.append(cnt);
		bw.write(sb.toString());
		bw.flush();
		
	}

	private static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(1); // 시작 정점
		visited[1] = true;
		while(!q.isEmpty()) {
			int cur = q.poll();
			cnt++;
			ArrayList<Integer> l = list[cur];
			for(int v : l) {
				if(!visited[v]) {
					q.offer(v);
					visited[v] = true;
				}
			}
		}
	}
}
