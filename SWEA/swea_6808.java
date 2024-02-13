package SWEA.D3;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <규영이와 인영이의 카드게임>
 * 링크
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWgv9va6HnkDFAW0
 * 요약
 * 1~18 카드 9장씩 나눠갖기, 규영이가 입력 순서대로 카드 드로우 시, 인영이가 카드를 내는 모든 경우에 대해 규영이의 승패 횟수 구하기
 * 풀이
 * next permutation
 */
public class swea_6808 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int T; // 테스트케이스 개수
	static int[] Gyuyoung;	
	static int[] Inyoung;
	static int gyuWin, gyuLose;
    public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());	// TC 개수
		for(int i=1; i<=T; i++) {
			// 입력
			gyuWin=0;	// 규영 승 횟수
			gyuLose=0;	// 규영 패 횟수
			st = new StringTokenizer(br.readLine());	
			Gyuyoung = new int[9];
			Inyoung = new int[9];
			for(int j=0; j<9; j++) Gyuyoung[j]=Integer.parseInt(st.nextToken());	// 규영이 카드 9장
			Set<Integer> set = new HashSet<>();	// 인영이 카드 9장 (전체-규영이카드)
			for(int j=1; j<=18; j++) set.add(j);
			for(int j=0; j<9; j++) set.remove(Gyuyoung[j]);
			for(int j=1, cnt=0; j<=18; j++) if(set.contains(j)) Inyoung[cnt++] = j;
			// 풀이
			solution();
			// 출력
			sb.append("#"+i+" "+gyuWin+" "+gyuLose+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static void solution() {
		int sumGyu, sumIn;
		do {
			sumGyu=0;
			sumIn=0;
			for(int i=0; i<9; i++) {
				if(Gyuyoung[i] > Inyoung[i]) sumGyu += (Gyuyoung[i]+Inyoung[i]);
				else sumIn += (Gyuyoung[i]+Inyoung[i]);
			}
			if(sumGyu > sumIn) gyuWin++;
			else if(sumGyu==sumIn);
			else gyuLose++;
		} while(np(Inyoung));
	}
	
	private static boolean np(int[] p) { // next permutation
		final int N = p.length;
		int i = N-1;
		while(i>0 && p[i-1]>=p[i]) --i; 
		if(i==0) return false;
		int j = N-1;
		while(p[i-1] >= p[j]) --j;
		swap(p, i-1, j);
		int k = N-1; 
		while(i<k) swap(p, i++, k--);
		return true;
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
