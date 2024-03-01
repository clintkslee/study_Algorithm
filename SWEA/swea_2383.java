package SWEA;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <점심 식사시간> unknown
 * 링크
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5-BEE6AK0DFAVl
 * 요약
 * 사람들이 탈출하는 데 걸리는 시간 최소값 구하기
 * 풀이
 * 각 사람마다 탈출할 문 선택(poset) 후 시간 계산
 */
public class swea_2383 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static class Pos{
		int y, x;

		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static class Person implements Comparable<Person>{
		int distance; // 계단까지의 거리
		int time; // 계단 내려가는 데 걸리는 시간
		public Person(int distance, int time) {
			this.distance = distance;
			this.time = time;
		}
		@Override
		public int compareTo(Person o) {
			if(this.distance==o.distance) return this.time - o.time; 
			return this.distance - o.distance;
		}
		@Override
		public String toString() {
			return "Person [distance=" + distance + ", time=" + time + "]";
		}
	}

	static int N;
	static int[][] map;
	static ArrayList<Pos> pList;
	static ArrayList<Pos> sList;
	static int[][] dist;
	static int[] door; // i번째 사람은 [i] 문으로 탈출 (0, 1 값)
	static int minTime;
	
    public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			pList = new ArrayList<>();
			sList = new ArrayList<>();
			// 입력
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]==1) pList.add(new Pos(i, j)); // 사람 좌표 저장
					else if(map[i][j]!=0) sList.add(new Pos(i, j)); // 계단 좌표 저장
				}
			}
			dist = new int[pList.size()][sList.size()]; // [i][j] i번째 사람과 j번째 계단의 거리 ([i][j] 만큼 이동해야 계단 도착)
			for(int i=0; i<pList.size(); i++) {
				int px = pList.get(i).x;
				int py = pList.get(i).y;
				for(int j=0; j<sList.size(); j++) {
					int sx = sList.get(j).x; 
					int sy = sList.get(j).y; 
					dist[i][j] = Math.abs(px-sx) + Math.abs(py-sy);
				}
			}
			// 풀이
			door = new int[pList.size()];
			minTime = Integer.MAX_VALUE;
			poset(0);
			// 출력
			sb.append("#"+tc+" "+minTime+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static void poset(int depth) {
		if(depth == pList.size()) { // 사람들 탈출할 문 선택 완료 (0번문, 1번문)
			minTime = Math.min(minTime, getEscapeTime());
			return;			
		}
		door[depth] = 0;
		poset(depth+1);
		door[depth] = 1;
		poset(depth+1);
	}

	// 사람 번호 i = 0~pCnt-1
	// i번째 사람이 탈출하는 문 door[i]
	// i번째 사람과 door[i]번 문의 거리 dist[i][door[i]]
	// door[i] 내려가는 데 걸리는 시간 map[sList.get(door[i]).y][sList.get(door[i]).x]
	private static int getEscapeTime() {
		int pCnt = pList.size(); // 사람 수, 0번 사람 ~ pCnt-1번 사람
		ArrayList<Person> tos0List = new ArrayList<>(); // 0번 계단으로 향하는 중인 사람들, 각 사람들은 0번 계단까지의 거리와 계단 높이를 갖고 있음
		ArrayList<Person> downs0List = new ArrayList<>(); // 0번 계단에서 내려가는 중인 사람들, 최대 3명까지만 입장 가능, 입장 못한 사람들은 tos0List 에서 대기중
		ArrayList<Person> tos1List = new ArrayList<>(); 
		ArrayList<Person> downs1List = new ArrayList<>(); 
		for(int i=0; i<pCnt; i++) {
			if(door[i]==0)	tos0List.add(new Person(dist[i][0], map[sList.get(door[i]).y][sList.get(door[i]).x]));
			else tos1List.add(new Person(dist[i][1], map[sList.get(door[i]).y][sList.get(door[i]).x]));
		}
		Collections.sort(tos0List); // 계단까지의 거리가 짧은 순으로 정렬
		Collections.sort(tos1List);
		
		int time = 0;
		while(tos0List.size()!=0 || tos1List.size()!=0 || downs0List.size()!=0 || downs1List.size()!=0) { // 문으로 향하는 사람들도 없고, 계단 내려가는 사람도 없어야 반복 종료
			time++;
			getOut(downs0List);
			getOut(downs1List);
			canGoDown(tos0List, downs0List);
			canGoDown(tos1List, downs1List);
			goDown(downs0List);
			goDown(downs1List);
			toStair(tos0List);
			toStair(tos1List);
		}
		return time;
	}
	
	// 계단 다 내려간 사람들 내쫒기
	private static void getOut(ArrayList<Person> downStairList) {
		if(!downStairList.isEmpty()) {
			for(int i=0; i < downStairList.size();) {	
				if(downStairList.get(i).time==0) {
					downStairList.remove(0);
				} else i++;
			}
		}
	}
	
	// 입구 도착한 사람들 내려보내기
	private static void canGoDown(ArrayList<Person> toStairList, ArrayList<Person> downStairList) {
		if(!toStairList.isEmpty()) {
			for(int i = 0; i < toStairList.size();) {
				if(toStairList.get(i).distance==0 && downStairList.size()<3) {
					downStairList.add(toStairList.get(i));
					toStairList.remove(i);
				} else i++;
			}
		}
	}
	
	// 계단 내려가기
	private static void goDown(ArrayList<Person> downStairList) {
		if(!downStairList.isEmpty()) {
			for(int i = 0; i < downStairList.size(); i++) {
				downStairList.get(i).time--;
			}
		}
		
	}
	
	// 계단으로 향하기
	private static void toStair(ArrayList<Person> toStairList) {
		if(!toStairList.isEmpty()) {
			for(int i = 0; i < toStairList.size(); i++) {
				if(toStairList.get(i).distance!=0)
					toStairList.get(i).distance--;
			}
		}
	}
}