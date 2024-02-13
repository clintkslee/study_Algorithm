package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class swea_2001 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int T;   	// TC
	static int N;   	// 영역 크기
	static int M;		// 파리채 크기
	static int[][] arr;	// 탐색할 영역 
	static int maxCnt;	// 잡은 파리 최대값 저장
	public static void main(String[] args)throws IOException{
		T = Integer.parseInt(br.readLine());		// TC 입력
		for(int k=0; k<T; k++) {					// TC 만큼 반복
			// 입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 탐색 영역 한 변 크기 N*N
			M = Integer.parseInt(st.nextToken());	// 파리채 한 변 크기 M*M
			arr = new int[N][N];					// 탐색 영역
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 연산
			maxCnt = 0;
			for(int i=0; i<=N-M; i++) {				// 한 번 M 으로 탐색
				for(int j=0; j<=N-M; j++) {
					maxCnt = Math.max(maxCnt, getFlies(i, j, M));
				}
			}
			sb.append("#"+(k+1)+" "+maxCnt+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static int getFlies(int i, int j, int m) {
		int temp = 0;		
		for(int a=i; a<i+m; a++) {					//[i][j]을 좌상으로 하는 M*M 크기 정사각형 저장
			for(int b=j; b<j+m; b++) {
				temp += arr[a][b];
			}
		}
		return temp;
	}
	
}
