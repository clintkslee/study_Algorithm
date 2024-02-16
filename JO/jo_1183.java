package JO;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <동전 자판기(下)> G2
 * 링크
 * https://jungol.co.kr/problem/1183
 * 요약
 * 최대한 동전을 많이 사용하여 물건 구입하기 == (내가 가진 동전 합 - 물건 값) 을 최소 개수의 동전으로 만들기
 * 풀이
 * greedy
 */
public class jo_1183 {	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int W;	// 물건 값 W
	static int total; // 내가 가진 돈
	static int remain;	// 물건 산 후 남은 돈
	static int[] coin = new int[6]; // 500 100 50 10 5 1
	static int[] value = new int[] {500, 100, 50, 10, 5, 1};
	static int[] cnt = new int[6]; // 남은 동전 개수 
	static int sumCnt;
    public static void main(String[] args) throws IOException {
		// 입력
		W = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<6; i++) {	// 동전 개수 입력
			coin[i] = Integer.parseInt(st.nextToken());
			total += coin[i] * value[i];
		}
		remain = total - W;
		// 풀이
		solution();
		// 출력
		sb.append(sumCnt+"\n");
		for(int i=0; i<6; i++) {
			sb.append((coin[i]-cnt[i])+" ");
		}
		sb.append("\n");
		bw.write(sb.toString());
		bw.flush();
	}
    
	private static void solution() {
		for(int i=0; i<6; i++) {
			int count = Math.min(coin[i], remain/value[i]);
			remain -= value[i]*count;
			cnt[i] += count;
			if(remain==0) break;
		}
		for(int i=0; i<6; i++) {
			sumCnt += coin[i] - cnt[i];
		}
		
	}
}
