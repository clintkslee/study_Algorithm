package BOJ.G4;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <배열 돌리기 4>
 * 링크
 * https://www.acmicpc.net/problem/17406
 * 요약
 * 배열 연산 구현
 * 풀이
 * -
 */
public class boj_17406 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, M, K; // N*M 배열, 연산횟수 K
    static int[][] arr; 
    static int[][] rcs; // 연산
    public static void main(String[] args) throws IOException {
		// 입력
        st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        rcs = new int[K][3];    //[0] r, [1] c, [2] s
        for(int i=0; i<N; i++) {    // 배열 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<K; i++) {    // K개 연산 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                rcs[i][j] = Integer.parseInt(st.nextToken());
            }
        }
		// 풀이
        // for(int i=0; i<N; i++) {   // 배열 출력
        //     for(int j=0; j<M; j++) {
        //         System.out.print(arr[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        // for(int i=0; i<K; i++) {   // 연산 출력
        //     for(int j=0; j<3; j++) {
        //         System.out.print(rcs[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        solution();
		// 출력
		//bw.write(sb.toString());
	//	bw.flush();
	}

	private static void solution() {
		sb.append("Hello Algorithm!\n");
	}
}
