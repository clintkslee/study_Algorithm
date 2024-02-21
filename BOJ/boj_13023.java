package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <ABCDE> G5
 * 링크
 * https://www.acmicpc.net/problem/13023
 * 요약
 * 주어진 무향그래프에서 각 노드를 시작으로 4번 진출할 수 있으면 1출력, 없으면 0출력
 * 풀이
 * dfs
 */
public class boj_13023 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, M; // 사람 수 N, 관계 수 M
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>(); // 인접리스트 (0번 노드 ~ N-1번 노드)
	static boolean isPossible;
    public static void main(String[] args) throws IOException {
		// 입력
    	st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for(int i=0; i<N; i++) 
			list.add(new ArrayList<Integer>());
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b); // 양방향 그래프
			list.get(b).add(a);
		}
		// 풀이
		isPossible = false;
		for(int i=0; i<N; i++) {
			dfs(i, new boolean[N], 0); // 시작점, 선택한 노드 개수	
			if(isPossible) {
				break;
			}
		}
		// 출력
		if(isPossible) sb.append("1");
		else sb.append("0");
		bw.write(sb.toString());
		bw.flush();
	}

	private static void dfs(int current, boolean[] visit, int depth) {
		visit[current] = true;	
		
		if(depth==4) {
			isPossible = true;
			return;
		}
		
		ArrayList<Integer> temp = list.get(current);
		for(int i=0; i<temp.size(); i++) {
			if(visit[temp.get(i)]) continue; // 이미 방문한 곳이면 continue
			dfs(temp.get(i), visit, depth+1);
		}
		
		visit[current] = false;
	}
}
