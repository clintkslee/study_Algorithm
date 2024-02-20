package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <줄 세우기> G3
 * 링크
 * https://www.acmicpc.net/problem/2252
 * 요약
 * summary
 * 키 순서대로 줄 세우기
 * 풀이
 * 위상정렬
 */
public class boj_2252 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, M; // 학생 N명, 비교 횟수 M
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>(); //[i]번 정점이 향하는 정점들
	static int[] inCnt; // 진입차수 저장
	
    public static void main(String[] args) throws IOException {
    	// 입력
    	st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<=N; i++) { // [0] 사용 안함
			list.add(new ArrayList<>());
		}
		inCnt = new int[N+1]; // [0] 사용 안함
		
		for(int i=0; i<M; i++) { // 관계 입력, 진입 차수++
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list.get(from).add(to);
			inCnt[to]++;
		}
		
		// 풀이
		solution();
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	private static void solution() {
		Queue<Integer> q = new ArrayDeque<>();
		for(int i=1; i<=N; i++) { // 시작점이 되는 정점(진입차수==0)들 먼저 큐에 삽입
			if(inCnt[i]==0) 
				q.offer(i);
		}
		while(!q.isEmpty()) { // 큐에서 정점 하나 꺼낸 후, 연결된 정점들의 진입차수--
			int from = q.poll(); 
			sb.append(from+" ");
			ArrayList<Integer> temp = list.get(from); // 진입차수 관리 위한 임시 레퍼런스
			for(int to : temp) {
				inCnt[to]--;
				if(inCnt[to]==0) q.offer(to);
			}
		}
	}
}
