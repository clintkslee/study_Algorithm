package day3.problems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN11660_PrefixSum5_이강산 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int n, m;
	static int[][] nums, sums;
	static int ax, ay, bx, by;
	static int[] range = new int[4];
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());	// 표의 크기 N x N
		m = Integer.parseInt(st.nextToken());	// 합 구하는 횟수		
		nums = new int[n+1][n+1]; 				// [0] 제외
		sums = new int[n+1][n+1];				// 누적합
		// 표 입력
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				nums[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 누적 합
		for(int i=1; i<=n; i++) {				
			for(int j=1; j<=n; j++) {
				sums[i][j] = nums[i][j] + sums[i][j-1] + sums[i-1][j] - sums[i-1][j-1];
			}
		}
		// 구간 합
		for(int a=0; a<m; a++) {
			// 범위 입력
			st = new StringTokenizer(br.readLine());
			ax = Integer.parseInt(st.nextToken());
			ay = Integer.parseInt(st.nextToken());
			bx = Integer.parseInt(st.nextToken());
			by = Integer.parseInt(st.nextToken());
			// 합
			int sum = sums[bx][by]- sums[ax-1][by] - sums[bx][ay-1] + sums[ax-1][ay-1];
			sb.append(sum+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
}
