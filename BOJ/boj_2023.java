package BOJ;

import java.io.*;

public class boj_2023 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	
	static int N;								//소수 특징
	static int[] primeStart = {2, 3, 5, 7};		//1자리 소수는 2, 3, 5, 7 == N자리 소수의 시작은 2, 3, 5, 7만 가능
	static int[] primeEnd = {1, 3, 7, 9};		//2자리 이상 소수는 1, 3, 7, 9로 끝나야 함
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());			// 자릿수
		for(int i = 0; i < 4; i++) {
			checkNum(""+primeStart[i], 1);				// 
		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static void checkNum(String num, int depth) {
		if(depth == N) {
			sb.append(num+"\n");
			return;
		}
		for(int i=0; i<4; i++) {
			if(isPrime(Integer.parseInt(num+primeEnd[i])))
				checkNum(num+primeEnd[i], depth+1);
		}
	}
	
	private static boolean isPrime(int k) {				// 에라토스테네스의 체
		if(k<2) return false;
		for(int i=2; i*i<=k; i++)
			if(k % i == 0) return false;
		return true;
	}
}
