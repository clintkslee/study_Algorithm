package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class swea_1208 {    
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int[] garo = new int[100];
	static int dumpCnt;                   				// 덤프 횟수
	
	static int min;
	static int minIdx;
	static int max;
	static int maxIdx;

	public static void main(String[] args) throws IOException {  
	    for(int i=0; i<10; i++) {    					// TC 10개 고정
	        dumpCnt = Integer.parseInt(br.readLine());	// 입력
	        st = new StringTokenizer(br.readLine());
	        for(int j=0; j<100; j++)
	            garo[j] = Integer.parseInt(st.nextToken());	   
	        min = 101;
	        max = -1;
	        dump(0);							      	// dump
	        sb.append("#"+(i+1)+" "+(max-min)+"\n");
	    }
	    bw.write(sb.toString());    					// 출력
	    bw.flush();
	}
	private static void dump(int depth) {
		getMinMax();
		if(depth==dumpCnt || max-min<=1) {
			return;
		}
		garo[maxIdx]--;
		garo[minIdx]++;
		dump(depth+1);
	}
	
	private static void getMinMax() {
        min = 101;
        max = -1;
		for(int i=0; i<100; i++) {
			if(min > garo[i]) {
				min = garo[i];
				minIdx = i;
			}
		}
		for(int i=0; i<100; i++) {
			if(max < garo[i]) {
				max = garo[i];
				maxIdx = i;
			}
		}
	}
}