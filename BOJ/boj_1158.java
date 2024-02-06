package BOJ;

import java.io.*;
import java.util.*;

public class boj_1158 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Deque<Integer> dq = new ArrayDeque<>();
		for(int i=1 ; i<=N; i++)	dq.add(i);
		// 풀이
		sb.append("<");
		while(!dq.isEmpty()) {
			int cnt = K-1;
			while(cnt-- > 0)	dq.offerLast(dq.pollFirst());
			sb.append(dq.pollFirst()+", ");
		}
		// 출력
		sb.delete(sb.length()-2, sb.length()).append(">");
		bw.write(sb.toString());
		bw.flush();
	}
}
