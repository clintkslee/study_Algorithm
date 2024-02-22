package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <게리맨더링> G4
 * 링크
 * https://www.acmicpc.net/problem/17471
 * 요약
 * 주어진 구역들을 인접한 2개의 선거구로 나누기 
 * 풀이
 * 1) 주어진 구역들을 2개의 선거구로 나눔 (조합) 
 * 2) 각 선거구들에 속하는 구역들이 모두 연결되어 있는 지 확인 
 * 3) 두 선거구 간 인구 수 차이 계산 후 업데이트
 */
public class boj_17471 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N; // 구역의 개수 N
	static int[] population; // 1~N 구역의 인구
	static int sumPopulation; // 1~N 구역의 인구 총합
	static boolean[][] adjMatrix;
	static int minDiff = Integer.MAX_VALUE;
	static boolean[] selectedA; // true면 A선거구에 속하는 구역들
    public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		// [1]~[N] 구역 인구수
		population = new int[N+1];
		for(int i=1; i<=N; i++) {	
			population[i]=Integer.parseInt(st.nextToken());
			sumPopulation += population[i];
		}
		// 인접행렬 생성
		adjMatrix = new boolean[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken()); // i번째 선거구와 인접한 곳 개수
			for(int j=0; j<cnt; j++) {
				int to = Integer.parseInt(st.nextToken());
				adjMatrix[i][to] = true;
				adjMatrix[to][i] = true;	
			}
		}
		// 풀이
		solution();
		// 출력
		if(minDiff==Integer.MAX_VALUE) sb.append(-1);
		else sb.append(minDiff);
		bw.write(sb.toString());
		bw.flush();
	}

	private static void solution() {
		int half = N/2;
		for(int i=1; i<=half; i++) { // A선거구에 속하는 i개 구역 선택 
			selectedA = new boolean[N+1];
			combi(1, 0, i);
		}
	}

	private static void combi(int start, int depth, int max) {
		if(depth==max) {
			// selectedA에서 true/false 로 구분된 두 선거구가 가능한 지 조합인지 한지 확인
			ArrayList<Integer> a = new ArrayList<>();
			ArrayList<Integer> b = new ArrayList<>();
			for(int i=1; i<=N; i++) {
				if(selectedA[i]) a.add(i);
				else b.add(i);
			}	
//			System.out.println(a);
//			System.out.println(b);
			if(a.size()>1 && !isConnected(a)) {
//				System.out.println("a not connected");
//				System.out.println("========");
				return;
			}
			if(b.size()>1 && !isConnected(b)) {
//				System.out.println("b not connected");
//				System.out.println("========");
				return;			
			}
//			System.out.println("========");
			int sumA=0;
			for(int i : a)
				sumA += population[i];
			int diff = Math.abs(sumPopulation-sumA-sumA); // 두 선거구 간의 인구 수 차이
			minDiff = Math.min(minDiff, diff);
			return;
		}
		for(int i=start; i<=N; i++) {
			selectedA[i] = true;
			combi(i+1, depth+1, max);
			selectedA[i] = false;
		}
	}

	private static boolean isConnected(ArrayList<Integer> a) {
		int len = a.size();
		int[] parents = new int[len]; // a 선거구에 속하는 구역들 union find
		for(int i=0; i<len; i++)
			parents[i] = i; // i번째 정점의 번호는 a.get(i);
		for(int i=0; i<len; i++) {
			for(int j=i+1; j<len; j++) {
				union(parents, i, a.get(i), j, a.get(j)); // 두 구역이 인접했으면 union 후 i 작은 쪽을 루트로 union
			}
		}
		int ancestor = find(parents, 0); 
		for(int i=1; i<len; i++) { // union 완료 후, 첫번째 구역의 루트와 다른 값 가지고 있는 구역 있는 지 확인
			if(find(parents, i)!=ancestor) return false; 
		}
		return true; // 모두 연결되어 트리가 되었으면 true
	}

	private static int find(int[] parents, int i) {
		if(i==parents[i]) return i;
		return parents[i] = find(parents, parents[i]);
	}

	private static void union(int[] parents, int i, int geti, int j, int getj) {
		if(adjMatrix[geti][getj]) parents[find(parents, j)] = find(parents, i);
	}
}
