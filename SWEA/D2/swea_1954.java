package day3.problems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SW1954_Snail_이강산 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;											// 배열 크기 N * N
	static int[][] arr;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());			// TC 개수
		for(int i=0; i<T; i++) {
			N = Integer.parseInt(br.readLine());			// 달팽이 크기
			arr = new int[N][N];
			cnt = 1;
			drawOutline(0, N);								// depth, N
			sb.append("#"+(i+1)+"\n");
			for(int j=0; j<N; j++) {
				for(int k=0; k<N; k++)	sb.append(arr[j][k]+" ");
				sb.append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static void drawOutline(int depth, int n) {
		if(n<=1) {
			if(N%2==1)
				arr[N/2][N/2] = cnt; // 홀수이면 가운데 값
			return;
		}
		for(int i=depth; i<N-depth; i++)		arr[depth][i] = cnt++; 		// 상
		for(int i=depth+1; i<N-depth; i++)		arr[i][N-1-depth] = cnt++;  // 우		
		for(int i=N-2-depth; i>=depth; i--)		arr[N-1-depth][i] = cnt++; 	// 하	 			
		for(int j=N-2-depth; j>=1+depth; j--)	arr[j][depth] = cnt++; 		// 좌		
		drawOutline(depth+1, n-2);
	}
	
	
}
