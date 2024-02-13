package SWEA;

import java.io.*;
import java.util.*;
/*
* 제목
* <사칙연산 유효성 검사>
* 링크
* https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV141176AIwCFAYD
* 요약
* 트리로 주어진 수식이 유효한 지 검사
*/
public class swea_1233_using_trait_of_tree {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N;			// 노드 개수
	static String[] arr;	// Tree
	public static void main(String[] args) throws IOException {
		for(int i=1; i<=10; ++i) {	// TC 10개 고정
			// 입력
			N = Integer.parseInt(br.readLine());
			arr = new String[N+1];
			for(int j=1; j<=N; j++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken();
				arr[j] = st.nextToken();
			}
			// 풀이
			boolean ans = solution();
			// 출력 
			if(ans) sb.append("#"+i+" "+1);
			else sb.append("#"+i+" "+0);
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	private static boolean solution() { // 브랜치 노드인데 숫자거나 리프 노드인데 연산자면 false
		for(int i=1; i<=N; ++i) {
			if(i*2>N) {	// 리프 노드 : 숫자로 변환 안되면(연산자이면) false
				try {Integer.parseInt(arr[i]);} 
				catch (NumberFormatException e){return false;}
			}
			else if(i*2+1<=N) {	// 브랜치 노드 : 숫자로 변환 되면 false
				try {Integer.parseInt(arr[i]);	return false;}	
				catch (NumberFormatException e){;}
			}
		}
		return true;
	}	
}

