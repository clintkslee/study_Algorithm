package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <스네이크버드>
 * 링크
 * https://www.acmicpc.net/problem/16435
 * 요약
 * 뱀이 커질 수 있는 최대 길이 구하기
 * 풀이
 * min heap
 */
public class boj_16435 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, L; // 과일 개수 N, 스네이크버드 초기 길이 L 
	static PriorityQueue<Integer> fruits = new PriorityQueue<>();	// N개의 과일 높이 낮은 순
    public static void main(String[] args) throws IOException {
		// 입력
    	st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			fruits.offer(Integer.parseInt(st.nextToken()));
		// 풀이
		solution();
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	private static void solution() {
		while(!fruits.isEmpty()) {	// 더이상 남은 과일이 없거나
			if(fruits.peek()>L)	break;	// 뱀길이 이하 높이에 위치한 과일 없으면 break
			fruits.poll();
			L++;
		}
		sb.append(L+"");
	}
}
