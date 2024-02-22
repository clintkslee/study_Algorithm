package SWEA;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <Contact> D4
 * 링크
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15B1cKAKwCFAYD
 * 요약
 * 주어진 정점에서 시작하여 최대한 뻗어나갈 때, 시작점에서 가장 멀리 위치한 노드들 중 가장 큰 값 출력
 * 풀이
 * bfs
 */
public class swea_1238 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int dataLength, start; // 데이터 길이, 데이터 시작점
	static int[][] phoneNum; // 연락처 인접행렬(유향그래프)
	static ArrayList<Integer> list;
    public static void main(String[] args) throws IOException {
		for(int tc = 1; tc<=10; tc++) {
			// 입력 	
			st = new StringTokenizer(br.readLine());
			dataLength = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			phoneNum = new int[101][101]; // 1번~100번 사람 간 연락처 여부
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<dataLength/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				phoneNum[from][to] = 1;
			}
			// 풀이
			bfs();
			// 출력
			sb.append("#"+tc+" "+list.get(0)+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	private static void bfs() {
		Queue<Integer> q1 = new ArrayDeque<>(); // BFS
		Queue<Integer> q2 = new ArrayDeque<>(); // 출력용
		boolean[] isCalled = new boolean[101]; // 1번~100번 사람
		
		isCalled[start] = true; // 비상연락 시작점
		q1.offer(start);
		q2.offer(start);
		
		while(true) {
			int q1Size = q1.size();
			int q2Size = q1Size;
			while(q1Size-->0) {
				int cur = q1.poll();
				for(int i=1; i<=100; i++) {
					if(phoneNum[cur][i]==1 && !isCalled[i]) {
						isCalled[i]=true;
						q1.offer(i);
						q2.offer(i); // q1과 같이 삽입
					}
				}
			}
			if(q1.isEmpty()) break; // bfs 종료
			while(q2Size-->0) { // q1보다 한박자 늦게 poll() 되므로 마지막 레벨의 노드들은 남음
				q2.poll();
			}
		}
		list = new ArrayList<>();
		while(!q2.isEmpty()) list.add(q2.poll()); 
		Collections.sort(list, Collections.reverseOrder()); // 마지막 레벨 노드들 정렬
	}
}
