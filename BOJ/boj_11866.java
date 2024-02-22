package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <요세푸스 문제 0> S5
 * 링크
 * https://www.acmicpc.net/problem/11866
 * 요약
 * 요세푸스 문제
 * 풀이
 * 큐
 */
public class boj_11866 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, K;
    static Queue<Integer> q = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
		// 입력
        st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// 풀이
		solution();
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	private static void solution() {
        for(int i=1; i<=N; i++) {
            q.offer(i);
        }
        sb.append("<");
		while(!q.isEmpty()) {
            for(int i=1; i<=K; i++) {
                if(i==K) sb.append(q.poll()+", ");
                else q.add(q.poll());
            }
        }
        sb.deleteCharAt(sb.length()-1).deleteCharAt(sb.length()-1).append(">");
	}
}
