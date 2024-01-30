package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj_1244 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int n;					// 스위치 개수
	static int[] switches;			// 스위치 상태 배열
	static int student;				// 학생 수
	static int[][] students;
	
	public static void main(String[] args) throws IOException {
		n = Integer.parseInt(br.readLine());
		switches = new int[n+1];	// [0] 제외
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {							
			switches[i] = Integer.parseInt(st.nextToken());
		}
		student = Integer.parseInt(br.readLine());			
		students = new int[student][2];
		for(int i=0; i<student; i++) {
			st = new StringTokenizer(br.readLine());
			students[i][0] = Integer.parseInt(st.nextToken());	// 성별 남1 여2
			students[i][1] = Integer.parseInt(st.nextToken());	// 주어진 수
			
			if(students[i][0]==1) boy(students[i][1]);			// 학생 성별에 따른 연산
			else girl(students[i][1]);		
		}
		
		// 출력
		for(int i=1; i<=n; i++) {
			sb.append(switches[i]+" ");
			if(i%20==0) sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	
	private static void boy(int k) {				// 남학생일 경우 수행
		for(int i=1; i<=n; i++) {
			if(i%k==0) toggle(i);
		}
	}
	
	private static void girl(int k) {				// 여학생일 경우 수행 
		toggle(k);
		int lo = k-1, up = k+1;
		while(true) {
			if(1<=lo && up<=n && switches[lo]==switches[up]) {
				toggle(lo--);
				toggle(up++);
			} else 
				break;
		}
	}
	
	private static void toggle(int k) {				// 스위치 토글 함수
		if(switches[k] == 0) switches[k] = 1;
		else switches[k] = 0;
	}
	
}
