package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <점프> S1
 * 링크
 * https://www.acmicpc.net/problem/1890
 * 요약
 * [0][0]에서 시작, 배열 값만큼 오른쪽이나 밑으로 이동하여 [N-1][N-1]으로 이동 가능한 경우의 수 구하기
 * 풀이
 * dp
 */
public class boj_1890 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N;
    static int[][] map;
    static int[][][] d;
    public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        d = new int [N][N][2]; // [n][n] 에 [0]왼쪽에서 이동해 도착한 경우, [1]위에서 내려와서 도착한 경우
		// 풀이
		d[0][0][0] = 0;
        d[0][0][1] = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(j+map[i][j]<N) d[i][j+map[i][j]][0]+=(d[i][j][0]+d[i][j][1]);
                if(i+map[i][j]<N) d[i+map[i][j]][j][1]+=(d[i][j][0]+d[i][j][1]);
            }
        }
		// 출력
        sb.append(d[N-1][N-1][0]+d[N-1][N-1][1]);
		bw.write(sb.toString());
		bw.flush();
	}
}
