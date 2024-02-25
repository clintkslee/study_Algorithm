package SWEA;

import java.awt.Point;
import java.io.*;
import java.util.*;
/*
 * 제목
 * <하나로> D4
 * 링크
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15StKqAQkCFAYD
 * 요약
 * MST
 * 풀이
 * 크루스칼
 */
public class swea_1251 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st ,st2;
	static StringBuilder sb = new StringBuilder();
	
	static class Edge implements Comparable<Edge>{
		int from, to;
		long weight;

		public Edge(int from, int to, long weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			if(this.weight-o.weight>0) return 1;
			else if(this.weight-o.weight==0) return 0;
			else return -1;
		}
	}
	
	static int T; // tc
	static int N; // 섬의 개수
	static double E;
	static Point[] island; // 섬들 정보 저장
	static ArrayList<Edge> edge; // 간선 리스트
	static double envFee; // 가중치 누적될 변수
	static int[] parents; // 크루스칼 parents
	
    public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			// 입력 	
			N = Integer.parseInt(br.readLine());
			island = new Point[N+1];
			st = new StringTokenizer(br.readLine());
			st2 = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st2.nextToken());
				island[i] = new Point(x, y);
			}
			E = Double.valueOf(br.readLine());
			// 풀이
			getEdges(); // 모든 edge 생성 (가중치 = 거리^2)
			kruscal();
			// 출력
			sb.append("#"+tc+" "+Math.round(envFee)+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
    }

	private static void kruscal() {
		envFee = 0;
		int cnt=0;
		makeSet();
		for(Edge e : edge) { 
			if(!union(e.from, e.to)) continue; // 현재  edge 추가 시 cycle 발생하므로 continue
			envFee += e.weight * E;
			if(++cnt==N-1) break; //N-1개 edge 선택되면 MST 생성 완료
		}
	}

	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if(rootA == rootB) return false;
		parents[rootB] = rootA;
		return true;
	}

	private static int find(int a) {
		if(parents[a]==a) return a;
		return parents[a] = find(parents[a]);
	}

	private static void makeSet() {
		parents = new int[N+1];
		for(int i=1; i<=N; i++) {
			parents[i]=i;
		}
	}

	private static void getEdges() {
		edge = new ArrayList<>();
		for(int i=1; i<=N; i++) {
			for(int j=i+1; j<=N; j++) {
				int dx = island[i].x - island[j].x;
				int dy = island[i].y - island[j].y;
				long weight = (long)(Math.pow(dx, 2) + Math.pow(dy, 2));
				edge.add(new Edge(i, j, weight));
			}
		}
		Collections.sort(edge);
	}
    
    
    
}
