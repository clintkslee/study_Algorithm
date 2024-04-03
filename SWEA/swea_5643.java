package SWEA;

import java.io.*;
import java.util.*;

/*
* 제목
* <키 순서> D4
* 링크
* https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXQsLWKd5cDFAUo
* 요약
* 모든 노드에 대해 다른 노드들 까지의 경로 있는 지 확인
* 임의의 노드 k에서 출발하여 경로 없는 노드에 대해 반대의 경우 도달할 수 있는 지 확인
* 풀이
* BFS
*/
public class swea_5643 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N; // 학생 수
	static int M; // 비교 횟수

	static ArrayList<ArrayList<Integer>> adjList; // 인접리스트
	static boolean[][] canGo;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; ++tc) {
			// 입력
			N = Integer.parseInt(br.readLine().trim());
			M = Integer.parseInt(br.readLine().trim());

			adjList = new ArrayList<>(N + 1); // 1~N번 노드가 연결된 노드
			for (int cnt = 0; cnt <= N; ++cnt) {
				adjList.add(new ArrayList<>());
			}

			for (int cnt = 0; cnt < M; ++cnt) {
				st = new StringTokenizer(br.readLine().trim());
				int small = Integer.parseInt(st.nextToken());
				int big = Integer.parseInt(st.nextToken());
				adjList.get(small).add(big);
			}

			// [i][j] : 노드 i에서 노드 j까지의 경로 있으면 true
			canGo = new boolean[N + 1][N + 1];
			for (int cnt = 1; cnt <= N; ++cnt)
				canGo[cnt][cnt] = true; // 차후 연산 위해 자기 자신으로 가는 길 true 라고 생각

			// 풀이
			// 1) 모든 노드에 대해 각 노드까지의 경로 존재하는 지 판단
			for (int i = 1; i <= N; ++i) { // 시작 노드
				findRoute(i);
			}
			// 2) 노드i에서 노드j로 가지 못해도 노드j에서 노드i로 갈 수 있으면 자기 위치 파악 가능. canGo[][] 를 항등행렬 기준으로 한쪽만 true면 true로 만들기
			for (int i = 2; i <= N; ++i) {
				for (int j = 1; j < i; ++j) {
					if (canGo[i][j] || canGo[j][i]) {
						canGo[i][j] = true;
						canGo[j][i] = true;
					}
				}
			}
			// 3) 모든 행 탐색하며 모든 열이 true면 cnt++
			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				boolean check = true;
				for (int j = 1; j <= N; j++) {
					if (!canGo[i][j]) {
						check = false;
						break;
					}
				}
				if (check)
					cnt++;
			}
			// 출력
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");

		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static void findRoute(int start) {
		boolean[] isVisited = new boolean[N + 1];
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		isVisited[start] = true;
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int node : adjList.get(cur)) {
				if (isVisited[node])
					continue;
				canGo[start][node] = true;
				isVisited[node] = true;
				q.offer(node);
			}
		}
	}
}