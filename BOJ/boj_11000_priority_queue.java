package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <강의실 배정> G5
 * 링크
 * https://www.acmicpc.net/problem/11000
 * 요약
 * 주어진 모든 강의들을 최소한의 강의실 사용하여 배정하기
 * 풀이
 * 우선순위 큐
 */
public class boj_11000_priority_queue {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	// 강의 클래스 (시작시간, 종료시간)
	static class myClass implements Comparable<myClass> {
		int start, end;
		myClass (int start, int end) {
			this.start = start;	
			this.end = end;
		}
		@Override
		public int compareTo(myClass o) {	// 시작 시간 빠른 순 / 같다면 종료 시간 빠른 순
			if(this.start == o.start) return this.end - o.end;
			return this.start - o.start;
		}
	}

	static int N; // 강의 수
	static PriorityQueue<myClass> mc = new PriorityQueue<>();
	static int cnt;
	public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine());	
		for(int i=0; i<N; ++i) { // 강의 입력 (시작시간, 종료시간)
			st = new StringTokenizer(br.readLine()); // 시작 시간(같으면 종료시간) 빠를 수록 우선순위 높음
			mc.offer(new myClass(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		// 풀이
		solution();
		// 출력					
		bw.write(cnt+"");				
		bw.flush();
	}

	private static void solution() {
		PriorityQueue<Integer> timeTable = new PriorityQueue<>(); // 강의실 시간표(종료시간 저장)
		timeTable.offer(0);								// 시간표 초기화 (0시에 끝나는 강의실 1개)
		while(!mc.isEmpty()) {								// 모든 강의 접근 (시작시간 오름차순)
			if(mc.peek().start >= timeTable.peek()) {		// 남은 강의 중 가장 빠른 시작시간 >= 시간표에서 가장 빠른 종료시간이면 
				timeTable.poll();							// 시간표에서 업데이트
				timeTable.offer(mc.poll().end);
			} else {	// 삽입할 강의 시작시간보다 빨리 끝나는 강의실 없으면 새로운 강의실 추가
				timeTable.offer(mc.poll().end);					
			}
		}
		cnt = timeTable.size();
	}
}

