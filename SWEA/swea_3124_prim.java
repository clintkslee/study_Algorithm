package SWEA;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <최소 스패닝 트리> D4
 * 링크
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV_mSnmKUckDFAWb
 * 요약
 * 프림 알고리즘 적용
 * 풀이
 * 프림
 */
public class swea_3124_prim {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static class Vertex implements Comparable<Vertex> {
		int no, weight;
		public Vertex(int no, int weight) {
			this.no = no;
			this.weight = weight;
		}
		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	static int T; // tc 수
	static int V, E; // 정점의 수 V, 간선의 수 E
	static ArrayList<ArrayList<Vertex>> list;
	static boolean[] visit; // 트리 정정들
	static int[] minEdge; // 비트리 정점들
	static long weight, cnt;
	
    public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			// 입력 	
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			visit = new boolean[V+1];
			minEdge = new int[V+1];
			list = new ArrayList<>();
			for(int i=0; i<=V; i++) {
				list.add(new ArrayList<>());
			}
			
			for(int i=0; i<E; i++) { // 인접 리스트 생성
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken()); // 정점 A
				int B = Integer.parseInt(st.nextToken()); // 정점 B
				int C = Integer.parseInt(st.nextToken()); // 간선 가중치 C
				list.get(A).add(new Vertex(B, C));
				list.get(B).add(new Vertex(A, C));
			}
			
			Arrays.fill(minEdge, Integer.MAX_VALUE);
			// 풀이
			prim();
			// 출력
			sb.append("#"+tc+" "+weight+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static void prim() {
		weight = 0; // 가중치 합
		cnt=0; // MST에 속한 정점 개수
		
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		minEdge[1] = 0; // 임의의 시작점 1
		pq.offer(new Vertex(1, minEdge[1])); // pq에서 뽑으면 트리에 속하는 정점이 됨
		
		while(!pq.isEmpty()) {
			Vertex v = pq.poll();
			if(visit[v.no]) continue;
			
			weight += v.weight;
			visit[v.no] = true;
			cnt++;
			if(cnt==V) break;
			
			for(Vertex x : list.get(v.no)) {
				if(!visit[x.no] && x.weight < minEdge[x.no]) {
					minEdge[x.no] = x.weight;
					pq.offer(new Vertex(x.no, minEdge[x.no]));
				}
			}
		}
	}
}
