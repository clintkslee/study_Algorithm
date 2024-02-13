package BOJ.G4;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <문자열 폭발>
 * 링크
 * https://www.acmicpc.net/problem/9935
 * 요약
 * summary
 * 풀이
 * how
 */
public class boj_9935 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
	static StringBuilder ans = new StringBuilder();

	static int N;
    public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine());
		// 풀이
		solution();
		// 출력
        if(sb.length()==0) sb.append("FRULA\n");
		bw.write(sb.toString());
		bw.flush();
	}

	private static void solution() {
		while(true) {
            sb.replaceAll("3", "");
        }
	}
}
