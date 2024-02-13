package Algorithm_Template_File.format;
// package

import java.io.*;
import java.util.*;
/*
 * 제목
 * <title>
 * 링크
 * link
 * 요약
 * summary
 * 풀이
 * how
 */
public class format {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N;
    public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine());
		// 풀이
		solution();
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	private static void solution() {
		sb.append("Hello Algorithm!\n");
	}
}
