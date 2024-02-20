package BOJ;

import java.awt.Point;
import java.io.*;
import java.util.*;
/*
 * 제목
 * <치킨 배달> G5
 * 링크
 * https://www.acmicpc.net/problem/15686
 * 요약
 * 도시에서 최대 m개의 치킨집만 남길 때, 치킨거리의 최소값 구하기
 * 풀이
 * 1~m 개 치킨집 선택 후, 치킨거리 업데이트
 */
public class boj_15686 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, M; // 도시 N*N, 도시에 남길 최대 M개의 치킨집
	static int[][] city;
	static ArrayList<Point> house;
	static ArrayList<Point> chicken;
	
	static int[] subsetChicken;
	static int chickenDistance = Integer.MAX_VALUE;

	final static int BLANK = 0;
	final static int HOUSE = 1;
	final static int CHICKEN = 2;
	
	
    public static void main(String[] args) throws IOException {
    	// 입력
    	st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		city = new int[N+1][N+1]; // [0] 제외
		house = new ArrayList<>(); // 집 좌표 저장
		chicken = new ArrayList<>(); // 치킨집 좌표 저장

		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				city[i][j] = temp;
				if(temp==HOUSE) house.add(new Point(j, i)); // 집 좌표 house에 유지
				if(temp==CHICKEN) chicken.add(new Point(j, i)); // 치킨집 좌표 chicken에 유지
			}
		}
		// 풀이
		solution();
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	private static void solution() {
		// chicken.size()개의 치킨집의 부분집합에 대해 치킨거리 계산 (크기가 1~m 인 부분집합에 대해서만 연산)
		for(int i=1; i<=M; i++) {
			subsetChicken = new int[i]; // 선택된 치킨집들의 인덱스 저장(chicken.get(i)해서 좌표 구할 떄 사용)
			subset(0, 0, i);	// 치킨집 최대 i개 선택 	
		}
		sb.append(chickenDistance+" ");
	}

	private static void subset(int start, int cnt, int maxCnt) {
		int chickenCnt = chicken.size(); // 치킨집 개수
		if(cnt == maxCnt) {
			int sum = 0;
			for(int i=0; i<house.size(); i++) { // 모든 집들에 대해 현재 선택된 치킨집들과의 치킨거리 계산
				int houseMin = Integer.MAX_VALUE;
				for(int j=0; j<maxCnt; j++) { // house.get(i)와 chicken.get(subsetChicken[j]) 와의 거리 구하기
					houseMin = Math.min(houseMin, Math.abs(house.get(i).x-chicken.get(subsetChicken[j]).x)+Math.abs(house.get(i).y-chicken.get(subsetChicken[j]).y));
				}
				sum += houseMin;
			}
			chickenDistance = Math.min(chickenDistance, sum);
			return;
		}
		for(int i=start; i<chickenCnt; i++) {
			subsetChicken[cnt] = i;
			subset(i+1, cnt+1, maxCnt);
		}
	}
}
