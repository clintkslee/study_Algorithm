package SWEA;

import java.io.*;
import java.util.*;

/*
* 제목
* <조합> D3
* 링크
* https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGKdbqczEDFAUo
* 요약
* 
* 풀이
* 페르마의 소정리
*/
public class swea_5607 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static long[] fact = new long[1000001];
	static int N, R;
	static long val1, val2;

	static long m = 1234567891L;

	public static void main(String[] args) throws IOException {
		// factorial 구하기
		fact[0] = 1;
		fact[1] = 1;
		for (int i = 2; i <= 1000000; ++i) {
			fact[i] = fact[i - 1] * i % m;
		}

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			// 입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());

			// 풀이
			val1 = fact[N];
			val2 = fact[R] * fact[N - R] % m;

			long val3 = divcon(val2, m - 2); // val2^(m-2) mod m

			long ans = (val1 * val3) % m;

			// 출력
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static long divcon(long val, long pow) {
		if (pow == 0)
			return 1L;
		else if (pow == 1)
			return val;
		else {
			long temp = divcon(val, pow / 2) % m;
			if (pow % 2 == 0) {
				return temp * temp % m;
			} else {
				return ((temp * temp) % m * val) % m;
			}
		}
	}
}
