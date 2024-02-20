package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <팰린드롬수> B1
 * 링크
 * https://www.acmicpc.net/problem/1259
 * 요약
 * 1~99999 사이의 입력에 대해 팰린드롬한 지 판단
 * 풀이
 * 문자열로 입력을 받아서 [i], [n-i-1] 비교
 */
public class boj_1259 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N;
    public static void main(String[] args) throws IOException {
        String str;
        int len;
        boolean isPalindrome;
        while(true) {
            // 입력
            str = br.readLine();
            if(str.equals("0")) break;
            // 풀이
		    len = str.length();
            isPalindrome = true;
            for(int i=0; i<len/2; i++) {
                if(str.charAt(i)!=str.charAt(len-i-1)) {
                    isPalindrome = false;
                    break;
                }
            }
            // 출력
            if(isPalindrome) sb.append("yes\n");
            else sb.append("no\n");
        } 
		bw.write(sb.toString());
		bw.flush();
	}
}
