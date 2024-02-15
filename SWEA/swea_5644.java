package SWEA;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <무선충전> D5
 * 링크
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRDL1aeugDFAUo
 * 요약
 * 두 유저 A, B의 이동경로, BC개의 무선충전기에 대한 정보가 주어졌을 떄, 두 유저가 이동하면서 충전한 양의 최대치 구하기
 * 풀이
 * 구현 ^^
 */
public class swea_5644 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st, st2;
	static StringBuilder sb = new StringBuilder();

	static int T;	// 테스트케이스 개수 
	static int M, A; // 총 이동시간 M, 무선충전기(BC) 개수 A
	
//	STAY=0, UP=1, RIGHT=2, DOWN=3, LEFT=4;
	final static int[] dx = {0, 0, 1, 0, -1};
	final static int[] dy = {0, -1, 0, 1, 0};	
	static int[] userA, userB;	// 유저A, B의 이동 경로
	static BatteryCharger[] bc;	// A개 무선충전기들의 정보
	static int sum;	// 유저A, B의 충전량 누적
	
    public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			// 입력
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());	
			userA = new int[M];
			userB = new int[M];
			bc = new BatteryCharger[A];
			st = new StringTokenizer(br.readLine());
			st2 = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				userA[i] = Integer.parseInt(st.nextToken());
				userB[i] = Integer.parseInt(st2.nextToken());
			}
			
			for(int i=0; i<A; i++) {
				st = new StringTokenizer(br.readLine());
				bc[i] = new BatteryCharger(
						Integer.parseInt(st.nextToken()), 
						Integer.parseInt(st.nextToken()), 
						Integer.parseInt(st.nextToken()), 
						Integer.parseInt(st.nextToken()));
			}
			Arrays.sort(bc);	// 성능(p)에 대해 내림차순 정령된 BC들
			// 입력검증
