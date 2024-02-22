package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <제로> S4
 * 링크
 * https://www.acmicpc.net/problem/10773
 * 요약
 * 스택 문제
 * 풀이
 * 스택
 */
public class boj_10773 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int K;
    public static void main(String[] args) throws IOException {
		// 입력
		K = Integer.parseInt(br.readLine());
		// 풀이
		solution();
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	private static void solution() throws IOException{
		Stack<Integer> s = new Stack<>();
        int temp, sum=0;
        for(int i=0; i<K; i++) {
            temp = Integer.parseInt(br.readLine());
            if(temp==0) s.pop();
            else s.push(temp);
        }
        while(!s.empty()) {
            sum+=s.pop();
        }
        sb.append(sum);
	}
}
