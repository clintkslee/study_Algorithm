package BOJ;

import java.io.*;
import java.util.*;

public class boj_2164 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N;
	public static void main(String[] args) throws IOException {
		Queue<Integer> q = new ArrayDeque<>();
		N = Integer.parseInt(br.readLine());
		for(int i=1; i<=N; i++) 
			q.offer(i);
		while(q.size()>1) {
			q.poll();
			q.offer(q.poll());
		}
		bw.write(q.peek().toString());
		bw.flush();
	}
}
