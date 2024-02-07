package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <배열돌리기 3>
 * 링크
 * https://www.acmicpc.net/problem/16935
 * 요약
 * N*M 배열을 조건에 따라 돌리기
 * 풀이
 * 노가다
 */
public class JUN16935_배열돌리기3_이강산 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N, M, cnt;	// [N][M] 배열, 연산횟수
	static int[][] arr;		// 연산 대상인 배열
	static int[][] temp, miniTemp;
	static int[] operations;// 수행될 연산들
	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cnt = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; ++j) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		operations = new int[cnt];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<cnt; ++i) {
			operations[i] = Integer.parseInt(st.nextToken());
		}
		// 풀이
		solution();
		// 출력
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j)
				sb.append(arr[i][j]+" ");
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	private static void solution() {
		for(int i=0; i<cnt; ++i) {
			switch (operations[i]) {
			case 1: op1();	break;
			case 2: op2();	break;
			case 3: op3();	break;
			case 4: op4();	break;
			case 5: op5();	break;
			case 6: op6();	break;
			}
		}
	}
	private static void op1() {	// 상하반전
		temp = new int[N][M];
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				temp[i][j] = arr[N-1-i][j];
			}
		}
		arr = temp;
	}
	
	private static void op2() {	// 좌우반전
		temp = new int[N][M];
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				temp[i][j] = arr[i][M-1-j];
			}
		}
		arr = temp;
	}
	
	private static void op3() { // 오른쪽으로 90도 회전
		temp = new int[M][N];
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				temp[j][N-1-i] = arr[i][j];	
			}
		}
		int tempX = N;
		N = M;
		M = tempX;
		arr = temp;
	}
	
	private static void op4() {	// 왼쪽으로 90도 회전
		temp = new int[M][N];
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				temp[M-1-j][i] = arr[i][j];	
			}
		}
		int tempX = N;
		N = M;
		M = tempX;
		arr = temp;
	}
	
	private static void op5() { // 4등분해서 시계방향으로 회전
		temp = new int[N][M];
		miniTemp = new int[N/2][M/2];
		// 1 -> 2
		for(int i=0; i<miniTemp.length; ++i) {
			for(int j=0; j<miniTemp[i].length; ++j) {
				miniTemp[i][j] = arr[i][j];
			}
		}
		for(int i=0; i<miniTemp.length; ++i) {
			for(int j=0; j<miniTemp[i].length; ++j) {
				temp[i][j+M/2] = miniTemp[i][j];
			}
		}
		// 2 -> 3
		for(int i=0; i<miniTemp.length; ++i) {
			for(int j=0; j<miniTemp[i].length; ++j) {
				miniTemp[i][j] = arr[i][j+M/2];
			}
		}
		for(int i=0; i<miniTemp.length; ++i) {
			for(int j=0; j<miniTemp[i].length; ++j) {
				temp[i+N/2][j+M/2] = miniTemp[i][j];
			}
		}
		// 3 -> 4
		for(int i=0; i<miniTemp.length; ++i) {
			for(int j=0; j<miniTemp[i].length; ++j) {
				miniTemp[i][j] = arr[i+N/2][j+M/2];
			}
		}
		for(int i=0; i<miniTemp.length; ++i) {
			for(int j=0; j<miniTemp[i].length; ++j) {
				temp[i+N/2][j] = miniTemp[i][j];
			}
		}
		// 4 -> 1
		for(int i=0; i<miniTemp.length; ++i) {
			for(int j=0; j<miniTemp[i].length; ++j) {
				miniTemp[i][j] = arr[i+N/2][j];
			}
		}
		for(int i=0; i<miniTemp.length; ++i) {
			for(int j=0; j<miniTemp[i].length; ++j) {
				temp[i][j] = miniTemp[i][j];
			}
		}
		arr = temp;
	}
	
	private static void op6() {	// 4등분해서 반시계방향으로 회전
		temp = new int[N][M];
		miniTemp = new int[N/2][M/2];
		// 1 -> 4
		for(int i=0; i<miniTemp.length; ++i) {
			for(int j=0; j<miniTemp[i].length; ++j) {
				miniTemp[i][j] = arr[i][j];
			}
		}
		for(int i=0; i<miniTemp.length; ++i) {
			for(int j=0; j<miniTemp[i].length; ++j) {
				temp[i+N/2][j] = miniTemp[i][j];
			}
		}
		// 2 -> 1
		for(int i=0; i<miniTemp.length; ++i) {
			for(int j=0; j<miniTemp[i].length; ++j) {
				miniTemp[i][j] = arr[i][j+M/2];
			}
		}
		for(int i=0; i<miniTemp.length; ++i) {
			for(int j=0; j<miniTemp[i].length; ++j) {
				temp[i][j] = miniTemp[i][j];
			}
		}
		// 3 -> 2
		for(int i=0; i<miniTemp.length; ++i) {
			for(int j=0; j<miniTemp[i].length; ++j) {
				miniTemp[i][j] = arr[i+N/2][j+M/2];
			}
		}
		for(int i=0; i<miniTemp.length; ++i) {
			for(int j=0; j<miniTemp[i].length; ++j) {
				temp[i][j+M/2] = miniTemp[i][j];
			}
		}
		// 4 -> 3
		for(int i=0; i<miniTemp.length; ++i) {
			for(int j=0; j<miniTemp[i].length; ++j) {
				miniTemp[i][j] = arr[i+N/2][j];
			}
		}
		for(int i=0; i<miniTemp.length; ++i) {
			for(int j=0; j<miniTemp[i].length; ++j) {
				temp[i+N/2][j+M/2] = miniTemp[i][j];
			}
		}
		arr = temp;
	}
}
