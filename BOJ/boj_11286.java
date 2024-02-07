package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <절댓값 힙>
 * 링크
 * https://www.acmicpc.net/problem/11286
 * 요약
 * N개의 정수 x에 대해 abs(x)가 작은 순으로 출력하기
 * 풀이
 * 우선순위 큐 사용
 */
public class JUN11286_절댓값_힙_이강산 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	
	static int N;	// 연산 개수
	static int x, absx;	// N개의 정수 x
	static PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
		if(Math.abs(o1)==Math.abs(o2)) return o1-o2;
		return Math.abs(o1)-Math.abs(o2);
	});
	public static void main(String[] args) throws IOException {
		// 입력, 풀이
		N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; ++i) {
			x = Integer.parseInt(br.readLine());
			if(x!=0) pq.offer(x);	
			else {	// x=0 이면 poll()
				if(pq.isEmpty())
					sb.append("0\n");
				else
					sb.append(pq.poll()+"\n");
			}
		}
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}
}
