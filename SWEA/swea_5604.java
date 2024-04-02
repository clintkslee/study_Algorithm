package SWEA;

import java.io.*;
import java.util.*;

/*
* 제목
* <구간 합> D4
* 링크
* https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGGNB6cnEDFAUo
* 요약
* 주어진 A~B 구간의 구간합 구하기
* 구간합은 각 자리수를 더하여 구한다.
* 풀이
* 점화식 세우기
*/
public class swea_5604 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	// F(n) : 1~n 까지의 숫자들 자리수 누적합
	// v : n이 몇자리 수 인지 ex) 123의 v는 100
	// F(n) = F(n-1-n%v)+G(n)
	// G(n) = n/v*(n%v+1)+F(n%v)
	static HashMap<Long, Long> Fmap = new HashMap<>();
	static {
		long temp = 0;
		for (long i = 0; i <= 9; ++i) {
			temp += i;
			Fmap.put(i, temp);
		}
	}
	static long A, B; // 구간 시작, 구간 끝

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			// 입력
			st = new StringTokenizer(br.readLine());
			A = Long.parseLong(st.nextToken());
			B = Long.parseLong(st.nextToken());
			// 풀이
			long sumA = getF(A - 1);
			long sumB = getF(B);
			long ans = sumB - sumA;
			// 출력
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static long getF(long n) {
		if (n < 0)
			return 0;
		if (Fmap.containsKey(n))
			return Fmap.get(n);
		long v = getV(n);
		long f = getF(n - 1 - n % v) + getG(n);
		Fmap.put(n, f);

		return Fmap.get(n);
	}

	private static long getG(long n) {
		long v = getV(n);
		long g = n / v * (n % v + 1) + getF(n % v);
		return g;
	}

	private static long getV(long n) {
		long v = 1;
		while (n > 9) {
			v *= 10;
			n /= 10;
		}
		return v;
	}
}
