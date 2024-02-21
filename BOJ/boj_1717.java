package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <집합의 표현> G5
 * 링크
 * https://www.acmicpc.net/problem/1717
 * 요약
 * 서로소 집합, 유니온 파인드 알고리즘 이해하기
 * 풀이
 * 유니온 파인드
 */
public class boj_1717 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n, m; // n+1개의 집합(0~n), m개의 서로소집합 연산
	static int[] parent;
    public static void main(String[] args) throws IOException {
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	parent = makeSet(n);
    	for(int i=0; i<m; i++) {
    		// 입력
    		st = new StringTokenizer(br.readLine());
    		int op = Integer.parseInt(st.nextToken());
    		int e1 = Integer.parseInt(st.nextToken());
    		int e2 = Integer.parseInt(st.nextToken());
    		// 풀이
    		if(op==0) { // union
    			union(e1, e2);
    		} else { // find
    			if(find(e1)==find(e2)) sb.append("YES\n");
    			else sb.append("NO\n");
    		}
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
		while(!(parent[e]==e)) 
			e = parent[e];
		return e;
	}

	private static int[] makeSet(int N) {
		int[] temp = new int[N+1];
		for(int i = 0; i < temp.length; i++) temp[i]=i;
		return temp;
	}
}
