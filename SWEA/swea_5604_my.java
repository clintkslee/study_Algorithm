package SWEA;

import java.io.*;
import java.util.*;

/*
* 제목
* <구간 합> D4
* 링크
* https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGGNB6cnEDFAUo
* 요약
* 
* 풀이
* 주어진 A~B 구간의 구간합 구하기
* 구간합은 각 자리수를 더하여 구한다.
*/
public class swea_5604_my {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static long[] f = new long[16]; // [0] 사용x, f[k]는 0~10^(k-1)-1 의 자릿수들 누적합, f[2]의 경우 0~99의 누적합

	static long A, B; // 구간 시작, 구간 끝

	public static void main(String[] args) throws IOException {
		getF(); // 누적 배열 생성
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			// 입력
			st = new StringTokenizer(br.readLine());
			A = Long.parseLong(st.nextToken());
			B = Long.parseLong(st.nextToken());
			// 풀이
			long ans = sol(A, B);
			// 출력
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static long sol(long A, long B) {
		long sumA = getAccum(A-1);
//		sb.append("sumA : "+sumA+"\n");
		long sumB = getAccum(B);
//		sb.append("sumB : "+sumB+"\n");
		return sumB - sumA;
	}

	//
	private static long getAccum(long n) {
		if(n==-1) {
			return 0; // 구간 시작이 0이면 -1 들어옴
		}
		/*
		 * 0~346의 구간합을 구한다고 가정하면 
		 * 우리가 아는 f[2] : 0~99 구간합, f[1] : 0~9 구간합으로 
		 * 0~299, 300~339, 340~346 을 구해야 한다.
		 * num[1]=6, num[2]=4, num[3]=3
		 * 
		 * 0~299
		 * 백의 자리가 변화하는 구간 (0, 1, 2)
		 * prefix = 0
		 * 0~299 구간의 누적합
		 * f(2) * 3(자릿수) + 3 * 10^(3-1)
		 * 
		 * 0~99 누적 = f(1)
		 * 100~199 = f(1) + 1 * 10^2
		 * 200~299 = f(1) + 2 * 10^2
		 * 
		 * ==============================
		 * 
		 * 300~339
		 * 십의 자리가 변화하는 구간 (0, 1, 2, 3) (백의 자리 3 고정)
		 * prefix = 3
		 * "00~39 구간의 누적합" + "3(prefix) * 숫자 개수(40)" 
		 * "f(1) * 2(자릿수) + 4(구간) * 10^(자릿수2-1)" + "3(prefix) * num[2] * 10^(2-1)"
		 * 
		 * ==============================
		 * 
		 * 340~346
		 * 일의 자리가 변화하는 구간 (0, 1, 2, 3, 4, 5, 6) (앞 자리 34 고정) 
		 * prefix = 3 + 4 = 7
		 * 0~6 구간의 누적합 + 7(prefix) * 숫자 개수(7) 
		 * 1~num[1] 까지의 누적 + prefix * (num[1]+1)
		 * 
		 * 
		 */

		// 입력받은 숫자를 자릿수 별로 조각내기
		long[] num = new long[17]; // 구간 최댓값인 10^15 가 16칸
		for (int i = 1; i <= 16; ++i) {
			num[i] = n % 10;
			n /= 10;
		}
		// 누적합 구하기
		long sum = 0;
		long prefix = 0;
		for (int i = 16; i >= 2; --i) { // i번째 자리수
			long accum = 0;
			for (int j = 1; j <= num[i] - 1; ++j) {
				accum += j;
			}
			sum += f[i-1] * num[i] + accum * (long)Math.pow(10, i-1) + prefix * num[i] * (long)Math.pow(10, i-1);
			prefix += num[i];
		}
		// 1의 자리 누적합 따로 계산
		for(int i=0; i<=num[1]; ++i) {
			sum += (prefix + i);
		}
		return (long)sum;
	}

	private static void getF() {
		// f[1] = 45
		// f[n] 은 0 ~ "9가n개" 까지의 누적합
		// f[3] = 0 ~ 999 의 누적
		// 점화식 : f(n) = 10 * f(n-1) + f(1) * 10^(n-1)

		f[1] = 45; // 0~9 의 자릿수 누적합( 0 + 1 + 2 + 3 + ... 8 + 9 )
		for (int i = 2; i <= 15; ++i) {
			f[i] = 10 * f[i - 1] + f[1] * (long) Math.pow(10, i - 1);
		}
	}
}
