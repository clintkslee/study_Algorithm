package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_15649 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[] result;
	static int N, M;
	static boolean[] isUsed;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		result = new int[M];
		isUsed = new boolean[N+1];
		nm(0);
		bw.flush();
	}
	
	private static void nm(int depth) throws IOException {
		if(depth == M) {
			bw.write(Arrays.toString(result).replaceAll(",", "")
											.replaceAll("\\[", "")
											.replaceAll("\\]", "")+"\n");
			return;
		}
		for(int i=1; i<=N; i++) {
			if(!isUsed[i]) {
				isUsed[i] = true;
				result[depth] = i;
				nm(depth+1);
				isUsed[i] = false;
			}
		}
	}
}