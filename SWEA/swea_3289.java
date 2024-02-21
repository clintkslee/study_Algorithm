package SWEA;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <서로소 집합> D4
 * 링크
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBJKA6qr2oDFAWr
 * 요약
 * 서로소 집합, 유니온 파인드 알고리즘 이해하기
 * 풀이
 * 유니온 파인드
 */
public class swea_3289 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int T; // tc 개수
	static int n, m; // n개의 집합, m개의 연산
	static int[] parent;
    public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			// 입력
			sb.append("#"+tc+" ");
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
	    	m = Integer.parseInt(st.nextToken());
	    	parent = makeSet(n); 	
	    	for(int i=0; i<m; i++) {
	    		st = new StringTokenizer(br.readLine());
	    		int op = Integer.parseInt(st.nextToken());
	    		int e1 = Integer.parseInt(st.nextToken());
	    		int e2 = Integer.parseInt(st.nextToken());
	    		// 풀이
	    		if(op==0) { // union
	    			union(e1, e2);
	    		} else { // find
	    			if(find(e1)==find(e2)) sb.append("1");
	    			else sb.append("0");
	    		}
	    	}
	    	sb.append("\n");
		}
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	private static void union(int e1, int e2) {
		int s1 = find(e1);
		int s2 = find(e2);
		parent[s2] = s1;
	}

	private static int find(int e) {
		if(parent[e]==e) return e;
		else return parent[e]=find(parent[e]);
	}

	private static int[] makeSet(int N) {
		int[] temp = new int[N+1];
		for(int i = 1; i < temp.length; i++) temp[i]=i;
		return temp;
	}
}
