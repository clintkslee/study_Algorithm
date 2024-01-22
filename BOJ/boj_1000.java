package BOJ;

import java.io.*; // BufferedReader, BufferedWriter
import java.util.*; // StringTokenizer

public class boj_1000 {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
        bw.write(""+(A+B));
		bw.flush();
    }
}
