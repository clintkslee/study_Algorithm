package day3.problems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN15650_NM_이강산 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int n, m;
	static int[] res;
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		res = new int[m];
		combi(0, 1); // cnt, start
		bw.write(sb.toString());
		bw.flush();
	}
	private static void combi(int cnt, int start) {
		if(cnt == m) {
			for(int i=0; i<m; i++)
				sb.append(res[i]+" ");
			sb.append("\n");
			return;
		}
		for(int i=start; i <= n; i++) {
			res[cnt] = i;
			combi(cnt+1, i+1);
		}
		
	}
}
