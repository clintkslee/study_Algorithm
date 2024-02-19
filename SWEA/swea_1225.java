package SWEA;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <암호생성기> D3
 * 링크
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14uWl6AF0CFAYD
 * 요약
 * 주어진 로직을 수행하는 코드 작성
 * 풀이
 * 큐 + 시뮬레이션
 */
public class swea_1225 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {                                 
        for(int i=1; i<=10; ++i) {							// TC 10개 고정
            // 입력
        	Queue<Integer> q = new ArrayDeque<Integer>();	// 8개 데이터 입력 받을 큐
            int c = Integer.parseInt(br.readLine());        // TC 번호
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<8; j++)
                q.offer(Integer.parseInt(st.nextToken())); 	// 초기 암호
 
            // 풀이
            int cnt=0;
            while(true) {
                if(q.peek()-(cnt%5+1)<=0) {         		// 0이하가 되는 경우 -> 암호
                    q.poll();
                    sb.append("#"+c+" ");
                    while(!q.isEmpty()) sb.append(q.poll()+" ");
                    sb.append("0\n");						// 암호 끝에 0 offer() 하는 대신 대신 출력에만 0 추가
                    break;
                }
                q.offer(q.poll()-(cnt%5+1));				// 첫번째 원소에서 필요한 만큼 뺀 후 offer()     
                cnt++;
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }
}