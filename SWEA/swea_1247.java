package SWEA;

import java.awt.Point;
import java.io.*;
import java.util.*;
/*
 * 제목
 * <최적 경로> D5
 * 링크
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15OZ4qAPICFAYD
 * 요약
 * 두 좌표간 거리를 dx+dy 라 할 때, 회사 -> 고객1 -> 고객2 -> ... -> 고객N -> 집 의 최단거리 구하기
 * 풀이
 * bt
 */
public class swea_1247 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int TC;	// 테스트케이스 개수 
	static int N;	// 고객 수
	static int min; // 최단 경로
	
	static Point company;	// 회사 좌표
	static Point home;	// 집 좌표
	static Point[] customer;	// 고객들 좌표
	static boolean[] isVisited;	// [i]번 고객 방문 여부
    public static void main(String[] args) throws IOException {
		TC = Integer.parseInt(br.readLine());
		for(int i=1; i<=TC; i++) {
			// 입력
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			company = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			home = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			customer = new Point[N];
			isVisited = new boolean[N];
			for(int j=0; j<N; j++)	customer[j] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			// 풀이
			min = Integer.MAX_VALUE;	// 최단 경로
			bt(0, company, 0);	// 현재 누적된 이동거리, 현재 위치, 방문 고객 수
			// 출력
			sb.append("#"+i+" "+min+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
    
	private static void bt(int dist, Point current, int depth) {
		if(depth == N) {	// N번 방문 완료 시 현재 위치와 집과의 거리 계산 후 누적, min과 비교하여 더 작은 값으로 업데이트
			dist += (int)Math.abs(current.x-home.x) + (int)Math.abs(current.y-home.y);
			min = Math.min(min, dist);
			return;
		}
		for(int i=0; i<N; i++) {
			if(isVisited[i]==true) continue;	// 전에 방문한 곳이면 continue
			int nextDist = dist + (int)Math.abs(current.x-customer[i].x) + (int)Math.abs(current.y-customer[i].y);	// [i]고객 방문 시 누적거리
			if(nextDist <= min) {	// [i]고객 방문 후 누적된 거리가 min보다 크면 prune
				isVisited[i] = true;	// [i]고객 방문
				bt(nextDist, customer[i], depth+1);	// [i]방문 후 누적거리, [i]고객 좌표, 방문 고객 수
				isVisited[i] = false;
			} else return;
		}
	}
}
