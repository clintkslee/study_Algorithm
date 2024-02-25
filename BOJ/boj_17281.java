package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <야구> G4
 * 링크
 * https://www.acmicpc.net/problem/17281
 * 요약
 * 선수들이 주어진 결과대로 이닝을 수행할 때, 최대 점수 구하기 
 * 풀이
 * next permutation + dfs
 */
public class boj_17281 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N; // 이닝 수
	static int[] perm = new int[]{1, 2, 3, 4, 5, 6, 7, 8};// 선수들 순열
	static int[] players;
	static Queue<Integer> q; // 타석 순서
	static int[][] inningRes;
	static int maxScore = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine());
		inningRes = new int[N][9]; // N개 이닝, 9명 선수 결과
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				inningRes[i][j] = Integer.parseInt(st.nextToken()); // (i+1)번째 이닝 (j+1)번 선수의 스윙 결과
			}
		}
		// 풀이
		do {
			setPlayers(); // q에 선수 순서 저장
			dfs(0, 0); // 수행한 이닝 수 , 이닝 수행 결과 누적(점수)
		} while(np(perm));
		// 출력
		System.out.println(maxScore);
	}

	private static boolean np(int[] p) {
		final int N = p.length;
		int i = N-1;
		while(i>0 && p[i-1]>=p[i]) --i; 
		if(i==0) return false;
		int j = N-1;
		while(p[i-1] >= p[j]) --j;
		swap(p, i-1, j);
		int k = N-1; 
		while(i<k) swap(p, i++, k--);
		return true;
	}
	
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		
	}
	
	private static void setPlayers() {
		int cnt = 0;
		players = new int[9];
		players[3] = 0; // 4번타자 1번선수로 고정
		for(int i=0; i<9; i++) {
			if(i==3) continue;
			players[i] = perm[cnt++];
		}	
		q = new ArrayDeque<>();
		for(int i=0; i<9; i++) q.add(players[i]);
	}

	private static void dfs(int depth, int score) {
		if(depth==N) {
			maxScore = Math.max(maxScore, score);
			return;
		} else {
			int outCount = 0;
			boolean[] baseState = {false, false, false}; // 1루, 2루, 3루
			while(outCount < 3) {
				int currentPlayer = q.poll();
				q.offer(currentPlayer);
				switch(inningRes[depth][currentPlayer]) { 
					case 0: // 아웃 : 아웃카운트+1
						outCount++;
						break;
					case 1: // 안타(1루타): 모든 주자 한 칸 전진
						if(baseState[2]) { // 3루에 주자 있으면 1점 획득
							score++;
							baseState[2]=false;
						}
						if(baseState[1]) { // 2루
							baseState[1]=false;
							baseState[2]=true;
						}
						if(baseState[0]) { // 1루
							baseState[0]=false;
							baseState[1]=true;
						}
						baseState[0]=true; // 타자 진루
						break;
					case 2: // 2루타 : 모든 주자 두 칸 전진
						if(baseState[2]) { // 3루
							score++;
							baseState[2]=false;
						}
						if(baseState[1]) { // 2루
							score++;
							baseState[1]=false;
						}
						if(baseState[0]) { // 1루
							baseState[0]=false;
							baseState[2]=true;
						}
						baseState[1]=true; // 타자 진루
						break;
					case 3: // 3루타 : 모든 주자 세 칸 전진
						if(baseState[2]) { // 3루
							score++;
							baseState[2]=false;
						}
						if(baseState[1]) { // 2루
							score++;
							baseState[1]=false;
						}
						if(baseState[0]) { // 1루
							score++;
							baseState[0]=false;
						}
						baseState[2]=true; // 타자 진루
						break;
					case 4: // 홈런(4루타) : 모든 주자 홈까지 전진
						if(baseState[2]) { // 3루
							score++;
							baseState[2]=false;
						}
						if(baseState[1]) { // 2루
							score++;
							baseState[1]=false;
						}
						if(baseState[0]) { // 1루
							score++;
							baseState[0]=false;
						}
						score++; // 타자 홈인
						break;
				}
			}
			dfs(depth+1, score);
		}
	}
}