//			System.out.println("M : "+M);
//			System.out.println("BC : "+A+"개");
//			System.out.println("user A : "+Arrays.toString(userA));
//			System.out.println("user B : "+Arrays.toString(userB));
//			for(int i=0; i<A; i++) {
//				System.out.println(bc[i].toString());
//			}
			// 풀이
			solution();
			// 출력
			sb.append("#"+tc+" "+sum+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
    
    private static void solution() {
//    	System.out.println("Battery Status :"+Arrays.toString(bc));
    	sum=0;
    	Person a = new Person(1, 1);
    	Person b = new Person(10, 10);
    	// 초기 상태에서 충전
//    	System.out.println("T = "+0);
    	charge(a, b);
    	for(int i=0; i<M; i++) {
//    		System.out.println("T = "+(i+1));
    		a.move(userA[i]);	// 이동 후 현 위치에서 각 BC들까지의 거리 계산
    		b.move(userB[i]);
    		charge(a, b);
    	}
	}

    //유저A, 유저B가 현재 위치에서 충전
	private static void charge(Person a, Person b) {
		int[] AchargerNum=new int[]{-1, -1}, BchargerNum=new int[]{-1, -1};	// 현 위치에서 선택 가능한 배터리충전기들 번호
		int cntA=0, cntB=0;
//		System.out.println("current position");
//		System.out.println("A "+a.x+" "+a.y);
//		System.out.println("B "+b.x+" "+b.y);
//		System.out.println("current distance to bc");
//		System.out.println(Arrays.toString(a.distance));
//		System.out.println(Arrays.toString(b.distance));
		for(int i=0; i<A; i++) { 
			if(cntA<2) {	// A가 선택할 수 있는 배터리충전기 번호를 성능 순으로 저장, [0]이 -1이면 선택가능한 충전기 없음
				if(a.distance[i]<=bc[i].c)
				AchargerNum[cntA++] = i;
			}
			if(cntB<2) {
				if(b.distance[i]<=bc[i].c)
				BchargerNum[cntB++] = i;
			}
		}
//		System.out.println("current charging!");
//		System.out.println("A avail charge : " +Arrays.toString(AchargerNum));
//		System.out.println("B avail charge : " +Arrays.toString(BchargerNum));
		if(AchargerNum[0]==-1 && BchargerNum[0]==-1) {	// 둘 다 선택지 없는 경우
			;
		} else if(AchargerNum[0]!=-1 && BchargerNum[0]==-1) { // A만 충전 가능한 경우
			sum += bc[AchargerNum[0]].p;
		} else if(AchargerNum[0]==-1 && BchargerNum[0]!=-1) { // B만 충전 가능한 경우
			sum += bc[BchargerNum[0]].p;
		} else { // 둘 다 충전 가능한 경우
			if(AchargerNum[0]!=BchargerNum[0]) {	// 각각 충전량 제일 많은 경우의 충전기가 다른 경우
				sum += bc[AchargerNum[0]].p;
				sum += bc[BchargerNum[0]].p;
			}
			else {	// 충전량 제일 많은 경우의 충전기가 같은 경우
				if(AchargerNum[1]==-1 && BchargerNum[1]==-1) { // 두번째 가능성 둘 다 없으면
					sum += bc[AchargerNum[0]].p;
				} else if(AchargerNum[1]!=-1 && BchargerNum[1]==-1) {	// A만 두번째 가능성 있는 경우
					int case1 = bc[AchargerNum[0]].p;
					int case2 = bc[AchargerNum[1]].p + bc[BchargerNum[0]].p;
					sum += Math.max(case1, case2);
				} else if(AchargerNum[1]==-1 && BchargerNum[1]!=-1) {	// B만 두번째 가능성 있는 경우
					int case1 = bc[AchargerNum[0]].p;
					int case2 = bc[AchargerNum[0]].p + bc[BchargerNum[1]].p;
					sum += Math.max(case1, case2);
				} else {	// 둘 다 두번째 가능성 있음
					if(AchargerNum[1]!=BchargerNum[1]) { // 두번째로 충전량 많은 충전기는 다른 경우
						int case1 = bc[AchargerNum[0]].p;	// 둘 다 첫번째 충전기 선택
						int case2 = bc[AchargerNum[0]].p + bc[BchargerNum[1]].p;	// A는 첫번째, B는 두번째 충전기 선택
						int case3 = bc[AchargerNum[1]].p + bc[BchargerNum[0]].p;	// A는 두번째, B는 첫번째 충전기 선택
						sum += Math.max(case1, Math.max(case2, case3));
					} else { // 두번째로 충전량 많은 충전기도 같은 경우
						int case1 = bc[AchargerNum[0]].p;	// 둘 다 첫번째 충전기 선택
						int case2 = bc[AchargerNum[0]].p + bc[BchargerNum[1]].p;	// 둘이 다른 충전기 선택
						sum += Math.max(case1, case2);
					}
				}
			}
		}
//		System.out.println("current sum : "+sum);
	}

	static class BatteryCharger implements Comparable<BatteryCharger> {
    	int x;	// x좌표
    	int y;	// y좌표
    	int c;	// 충전범위(coverage)
    	int p;	// 성능(performance)
    	
		public BatteryCharger(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}

		@Override
		public String toString() {
			return "BatteryCharger [x=" + x + ", y=" + y + ", c=" + c + ", p=" + p + "]";
		}

		@Override
		public int compareTo(BatteryCharger o) {	// 충전 성능 순으로 정렬
			return o.p-this.p;
		}
    }
	
	static class Person {
    	int x;	// x좌표
    	int y;	// y좌표
    	int[] distance;
    	
		public Person(int x, int y) {
			this.x = x;
			this.y = y;
			distance = new int[A];	// 각 BC와의 거리 정보 유지
			setDistance();
		}
		
		void move(int direction) {
			this.x += dx[direction];
			this.y += dy[direction];
			setDistance();
		}
		
		void setDistance() {
			for(int i=0; i<A; i++) {
				this.distance[i] = (int)Math.abs(this.x-bc[i].x)+(int)Math.abs(this.y-bc[i].y);
			}
		}
	}
}
