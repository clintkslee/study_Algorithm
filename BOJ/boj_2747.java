package BOJ;

import java.io.*;
import java.util.StringTokenizer;

/*
* 제목
<피보나치 수> B2
* 링크
https://www.acmicpc.net/problem/2747
* 요약
n0 = 0, n1 = 1, n2 = 1, ...
n번째 피보나치 수 구하기
* 풀이
dp
* 메모리
14252 KB
* 시간
100 ms
*/

public class boj_2747 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[] fib;
    static int n; // n번째 피보나치 수 구하기
    public static void main(String[] args) throws IOException {
        // 초기화
        fib = new int[46];
        fib[0] = 0;
        fib[1] = 1;

        // 입력
        n = Integer.parseInt(br.readLine());

    	// 풀이
        for(int i=2; i<=45; i++) {
            fib[i] = fib[i-1] + fib[i-2];
        }
        sb.append(fib[n]);

    	// 출력
    	bw.write(sb.toString());
    	bw.flush();
    	
    }
}
