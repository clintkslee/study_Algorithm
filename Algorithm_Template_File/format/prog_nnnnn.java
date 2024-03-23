package Algorithm_Template_File.format;
//package programmers;

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
class prog_nnnnn {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;

    public int solution(int[][] board, int[][] skill) throws IOException {
        int answer = 0;
        // 입력
        N = Integer.parseInt(br.readLine());
        // 풀이

        // 출력
        bw.write(sb.toString());
        bw.flush();
        return answer;
    }
}