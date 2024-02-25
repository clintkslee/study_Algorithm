package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <회전 초밥> G4
 * 링크
 * https://www.acmicpc.net/problem/15961
 * 요약
 * 주어진 입력에서 연속된 숫자 k개를 탐색할 때, 최대한 많은 가짓수인 경우 구하기
 * 풀이
 * 투포인터
 */
public class boj_15961 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	
	static int N, d, k ,c; // 벨트에 놓인 접시 수 N, 초밥의 가짓수 d, 연속해서 먹는 접시의 수 k, 쿠폰번호 c 
	static int[] dish; 
	static int[] have; // 1번초밥 ~ d번초밥
	static int max = 0;
    public static void main(String[] args) throws IOException {
		// 입력
    	st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		dish = new int[N];
		have = new int[d+1];
		for(int i=0; i<N; i++) dish[i] = Integer.parseInt(br.readLine());
		// 풀이			
		solution();
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}
	private static void solution() {
		int tempMax=0;
		have[c]++; // 쿠폰 적용
		int p=0, q;
		
		for(q=0; q<k; q++)	// 초기 설정 p=0, q=k	
			have[dish[q]]++;
		for(int i=1; i<=d; i++)	
			if(have[i]>0) 
				tempMax++;
		max = tempMax;
		for(int i=1; i<N; i++) { // N개의 시작 위치
			have[dish[p%N]]--;
			if(have[dish[p%N]]==0) tempMax--;
			p++;
			have[dish[q%N]]++;
			if(have[dish[q%N]]==1) tempMax++;
			q++;
			max = Math.max(max, tempMax);
		}
		sb.append(max);
	}
}
