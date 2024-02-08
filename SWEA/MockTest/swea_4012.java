package SWEA.MockTest;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <요리사>
 * 링크
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeUtVakTMDFAVH
 * 요약
 * 재료를 반반씩 쓴 음식A, 음식B 의 맛 차이 최소로 만들기
 * 풀이
 * n-1_C_n/2-1 가지 경우를 탐색
 */
public class swea_4012 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int T;	// TC 개수
	static int N;	// 재료 개수
	static int[][] arr; // 재료 간 시너지 배열
	static boolean[] foodA;	// 음식A에 들어간 재료, A에 들어가지 않은 재료는 자동으로 음식B의 재료
	static int diff;
    public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int i=1; i<=T; i++) {
			// 입력
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			foodA = new boolean[N];
			for(int j=0; j<N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0; k<N; k++) {
					arr[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			// 풀이
			diff = Integer.MAX_VALUE;
			foodA[0] = true;
			combi(1, 1);	// foodA 는 항상 [0]번 재료를 보유한 채 조합 생성 (ABC DEF 와 DEF ABC 의 diff 는 동일하므로)
			// 출력
			sb.append("#"+i+" "+diff+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static void combi(int cnt, int start) {
		if(cnt==N/2) {	// true : foodA, false : foodB
			int tasteA, tasteB;
			tasteA = getTaste(true);	// foodA 의 맛
			tasteB = getTaste(false);	// foodB 의 맛
			diff = Math.min(diff, (int)Math.abs(tasteA-tasteB));
			return;
		}
		for(int i=start; i<N; i++) {
			if(foodA[i]) continue;
			foodA[i] = true;
			combi(cnt+1, i+1);
			foodA[i] = false;
		}
	}

	private static int getTaste(boolean b) {
		int sum=0;
		int[] idx = new int[N/2]; // 재료 배열(재료 번호 보유)
		int cnt=0;
		for(int i=0; i<N; i++) {
			if(foodA[i]==b) // true 면 foodA, false 면 foodB
				idx[cnt++] = i;
		}
		for(int i=0; i<N/2; i++) { // 음식의 재료 간 시너지 계산(조합)
			for(int j=i+1; j<N/2; j++) {
				sum += arr[idx[i]][idx[j]];
				sum += arr[idx[j]][idx[i]];
			}
		}
		return sum;
	}
}
