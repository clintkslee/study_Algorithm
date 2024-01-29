package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj_17478 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
	static int n;
	public static void main(String[] args) throws IOException {
		n = Integer.parseInt(br.readLine());
		bw.write("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
		chatBot(n);
		bw.flush();
	}
	static void chatBot(int k) throws IOException {
		printBar(k);
		bw.write("\"재귀함수가 뭔가요?\"\n");
		
		if(k==0) {
			printBar(k);
			bw.write("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
		} else {
			printBar(k);
			bw.write("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
			printBar(k);
			bw.write("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
			printBar(k);
			bw.write("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
			chatBot(k-1);
		}
		printBar(k);
		bw.write("라고 답변하였지.\n");
	}
	
	static void printBar(int k) throws IOException {
		for(int i=0; i<n-k; i++)	
			bw.write("____");
	}
}
