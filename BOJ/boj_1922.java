package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <네트워크 연결> G4
 * 링크
 * https://www.acmicpc.net/problem/1922
 * 요약
 * 크루스칼 알고리즘 이해
 * 풀이
 * 간선 리스트 생성, 유니온 파인드, 크루스칼
 */
public class boj_1922 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static class Edge implements Comparable<Edge> { // 간선 클래스
		int u;
		int v;
		int weight;
		public Edge(int u, int v, int weight) {
			this.u = u;
			this.v = v;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	static int N; // 컴퓨터 수 N
	static int M; // 연결할 수 있는 선의 수 M
	static Edge[] edges;
	static int[] parent;
	static int weightSum = 0;
	
    public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		edges = new Edge[M];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(u, v, w);
		}
		// 풀이
		kruskal();
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	private static void kruskal() {
		int cnt = 0; 
		parent = new int[N+1];
		// make set
		for(int i=1; i<=N; i++) parent[i] = i;
		// edge 정렬
		Arrays.sort(edges);
		// N-1 개 edge 선택 시 종료
		for(int i=0; i<M; i++) { // 가중치 낮은 순서로 edge 하나씩 탐색 
			if(!union(edges[i].u, edges[i].v)) continue; // 두 정점이 같은 set에 속하면 continue
			cnt++;
			weightSum += edges[i].weight;
			if(cnt==N-1) break; // N-1개 간선 선택 시 break
		}
		sb.append(weightSum+"");
	}

	private static int find(int u) {
		if(u==parent[u]) return u;
		return parent[u] = find(parent[u]);
	}

	private static boolean union(int u, int v) {
		int vRoot = find(v);
		int uRoot = find(u);
		if(vRoot==uRoot) return false;
		parent[uRoot] = vRoot;
		return true; 
	}
}