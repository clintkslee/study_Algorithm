package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <과제는 끝나지 않아!> S3
 * 링크
 * https://www.acmicpc.net/problem/17952
 * 요약
 * 주어진 로직대로 과제 수행 시 점수 구하기
 * 풀이
 * 스택 + 구현
 */
public class boj_17952 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static class Task {
		int isTask;
		int score;
		int needTime;
		public Task(int isTask, int score, int needTime) {
			this.isTask = isTask;
			this.score = score;
			this.needTime = needTime;
		}
		@Override
		public String toString() {
			return "Task [isTask=" + isTask + ", score=" + score + ", needTime=" + needTime + "]";
		}
	}
	
	static int N; // 이번 분기 minute
	static Queue<Task> q = new ArrayDeque<>();
	
	static Stack<Task> stack = new Stack<>();
	static int scoreSum = 0; // 누적 점수
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a1 = Integer.parseInt(st.nextToken());
			if(a1==1) {
				int a2 = Integer.parseInt(st.nextToken());
				int a3 = Integer.parseInt(st.nextToken());
				q.add(new Task(a1, a2, a3));
			} else {
				q.add(new Task(a1, 0, 0));
			}

			
		}
		
		Task t = null; // 현재 작업 중인 task
		for(int i=1; i<=N; i++) {
			Task temp = q.poll(); // 시간 N 일때 주어지는 task 여부 (0, 1)
			if(t==null) { // 현재 작업 중인 task 없을 때
				if(temp.isTask==1) { // 새로 뽑은 게 task 일 때
					temp.needTime--;
					if(temp.needTime==0) {
						scoreSum += temp.score;
					} else {
						t = temp;
					}
				} else { // 새로 뽑은 것도 task 아니면 stack 확인
					if(!stack.isEmpty()) {
						t = stack.pop();
						t.needTime--;
						if(t.needTime==0) {
							scoreSum += temp.score;
							t = null;
						} else {
							;
						}
					}
				}	
			} else { // 현재 작업 중인 task 있을 때
				if(temp.isTask==1) { // 새로 뽑은 게 task 일 때
					stack.add(t);
					t = temp;
					t.needTime--;
					if(t.needTime==0) {
						scoreSum += t.score;
						t = null;
					} else {
						;
					}
				} else {
					t.needTime--;
					if(t.needTime==0) {
						scoreSum += t.score;
						t = null;
					} else {
						;
					}
				}
			}
			if(t==null && !stack.isEmpty()) { // task 완료 후 t=null 되었을 때, stack 에 남아 있는 task 있으면 일 할 준비
				t = stack.pop();
			}
		}
		
		System.out.println(scoreSum);
	}
}
