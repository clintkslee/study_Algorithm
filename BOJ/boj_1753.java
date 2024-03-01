package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <최단경로> G4
 * 링크
 * https://www.acmicpc.net/problem/1753
 * 요약
 * 다익스트라 구현하기
 * 풀이
 * 다익스트라
 */
public class boj_1753 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static class Node {
		int num;
		int weight;
		
		public Node(int num, int weight) {
			this.num = num;
			this.weight = weight;
		}
	}

	static int V, E; // 정점 개수(1~N), 간선 개수
	static int K; // 시작 정점 개수
	static ArrayList<Node>[] list; // 인접리스트
	static boolean[] visited; // 정점i 방문 여부 [i]
	static int[] distance; // 정점i까지의 거리 [i]
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		// 입력
    	st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); // 정점 개수 N(1~N)
		E = Integer.parseInt(st.nextToken()); // 간선 개수 E
		K = Integer.parseInt(br.readLine()); // 시작 정점
		
		// 인접리스트 생성
		list = new ArrayList[V+1];
		for(int i=1; i<=V; i++) list[i] = new ArrayList<>();
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[u].add(new Node(v, w));
		}
		// 풀이
		dijkstra(K);
		// 출력
		for(int i=1; i<=V; i++) {
			if(distance[i]==Integer.MAX_VALUE) sb.append("INF\n");
			else sb.append(distance[i]+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}


	private static void dijkstra(int start) {
		// 초기화
		visited = new boolean[V+1];
		distance = new int[V+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		 // 시작 정점 설정
		distance[start] = 0;
		
		int min, node;
		for(int i=1; i<=V; i++) { // 최대 정점 개수 만큼 반복
			min = Integer.MAX_VALUE;
			node = -1; // 현재 갈 수 있는 노드들 중 가장 가까운 노드
			for(int j=1; j<=V; j++) {
				if(!visited[j] && min > distance[j]) {
					min = distance[j];
					node = j;
				}
			}
			if(node == -1) break; // 미방문인 노드 못찾은 경우
			
			// 거리 업데이트
			visited[node] = true;
			for(Node n : list[node]) {
				if(distance[n.num] > distance[node] + n.weight) {
					distance[n.num] = distance[node] + n.weight;
				}
			}
		}
	}
}
