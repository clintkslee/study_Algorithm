package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <균형잡힌 세상> S4
 * 링크
 * https://www.acmicpc.net/problem/4949
 * 요약
 * 괄호 문제
 * 풀이
 * 스택
 */
public class boj_4949 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {      
        while(true) {
            // 입력
            String str = br.readLine();
            Stack<Character> stack = new Stack<>();
            boolean flag = true;
            if(str.length()==1 && str.charAt(0)=='.') break;
            // 풀이
            for(int i=0; i<str.length(); i++) {
                char c = str.charAt(i);
                if(c=='[' || c=='(') {
                    stack.push(c);
                } else if(c==']') {
                    if(stack.isEmpty()) {
                        flag = false;
                        break;
                    } else if(stack.pop()!='[') {
                        flag = false;
                        break;
                    }
                } else if(c==')') {
                    if(stack.isEmpty()) {
                        flag = false;
                        break;
                    } else if(stack.pop()!='(') {
                        flag = false;
                        break;
                    }
                }
            }
            if(!stack.isEmpty())
                flag = false;
            // 출력
            if(flag) sb.append("yes\n");
            else sb.append("no\n");
        }
		
		bw.write(sb.toString());
		bw.flush();
	}
}
