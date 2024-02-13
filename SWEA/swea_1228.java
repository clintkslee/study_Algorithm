package SWEA;

import java.io.*;
import java.util.*;


public class swea_1228 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N; // 원본 암호문의 길이
	static LinkedList<Integer> l;	// 원본 암호문
	static int K; // 명령어 개수
	
	static String temp;	// I 제거용
	static int index;	// index 다음에 삽입
	static int cnt;		// cnt 개
	public static void main(String[] args) throws IOException {
		for(int i=1; i<=10; ++i) {	// TC 10개 고정
			// 입력
			N = Integer.parseInt(br.readLine());
			l = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++)
				l.add(Integer.parseInt(st.nextToken()));	// 원본 암호문 입력 
			K = Integer.parseInt(br.readLine());	// K개 명령어
			// 풀이
			st = new StringTokenizer(br.readLine());	
			for(int j=0; j<K; ++j) {
				temp = st.nextToken();	// I 제거
				index = Integer.parseInt(st.nextToken());	// 입력할 위치
				cnt = Integer.parseInt(st.nextToken());		// 입력할 숫자 개수
				for(int k=0; k<cnt; k++) {
					l.add(index++, Integer.parseInt(st.nextToken()));
				}
			}
			sb.append("#"+i+" ");
			for(int num = 0 ; num < 10; num++)	// 앞 10개만 저장
				sb.append(l.get(num)+" ");
			sb.append("\n");
		}
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}
}
