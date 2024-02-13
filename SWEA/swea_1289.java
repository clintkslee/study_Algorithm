package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class swea_1289 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(bf.readLine());
		String[] s = new String[T];
		for(int i=0; i<T; i++) {
			s[i] = bf.readLine();
		}
		//
		for(int i=0; i<T; i++) {
			int cnt = 0;
			if(s[i].charAt(0)=='1') 
				cnt++;
			for(int j=1; j<s[i].length(); j++) {
				if(s[i].charAt(j)!=s[i].charAt(j-1))
					cnt++;
			}
			bw.write("#"+(i+1)+" "+cnt+"\n");
		}
		bw.flush();
		bw.close();
	}
}

