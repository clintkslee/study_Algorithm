package BOJ;

import java.io.*;
import java.util.*;

public class boj_2493 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N;
	static int towers[];
	
	public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine());
		towers =new int[N+1];		// [0] 제외
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; ++i)
			towers[i] = Integer.parseInt(st.nextToken());
		// 풀이
		solution();
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	private static void solution() {
		Stack<Integer> towerHeight = new Stack<Integer>();
		Stack<Integer> towerIdx = new Stack<Integer>();
		towerHeight.add(0);
		towerIdx.add(0);
		for(int i=1; i<towers.length; ++i ) {
			if(towerHeight.peek()<towers[i]) {										// 스택 안 탑보다 지금 탑이 더 클 때
				while(towerHeight.peek() < towers[i] && towerHeight.size()>1) {		// 더 큰 탑 나오거나 탑 스택 내 탑이 없을때까지 pop()
					towerHeight.pop();					
					towerIdx.pop();
				}
				sb.append(towerIdx.peek()+" ");
				towerHeight.add(towers[i]);
				towerIdx.add(i);
			}
			else {																	// 스택 안 탑보다 지금 탑이 더 작을 때
				sb.append(towerIdx.peek()+" ");										// 스택 안 탑이 레이저 수신, 탑 추가
				towerHeight.add(towers[i]);
				towerIdx.add(i);
			}			
		}
	}
}
