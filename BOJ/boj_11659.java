package day3.problems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN11659_PrefixSum4_이강산 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int n, m, i, j, sum;
	static int[] nums;
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		nums = new int[n+1]; // [0] 제외
		st = new StringTokenizer(br.readLine());
		
		// prefix sum 
		for(int i=1; i<=n; i++) {
			sum += Integer.parseInt(st.nextToken());
			nums[i] = sum;
		}
		for(int a=0; a<m; a++) {
			st = new StringTokenizer(br.readLine());
			i = Integer.parseInt(st.nextToken());
			j = Integer.parseInt(st.nextToken()); // [i]~[j] 합 구하기
			
			sb.append((nums[j]-nums[i-1])+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
}
