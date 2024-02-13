package SWEA.D4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class swea_1210 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int cnt, startX, startY;
	static int[][] arr = new int[100][100];
	public static void main(String[] args) throws IOException {
		for(int i=0; i<10; i++) {    				// TC 10개 고정
			cnt = Integer.parseInt(br.readLine());	// TC 번호
			for(int j=0; j<100; j++) {				// 입력
				st = new StringTokenizer(br.readLine());
				for(int k=0; k<100; k++) {
					arr[j][k] = Integer.parseInt(st.nextToken());
					if(arr[j][k]==2) {
						startX = j;
						startY = k;
					}
				}
			}
			getX(startX, startY);  
		}
		bw.write(sb.toString());
		bw.flush();
	}
	static void getX(int curX, int curY) {
		if(curX==0) {
			sb.append("#"+cnt+" "+curY+"\n");
			return;
		}
		if(0 <= curY-1 && arr[curX][curY-1] == 1) {			//좌
			arr[curX][curY]=0;
			getX(curX, curY-1);
		} else if(curY+1 <= 99 && arr[curX][curY+1] == 1) {	//우
			arr[curX][curY]=0;
			getX(curX, curY+1);
		} else {											//상
			getX(curX-1, curY);
		}
	}
}