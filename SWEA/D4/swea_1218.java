package SWEA.D4;

import java.io.*;
import java.util.*;

public class swea_1218 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static int length;
	static String s;
	public static void main(String[] args) throws IOException {
		for(int i=1; i<=10; ++i)	{// TC 10개 고정
			boolean isValid = true;
			length = Integer.parseInt(br.readLine());
			s = br.readLine();
			Stack<Character> stack = new Stack<>();
			
	Valid:	for(int j=0; j<length; j++) {
				if("([{<".contains(""+s.charAt(j))) {	// 여는 괄호
					stack.add(s.charAt(j));
				} else {								// 닫는 괄호
					if(stack.isEmpty()) {				// 스택 비었을 때 닫는 괄호면 false
						isValid=false;
						break Valid;
					}
					switch (s.charAt(j)) {				// 괄호가 대응하지 않으면 false
						case ')': if(stack.pop()!='(') isValid=false;	break;
						case ']': if(stack.pop()!='[') isValid=false;	break;
						case '}': if(stack.pop()!='{') isValid=false;	break;
						case '>': if(stack.pop()!='<') isValid=false;	break;
					}
				}
			}
			if(!stack.isEmpty()) isValid = false;		// 스트링 다 읽고 스택에 괄호 남아있으면 false
			if(!isValid) sb.append("#"+i+" 0\n");
			else sb.append("#"+i+" 1\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
}
