package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj_2961 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N;
	static int[][] ingredients;
	static int S, B;
	static int sour, bitter, diff;
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());// N : 재료의 개수			
		ingredients = new int[N][2];		// [0] 신맛, [1] 쓴맛
		for(int i=0; i<N; i++) {			// 입력
			st = new StringTokenizer(br.readLine());
			ingredients[i][0] =  Integer.parseInt(st.nextToken());	
			ingredients[i][1] =  Integer.parseInt(st.nextToken());	
		}
		getFood(N);
		bw.write(sb.toString());
		bw.flush();
	}
	
	private static void getFood(int n) {
		diff = Integer.MAX_VALUE;
		int cnt = 1 << n; 					// 1<<n = 2^n, 재료집합의 부분집합의 개수
		for (int i = 1; i < cnt; ++i) {		//부분집합 개수만큼 반복 (001, 010, ... 111), 재료 안 넣는 경우 제외 (cnt=0)
			sour = 1;
			bitter = 0;
			for (int position = 0; position < N; ++position) {
				int result = i & 1 << position;
				if(result !=0) {		// 포함 되는 경우
					sour *= ingredients[position][0];
					bitter += ingredients[position][1];
				}
			}
			int temp = Math.abs(sour-bitter);
			diff = diff > temp ? temp : diff; 
		}
		sb.append(diff+"\n");
	}
}
