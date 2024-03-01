package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <2048 (Easy)> G2
 * 링크
 * https://www.acmicpc.net/problem/12100
 * 요약
 * 2048!
 * 풀이
 * dfs
 * 1) 이동하려는 방향 파악
 * 2) 해당 방향부터 원소들 모두 큐에 삽입
 * 3) 해당 방향 위부터 차례로 숫자 삽입 
 * 4) 5회 반복 후 게임판 전체 탐색, 최대값 갱신
 */
public class boj_12100 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N; // 게임판 크기 N*N
	static int[][] map;
	static final int UP=0, DOWN=1, LEFT=2, RIGHT=3;
	static Queue<Integer>[] q;
	static int maxVal = 0;

	@SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
		// 입력
    	N = Integer.parseInt(br.readLine());
    	q = new ArrayDeque[N];
    	for(int i=0; i<N; i++)
    	map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 풀이
		dfs(0, copyMap(map));
		// 출력
		sb.append(maxVal);
		bw.write(sb.toString());
		bw.flush();
	}

    private static int[][] copyMap(int[][] oriMap) {
		int[][] temp = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				temp[i][j] = oriMap[i][j];
			}
		}
		return temp;
	}

	private static void dfs(int depth, int[][] currentMap) {
		if(depth==5) {
			maxVal = Math.max(maxVal, getMaxFromCurrentMap(currentMap));
			return;
		}
		for(int i=0; i<4; i++) { // 0=UP, 1=DOWN, 2=LEFT, 3=RIGHT
			int[][] nextMap = new int[N][N]; // 연산 수행할 맵 생성
			switch (i) {
				case UP:
					getElements(currentMap, UP);
					setNextMap(nextMap, UP);
					dfs(depth+1, nextMap);
					break;
				case DOWN:
					getElements(currentMap, DOWN); 
					setNextMap(nextMap, DOWN);
					dfs(depth+1, nextMap);
					break;
				case LEFT:
					getElements(currentMap, LEFT);
					setNextMap(nextMap, LEFT);
					dfs(depth+1, nextMap);
					break;
				case RIGHT:
					getElements(currentMap, RIGHT);
					setNextMap(nextMap, RIGHT);
					dfs(depth+1, nextMap);
					break;
			}
		}
	}
    
    private static void setNextMap(int[][] nextMap, int direction) {
    	switch (direction) {
		case UP:
			for(int i=0; i<N; i++) { // N개의 큐 
				int idx = 0;
				if(q[i].isEmpty()) continue; // 빈 큐면 continue
				int before = q[i].poll(); // 큐의 첫번째 원소
				nextMap[idx++][i]=before; 
				while(!q[i].isEmpty()) {		
					int after = q[i].poll();
					if(before==0) { // 직전 원소가 그 전 원소와 합쳐졌을 때
						before = after;
						nextMap[idx++][i]=before;
					} else if(before==after) {
						nextMap[idx-1][i]+=after; 
						before=0;
					} else { // before != after
						nextMap[idx++][i]=after;
						before=after;
					}
				}
			}
			break;
		case DOWN:
			for(int i=0; i<N; i++) { // N개의 큐 
				int idx = N-1;
				if(q[i].isEmpty()) continue; // 빈 큐면 continue
				int before = q[i].poll(); // 큐의 첫번째 원소
				nextMap[idx--][i]=before; 
				while(!q[i].isEmpty()) {		
					int after = q[i].poll();
					if(before==0) { // 직전 원소가 그 전 원소와 합쳐졌을 때
						before = after;
						nextMap[idx--][i]=before;
					} else if(before==after) {
						nextMap[idx+1][i]+=after; 
						before=0;
					} else { // before != after
						nextMap[idx--][i]=after;
						before=after;
					}
				}
			}
			break;
		case LEFT:
			for(int i=0; i<N; i++) { // N개의 큐 
				int idx = 0;
				if(q[i].isEmpty()) continue; // 빈 큐면 continue
				int before = q[i].poll(); // 큐의 첫번째 원소
				nextMap[i][idx++]=before; 
				while(!q[i].isEmpty()) {		
					int after = q[i].poll();
					if(before==0) { // 직전 원소가 그 전 원소와 합쳐졌을 때
						before = after;
						nextMap[i][idx++]=before;
					} else if(before==after) {
						nextMap[i][idx-1]+=after; 
						before=0;
					} else { // before != after
						nextMap[i][idx++]=after;
						before=after;
					}
				}
			}
			break;
		case RIGHT:
			for(int i=0; i<N; i++) { // N개의 큐 
				int idx = N-1;
				if(q[i].isEmpty()) continue; // 빈 큐면 continue
				int before = q[i].poll(); // 큐의 첫번째 원소
				nextMap[i][idx--]=before; 
				while(!q[i].isEmpty()) {	
					int after = q[i].poll();
					if(before==0) { // 직전 원소가 그 전 원소와 합쳐졌을 때
						before = after;
						nextMap[i][idx--]=before;
					} else if(before==after) {
						nextMap[i][idx+1]+=after; 
						before=0;
					} else { // before != after
						nextMap[i][idx--]=after;
						before=after;
					}
				}
			}
			break;
    	}
	}

	private static void getElements(int[][] currentMap, int direction) {
		makeQueue(); // N개 행의 원소값 각각 저장할 리스트들
    	switch (direction) {
		case UP:
			for(int i=0; i<N; i++) { // 0행부터 큐에 삽입 (j열의 값 -> j번째 큐에 삽입)
				for(int j=0; j<N; j++) { // N개의 큐 
					if(currentMap[i][j]==0) continue;
					q[j].offer(currentMap[i][j]);
				}
			}
			break;
		case DOWN:
			for(int i=N-1; i>=0; i--) { 
				for(int j=0; j<N; j++) {
					if(currentMap[i][j]==0) continue;
					q[j].offer(currentMap[i][j]);
				}
			}
			break;
		case LEFT:
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(currentMap[i][j]==0) continue;
					q[i].offer(currentMap[i][j]);
				}
			}
			break;
		case RIGHT:
			for(int i=0; i<N; i++) { 
				for(int j=N-1; j>=0; j--) {
					if(currentMap[i][j]==0) continue;
					q[i].offer(currentMap[i][j]);
				}
			}
			break;
    	}
	}

	private static void makeQueue() {
		for(int i=0; i<N; i++) {
			q[i] = new ArrayDeque<>();
		}
	}

	private static int getMaxFromCurrentMap(int[][] map) {
		int max = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				max = Math.max(max, map[i][j]);
			}
		}
		return max;
	}	
}
