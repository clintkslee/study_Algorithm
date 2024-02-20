package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <수 정렬하기 2> S5
 * 링크
 * https://www.acmicpc.net/problem/2751
 * 요약
 * N(1~100만)개 이하의 수 정렬
 * 풀이
 * 우선순위 큐 사용
 */
public class boj_2751 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) 
			pq.offer(Integer.parseInt(br.readLine()));
		// 풀이
		while(!pq.isEmpty()) 
			sb.append(pq.poll()+"\n");
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}
}
