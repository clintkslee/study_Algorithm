package SWEA.D3;

import java.io.*;
import java.util.*;
/*
* 제목
* <한빈이와 Spot Mart>
* 링크
* https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AW8Wj7cqbY0DFAXN
* 요약
* 한빈이가 들고 다닐 수 있는 과제들의 최대 무게 합 nC2
*/
public class swea_9229 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int TC;			// TC 개수
	static int N, M;		// 과자 봉지 개수, 무게 합 제한
	static int[] snacks;	// 과자 봉지들 무게 저장 배열
	static int[] ans;
	static int maxWeight;
	public static void main(String[] args) throws IOException {
		TC = Integer.parseInt(br.readLine());
		for(int i=1; i<=TC; ++i) {	// TC 만큼 반복
			// 입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			snacks = new int[N];
			ans = new int[2];
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++)	
				snacks[j] = Integer.parseInt(st.nextToken());
			// 풀이, 출력
			maxWeight = -1;
			solution(0, 0);		
			sb.append("#"+i+" "+maxWeight+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	private static void solution(int start, int depth) {	// nC2 
		if(depth == 2) {
			int temp = ans[0] + ans[1];
			if(temp <= M)	maxWeight = Math.max(maxWeight, temp);
			return;
		}
		for(int i=start; i<N; i++) {
			ans[depth] = snacks[i];
			solution(i+1, depth+1);
		}
	}	
}

