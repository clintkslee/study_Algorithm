package SWEA;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <최소 스패닝 트리> D4
 * 링크
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV_mSnmKUckDFAWb
 * 요약
 * MST 이해
 * 풀이
 * 유니온 파인드, 크루스칼
 */
public class swea_3124_kruskal {
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
			return Integer.compare(this.weight, o.weight);
		}
	}
	static int T; // tc 수
	static int V, E; // 정점의 수 V, 간선의 수 E
	static Edge[] edges;
	static int[] parent;
	static long weight = 0;
	
    public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			// 입력 	
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			edges = new Edge[E];
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken()); // 정점 A
				int B = Integer.parseInt(st.nextToken()); // 정점 B
				int C = Integer.parseInt(st.nextToken()); // 간선 가중치 C
				edges[i] = new Edge(A, B, C);
			}
			// 풀이
			weight = 0;
			kruskal();
			// 출력
			sb.append("#"+tc+" "+weight+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static void kruskal() {
		int cnt = 0; 
		parent = new int[V+1]; // V={1, 2, 3, 4 ... V+1}
		// make set
		for(int i=1; i<=V; i++) parent[i] = i;
		// edge 정렬
		Arrays.sort(edges);
		// V-1 개 edge 선택 시 종료
		for(int i=0; i<E; i++) { // 가중치 낮은 순서로 edge 하나씩 탐색 
			if(!union(edges[i].u, edges[i].v)) continue; // 두 정점이 같은 set에 속하면 continue
			cnt++;
			weight += edges[i].weight;
			if(cnt==V-1) break; // V-1개 간선 선택 시 break
		}
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
