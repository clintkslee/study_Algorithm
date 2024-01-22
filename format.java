import java.io.*; // BufferedReader, BufferedWriter
import java.util.*; // StringTokenizer

public class format {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
				
		// 입력
		int N = Integer.parseInt(br.readLine());		// 스페이스 포함, 개행 전까지, 개행은 제거
		bw.write("N : "+ N+"\n");						// write() 는 char, String 만 제대로 출력
		bw.flush();										// flush() 해야 제대로 출력되나 최대한 줄이기	

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());	// 한 줄에서 여러 번 입력 받을 땐 tokenizer 사용
			int a = Integer.parseInt(st.nextToken());
			bw.write(a+" ");
			a = Integer.parseInt(st.nextToken());
			bw.write(a+" \n");
		}
		bw.flush();

		// Problem Solving
		
		
		
		// 출력
		
		
		br.close(); // 굳이 닫아야 하나 싶기도 하고
		bw.close(); 
	}
}
