package BOJ;

import java.io.*;
import java.util.*;

public class boj_12891 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int S, P;
	static int[] DNA = new int[4];
	static StringBuilder DNAString = new StringBuilder();
	static char[] DNAchar;
	static int ans;
	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());			// 원본 문자열 길이
		P = Integer.parseInt(st.nextToken());			// 암호 문자열 길이
		DNAString.append(" ").append(br.readLine());	// [0]번 제외 위해 공백 추가
		DNAchar = DNAString.toString().toCharArray();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++)
			DNA[i] = Integer.parseInt(st.nextToken());	// [0]A [1]C [2]G [3]T  PW로 인정받기 위한 최소 개수
		
		// 누적합	
		int[] A, C, G, T;
		A = new int[DNAchar.length];					// 누적합. [n] 은 1~n번째까지 탐색 후 찾아 낸 해당 문자의 개수
		C = new int[DNAchar.length];
		G = new int[DNAchar.length];
		T = new int[DNAchar.length];
		for(int i=1; i<DNAchar.length; i++) {
			switch (DNAchar[i]) {
				case 'A': A[i]++; break;
				case 'C': C[i]++; break;
				case 'G': G[i]++; break;
				case 'T': T[i]++; break;
			}
			A[i] += A[i-1];
			C[i] += C[i-1];
			G[i] += G[i-1];
			T[i] += T[i-1];
		}
		int cnt = S - P;	// 반복 횟수
		for(int i=0; i<=cnt; i++) {				
			if(A[P+i]-A[i]>=DNA[0] && 				// A[P+i] : P+i 까지 A 개수, A[i] : i 까지의 A 개수
					C[P+i]-C[i]>=DNA[1] && 
					G[P+i]-G[i]>=DNA[2] && 
					T[P+i]-T[i]>=DNA[3]) 
				ans++;
		}
		bw.write(ans+"");
		bw.flush();
	}
}
